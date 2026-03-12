package com.netsdk.demo.module;

import java.awt.Panel;

import com.netsdk.lib.NetSDKLib.CFG_DVRIP_INFO;
import com.netsdk.lib.NetSDKLib.LLong;
import com.netsdk.lib.NetSDKLib.NET_IN_LOGIN_WITH_HIGHLEVEL_SECURITY;
import com.netsdk.lib.NetSDKLib.NET_OUT_LOGIN_WITH_HIGHLEVEL_SECURITY;
import com.netsdk.lib.NetSDKLib.fServiceCallBack;
import com.netsdk.lib.NetSDKLib;
import com.netsdk.lib.ToolKits;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

public class AutoRegisterModule {
	// 监听服务句柄
	public static LLong mServerHandler = new LLong(0);	
	
	// 设备信息
	public static NetSDKLib.NET_DEVICEINFO_Ex m_stDeviceInfo = new NetSDKLib.NET_DEVICEINFO_Ex();
	
	// 语音对讲句柄
	public static LLong m_hTalkHandle = new LLong(0);
	private static boolean m_bRecordStatus    = false;
	
	/**
	 * 开启服务
	 * @param address 本地IP地址
	 * @param port 本地端口, 可以任意
	 * @param callback 回调函数
	 */
	public static boolean startServer(String address, int port, fServiceCallBack callback) {
		
		mServerHandler = LoginModule.netsdk.CLIENT_ListenServer(address, port, 1000, callback, null);
		if (0 == mServerHandler.longValue()) {
			System.err.println("Failed to start server." + ToolKits.getErrorCodePrint());
		} else {
			System.out.printf("Start server, [Server address %s][Server port %d]\n", address, port);
		}
		return mServerHandler.longValue() != 0;
	}
	
	/**
	 * 结束服务
	 */
	public static boolean stopServer() {
		boolean bRet = false;
		
		if(mServerHandler.longValue() != 0) {
			bRet = LoginModule.netsdk.CLIENT_StopListenServer(mServerHandler);	
			mServerHandler.setValue(0);
			System.out.println("Stop server!");
		}
		
		return bRet;
	}
	
	/**
	 * 登录设备(主动注册登陆接口)
	 * @param m_strIp  设备IP
	 * @param m_nPort  设备端口号
	 * @param m_strUser  设备用户名
	 * @param m_strPassword  设备密码
	 * @param deviceId  设备ID
	 * @return
	 */
	public static LLong login(String m_strIp, int m_nPort, String m_strUser, String m_strPassword, String deviceIds) {	
		Pointer deviceId = ToolKits.GetGBKStringToPointer(deviceIds);		
		//IntByReference nError = new IntByReference(0);
		//入参
		NET_IN_LOGIN_WITH_HIGHLEVEL_SECURITY pstInParam=new NET_IN_LOGIN_WITH_HIGHLEVEL_SECURITY();
		pstInParam.nPort=m_nPort;
		pstInParam.szIP=m_strIp.getBytes();
		pstInParam.szPassword=m_strPassword.getBytes();
		pstInParam.szUserName=m_strUser.getBytes();
		pstInParam.emSpecCap = 2;// 主动注册方式
		pstInParam.pCapParam=deviceId;
		//出参
		NET_OUT_LOGIN_WITH_HIGHLEVEL_SECURITY pstOutParam=new NET_OUT_LOGIN_WITH_HIGHLEVEL_SECURITY();
		pstOutParam.stuDeviceInfo=m_stDeviceInfo;
		/*LLong m_hLoginHandle = LoginModule.netsdk.CLIENT_LoginEx2(m_strIp, m_nPort, m_strUser, m_strPassword, 
								    tcpSpecCap, deviceId, m_stDeviceInfo, nError);*/
		LLong m_hLoginHandle=LoginModule.netsdk.CLIENT_LoginWithHighLevelSecurity(pstInParam, pstOutParam);
		return m_hLoginHandle;
	}
	
	/**
	 * P2P登录设备 - 通过设备序列号进行P2P连接
	 * @param serialNumber  设备序列号(SN)
	 * @param username  设备用户名
	 * @param password  设备密码
	 * @return 登录句柄, 0表示失败
	 */
	public static LLong loginP2P(String serialNumber, String username, String password) {
		Pointer pSerialNumber = ToolKits.GetGBKStringToPointer(serialNumber);
		IntByReference nError = new IntByReference(0);
		
		// 步骤1: 设置P2P网络参数 (必须在P2P登录前调用)
		System.out.println("[P2P] Step 1: Setting P2P network parameters via CLIENT_SetOptimizeMode...");
		NetSDKLib.NET_PARAM p2pNetParam = new NetSDKLib.NET_PARAM();
		p2pNetParam.nWaittime = 30000;           // P2P等待超时30秒(云中继需要更长时间)
		p2pNetParam.nConnectTime = 30000;         // P2P连接超时30秒
		p2pNetParam.nConnectTryNum = 3;           // 尝试3次
		p2pNetParam.nGetDevInfoTime = 10000;      // 获取设备信息超时10秒
		p2pNetParam.nGetConnInfoTime = 10000;     // 获取子连接超时10秒
		p2pNetParam.nSubConnectSpaceTime = 100;   // 子连接间隔100ms
		p2pNetParam.byNetType = 1;                // WAN模式 (P2P通过互联网)
		p2pNetParam.bDetectDisconnTime = 60;      // 心跳检测60秒
		p2pNetParam.bKeepLifeInterval = 10;       // 心跳间隔10秒
		p2pNetParam.write();
		
		boolean optimizeResult = LoginModule.netsdk.CLIENT_SetOptimizeMode(
			2,  // EM_OPT_TYPE_P2P_NETPARAM_V1 = 2
			p2pNetParam.getPointer()
		);
		System.out.println("[P2P] SetOptimizeMode result: " + (optimizeResult ? "SUCCESS" : "FAILED"));
		
		// 步骤2: 同时设置全局连接超时(更长)
		LoginModule.netsdk.CLIENT_SetConnectTime(30000, 3);
		
		// 步骤3: 使用 CLIENT_LoginEx2 + nSpecCap = EM_LOGIN_SPEC_CAP_P2P (19)
		System.out.println("[P2P] Step 2: Trying CLIENT_LoginEx2 with nSpecCap=19...");
		
		LLong loginHandle = LoginModule.netsdk.CLIENT_LoginEx2(
			"0.0.0.0",  // P2P不需要IP
			0,           // P2P不需要端口
			username, 
			password, 
			19,          // EM_LOGIN_SPEC_CAP_P2P = 19
			pSerialNumber,  // Serial Number
			m_stDeviceInfo, 
			nError
		);
		
		if (loginHandle.longValue() != 0) {
			System.out.printf("[P2P] Login Success via CLIENT_LoginEx2! SN[%s], Handle: %d\n", serialNumber, loginHandle.longValue());
			// 恢复默认连接超时
			LoginModule.netsdk.CLIENT_SetConnectTime(5000, 1);
			return loginHandle;
		}
		
		System.err.printf("[P2P] CLIENT_LoginEx2 failed. Error code: %d, %s\n", nError.getValue(), ToolKits.getErrorCodePrint());
		
		// 步骤4: 回退方式 - CLIENT_LoginWithHighLevelSecurity + emSpecCap = 19
		System.out.println("[P2P] Step 3: Trying CLIENT_LoginWithHighLevelSecurity with emSpecCap=19...");
		
		NET_IN_LOGIN_WITH_HIGHLEVEL_SECURITY pstInParam = new NET_IN_LOGIN_WITH_HIGHLEVEL_SECURITY();
		pstInParam.nPort = 0;
		pstInParam.szIP = "0.0.0.0".getBytes();
		pstInParam.szPassword = password.getBytes();
		pstInParam.szUserName = username.getBytes();
		pstInParam.emSpecCap = 19;  // EM_LOGIN_SPEC_CAP_P2P
		pstInParam.pCapParam = pSerialNumber;
		
		NET_OUT_LOGIN_WITH_HIGHLEVEL_SECURITY pstOutParam = new NET_OUT_LOGIN_WITH_HIGHLEVEL_SECURITY();
		pstOutParam.stuDeviceInfo = m_stDeviceInfo;
		
		loginHandle = LoginModule.netsdk.CLIENT_LoginWithHighLevelSecurity(pstInParam, pstOutParam);
		
		// 恢复默认连接超时
		LoginModule.netsdk.CLIENT_SetConnectTime(5000, 1);
		
		if (loginHandle.longValue() != 0) {
			System.out.printf("[P2P] Login Success via HighLevelSecurity! SN[%s], Handle: %d\n", serialNumber, loginHandle.longValue());
		} else {
			System.err.printf("[P2P] HighLevelSecurity also failed. Error: %d, %s\n", pstOutParam.nError, ToolKits.getErrorCodePrint());
		}
		
		return loginHandle;
	}
	
	/**
	 * 登出设备
	 * @param m_hLoginHandle  登陆句柄
	 * @return
	 */
	public static boolean logout(LLong m_hLoginHandle) {
		boolean bRet = false;
		if(m_hLoginHandle.longValue() != 0) {
			bRet = LoginModule.netsdk.CLIENT_Logout(m_hLoginHandle);
			m_hLoginHandle.setValue(0);	
		}

		return bRet;
	}
	
	/**
	 * 开始预览
	 * @param m_hLoginHandle 登陆句柄
	 * @param channel  通道号
	 * @param stream  码流类型
	 * @param realPlayWindow  拉流窗口
	 * @return
	 */
	public static LLong startRealPlay(LLong m_hLoginHandle, int channel, int stream, Panel realPlayWindow) {
		LLong m_hPlayHandle = LoginModule.netsdk.CLIENT_RealPlayEx(m_hLoginHandle, channel, Native.getComponentPointer(realPlayWindow), stream);
	
	    if(m_hPlayHandle.longValue() == 0) {
	  	    System.err.println("Failed to start realplay." + ToolKits.getErrorCodePrint());
	    } else {
	  	    System.out.println("Success to start realplay"); 
	    }
	    
	    return m_hPlayHandle;
	} 
	
	/**
	 * 停止预览
	 * @param m_hPlayHandle 实时预览句柄
	 * @return
	 */
	public static boolean stopRealPlay(LLong m_hPlayHandle) {
		boolean bRet = false;
		if(m_hPlayHandle.longValue() != 0) {
			bRet = LoginModule.netsdk.CLIENT_StopRealPlayEx(m_hPlayHandle);
			m_hPlayHandle.setValue(0);
		}
		
		return bRet;
	}
	
	/**
	 * 远程抓图
	 * @param m_hLoginHandle 登陆句柄
	 * @param chn  通道号
	 * @return
	 */
	public static boolean snapPicture(LLong m_hLoginHandle, int chn) {
		// 发送抓图命令给前端设备，抓图的信息
		NetSDKLib.SNAP_PARAMS msg = new NetSDKLib.SNAP_PARAMS(); 
		msg.Channel = chn;  			// 抓图通道
		msg.mode = 0;    			    // 抓图模式
		msg.Quality = 3;				// 画质
		msg.InterSnap = 0; 	            // 定时抓图时间间隔
		msg.CmdSerial = 0;  			// 请求序列号，有效值范围 0~65535，超过范围会被截断为  
		
		IntByReference reserved = new IntByReference(0);
		
		if (!LoginModule.netsdk.CLIENT_SnapPictureEx(m_hLoginHandle, msg, reserved)) { 
			System.err.printf("SnapPictureEx Failed!" + ToolKits.getErrorCodePrint());
			return false;
		} else { 
			System.out.println("SnapPictureEx success"); 
		}
		return true;
	}
	
	/**
	 *设置抓图回调函数， 图片主要在m_SnapReceiveCB中返回
	 * @param m_SnapReceiveCB
	 */
	public static void setSnapRevCallBack(NetSDKLib.fSnapRev m_SnapReceiveCB){ 	
		LoginModule.netsdk.CLIENT_SetSnapRevCallBack(m_SnapReceiveCB, null);
	}
	
	/**
	 * 获取网络协议
	 * @param m_hLoginHandle 登录句柄
	 * @return
	 */
	public static CFG_DVRIP_INFO getDVRIPConfig(LLong m_hLoginHandle) {
		CFG_DVRIP_INFO msg = new CFG_DVRIP_INFO();
		
		if(!ToolKits.GetDevConfig(m_hLoginHandle, -1, NetSDKLib.CFG_CMD_DVRIP, msg)) {
			return null;
		}
		
		return msg;
	}
	
	/**
	 * 网络协议配置
	 * @param m_hLoginHandle 登陆句柄
	 * @param enable  使能
	 * @param address 服务器地址
	 * @param nPort  服务器端口号
	 * @param deviceId  设备ID
	 * @param info 获取到的网络协议配置
	 * @return
	 */
	public static boolean setDVRIPConfig(LLong m_hLoginHandle, boolean enable, String address, int nPort, byte[] deviceId, CFG_DVRIP_INFO info) {
		CFG_DVRIP_INFO msg = info;
		// 主动注册配置个数
		msg.nRegistersNum = 1;  
		
		 // 主动注册使能
		msg.stuRegisters[0].bEnable = enable? 1:0; 
	
		// 服务器个数
		msg.stuRegisters[0].nServersNum = 1;
		
		// 服务器地址
		ToolKits.StringToByteArray(address, msg.stuRegisters[0].stuServers[0].szAddress);
	    
		// 服务器端口号
		msg.stuRegisters[0].stuServers[0].nPort = nPort;
	    
	    // 设备ID
		ToolKits.ByteArrayToByteArray(deviceId, msg.stuRegisters[0].szDeviceID);
		
		return ToolKits.SetDevConfig(m_hLoginHandle, -1, NetSDKLib.CFG_CMD_DVRIP, msg);
	}
	
	/**
	 * \if ENGLISH_LANG
	 * Start Talk
	 * \else
	 * 开始通话
	 * \endif
	 */
	public static boolean startTalk(LLong m_hLoginHandle) {
	
		// 设置语音对讲编码格式
		NetSDKLib.NETDEV_TALKDECODE_INFO talkEncode = new NetSDKLib.NETDEV_TALKDECODE_INFO();
		talkEncode.encodeType = NetSDKLib.NET_TALK_CODING_TYPE.NET_TALK_PCM;
		talkEncode.dwSampleRate = 8000;
		talkEncode.nAudioBit = 16;
		talkEncode.nPacketPeriod = 25;
		talkEncode.write();
		if(LoginModule.netsdk.CLIENT_SetDeviceMode(m_hLoginHandle, NetSDKLib.EM_USEDEV_MODE.NET_TALK_ENCODE_TYPE, talkEncode.getPointer())) {
			System.out.println("Set Talk Encode Type Succeed!");
		} else {
			System.err.println("Set Talk Encode Type Failed!" + ToolKits.getErrorCodePrint());
			return false;
		}
		
		// 设置对讲模式
		NetSDKLib.NET_SPEAK_PARAM speak = new NetSDKLib.NET_SPEAK_PARAM();
        speak.nMode = 0;
        speak.bEnableWait = 0;
        speak.nSpeakerChannel = 0;
        speak.write();
        
        if (LoginModule.netsdk.CLIENT_SetDeviceMode(m_hLoginHandle, NetSDKLib.EM_USEDEV_MODE.NET_TALK_SPEAK_PARAM, speak.getPointer())) {
        	System.out.println("Set Talk Speak Mode Succeed!");
        } else {
        	System.err.println("Set Talk Speak Mode Failed!" + ToolKits.getErrorCodePrint());
			return false;
        }
		
		// 设置语音对讲是否为转发模式
		NetSDKLib.NET_TALK_TRANSFER_PARAM talkTransfer = new NetSDKLib.NET_TALK_TRANSFER_PARAM();
		talkTransfer.bTransfer = 0;   // 是否开启语音对讲转发模式, 1-true; 0-false
		talkTransfer.write();
		if(LoginModule.netsdk.CLIENT_SetDeviceMode(m_hLoginHandle, NetSDKLib.EM_USEDEV_MODE.NET_TALK_TRANSFER_MODE, talkTransfer.getPointer())) {
			System.out.println("Set Talk Transfer Mode Succeed!");
		} else {
			System.err.println("Set Talk Transfer Mode Failed!" + ToolKits.getErrorCodePrint());
			return false;
		}
		
		m_hTalkHandle = LoginModule.netsdk.CLIENT_StartTalkEx(m_hLoginHandle, AudioDataCB.getInstance(), null);
		
	    if(m_hTalkHandle.longValue() == 0) {
	  	    System.err.println("Start Talk Failed!" + ToolKits.getErrorCodePrint());	  
	  	    return false;
	    } else {
	  	    System.out.println("Start Talk Success");
			if(LoginModule.netsdk.CLIENT_RecordStart()){
				System.out.println("Start Record Success");
				m_bRecordStatus = true;
			} else {
				System.err.println("Start Local Record Failed!" + ToolKits.getErrorCodePrint());
				stopTalk(m_hTalkHandle);				
				return false;
			}
	    }
		
		return true;
	}
	
	/**
	 * \if ENGLISH_LANG
	 * Stop Talk
	 * \else
	 * 结束通话
	 * \endif
	 */
	public static void stopTalk(LLong m_hTalkHandle) {
		if(m_hTalkHandle.longValue() == 0) {
			return;
		}
		
		if (m_bRecordStatus){
			LoginModule.netsdk.CLIENT_RecordStop();
			m_bRecordStatus = false;
		}
		
		if(!LoginModule.netsdk.CLIENT_StopTalkEx(m_hTalkHandle)) {			
			System.err.println("Stop Talk Failed!" + ToolKits.getErrorCodePrint());
    	} else {
    		m_hTalkHandle.setValue(0);
    	}
	}
	
	/**
	 * \if ENGLISH_LANG
	 * Audio Data Callback
	 * \else
	 * 语音对讲的数据回调
	 * \endif
	 */
	private static class AudioDataCB implements NetSDKLib.pfAudioDataCallBack {
		
		private AudioDataCB() {}
		private static AudioDataCB audioCallBack = new AudioDataCB();
		
		public static AudioDataCB getInstance() {
			return audioCallBack;
		}
		
		public void invoke(LLong lTalkHandle, Pointer pDataBuf, int dwBufSize, byte byAudioFlag, Pointer dwUser){
			
			if(lTalkHandle.longValue() != m_hTalkHandle.longValue()) {
				return;
			}
			
			if (byAudioFlag == 0) { // 将收到的本地PC端检测到的声卡数据发送给设备端
				
				LLong lSendSize = LoginModule.netsdk.CLIENT_TalkSendData(m_hTalkHandle, pDataBuf, dwBufSize);
				if(lSendSize.longValue() != (long)dwBufSize) {
					System.err.println("send incomplete" + lSendSize.longValue() + ":" + dwBufSize);
				} 
			}else if (byAudioFlag == 1) { // 将收到的设备端发送过来的语音数据传给SDK解码播放
				LoginModule.netsdk.CLIENT_AudioDecEx(m_hTalkHandle, pDataBuf, dwBufSize);
			}
		}
	}
}

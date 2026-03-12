package com.netsdk.demo.module;

import java.awt.Panel;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

import com.netsdk.common.SavePath;
import com.netsdk.lib.NetSDKLib;
import com.netsdk.lib.NetSDKLib.LLong;
import com.netsdk.lib.ToolKits;

import com.netsdk.lib.enumeration.EM_AUDIO_DATA_TYPE;
import com.netsdk.lib.enumeration.EM_REAL_DATA_TYPE;
import com.netsdk.lib.enumeration.ENUMERROR;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

/**
 * 实时预览接口实现
 * 主要有 ：开始拉流、停止拉流功能
 */
public class RealPlayModule {	
	/**
	 * \if ENGLISH_LANG
	 * Start RealPlay
	 * \else
	 * 开始预览
	 * \endif
	 */
	public static LLong startRealPlay(int channel, int stream, Panel realPlayWindow) {
		LLong m_hPlayHandle = LoginModule.netsdk.CLIENT_RealPlayEx(LoginModule.m_hLoginHandle, channel, Native.getComponentPointer(realPlayWindow), stream);
	
	    if(m_hPlayHandle.longValue() == 0) {
	  	    System.err.println("开始实时预览失败，错误码" + ToolKits.getErrorCodePrint());
	    } else {
	  	    System.out.println("Success to start realplay"); 
	    }
	    
	    return m_hPlayHandle;
	} 
	
	/**
	 * \if ENGLISH_LANG
	 * Start RealPlay
	 * \else
	 * 停止预览
	 * \endif
	 */
	public static void stopRealPlay(LLong m_hPlayHandle) {
		if(m_hPlayHandle.longValue() == 0) {
			return;
		}
		
		boolean bRet = LoginModule.netsdk.CLIENT_StopRealPlayEx(m_hPlayHandle);
		if(bRet) {
			m_hPlayHandle.setValue(0);
		}
	}


	static String path = SavePath.getSavePath().getSaveRecordFilePathWithOutPostfix();
	static File file = new File(path);
	static String absolutePath = file.getAbsolutePath();

	/**
	 * 获取指定类型的码流数据
	 *
	 * @param channelID 通道号
	 * @param dataType 码流类型
	 * @param panel 播放窗口,swing应用可用,一般传入null
	 * @return
	 */
	public static LLong realPlayByDataType(
			int channelID,
			int dataType,
			Panel panel) {
		NetSDKLib.NET_IN_REALPLAY_BY_DATA_TYPE inParam = new NetSDKLib.NET_IN_REALPLAY_BY_DATA_TYPE();
		inParam.rType = 0;
		inParam.nChannelID = channelID;
		inParam.emDataType = dataType;
		inParam.emAudioType = 0;
//		inParam.szSaveFileName = "";
		inParam.cbRealDataEx = DefaultRealPlayCallback.getInstance();
		if(dataType == 3){
			inParam.nMP4Type = 1;
		}else {
			inParam.nMP4Type = 0;
		}
		if (panel != null) {
			inParam.hWnd = Native.getComponentPointer(panel);
		}

		NetSDKLib.NET_OUT_REALPLAY_BY_DATA_TYPE outParam =
				new NetSDKLib.NET_OUT_REALPLAY_BY_DATA_TYPE();
		NetSDKLib.LLong realplay =
				LoginModule.netsdk
						.CLIENT_RealPlayByDataType(
								LoginModule.m_hLoginHandle, inParam, outParam, 3000);
		if (realplay.longValue() == 0) {
			System.out.println(ENUMERROR.getErrorMessage());
		}
		return realplay;
	}

	public static class DefaultRealPlayCallback implements NetSDKLib.fRealDataCallBackEx2 {
		private static DefaultRealPlayCallback instance;

		private DefaultRealPlayCallback() {}

		public static DefaultRealPlayCallback getInstance() {
			if (instance == null) {
				instance = new DefaultRealPlayCallback();
			}
			return instance;
		}

		@Override
		public void invoke(
				NetSDKLib.LLong lRealHandle,
				int dwDataType,
				Pointer pBuffer,
				int dwBufSize,
				NetSDKLib.LLong param,
				Pointer dwUser) {
			System.out.println("dwDataType==" + dwDataType);
			// 私有流或mp4文件
			if (dwDataType == 1003) {
				System.out.println("码流大小为" + dwBufSize + "\n" + "码流类型为mp4");
				System.out.println("param:" + param.intValue());
				byte[] buffer = pBuffer.getByteArray(0, dwBufSize);
				String filePath = absolutePath + "3333.mp4";//此处仅演示windows环境
				// 获取 param 的值并将其转换为整数
				long offset = param.longValue(); // 使用 long 类型，如果 param 是 64 位的
				try {
					saveToFile(filePath, buffer, offset);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if (dwDataType == 0) {
				System.out.println("码流大小为" + dwBufSize + "\n" + "码流类型为dav");
				System.out.println("param:" + param.intValue());
				byte[] buffer = pBuffer.getByteArray(0, dwBufSize);
				String filePath = absolutePath + "0000.dav";//此处仅演示windows环境
				addToFile(filePath, buffer);
			} else {
				int dataType = dwDataType - 1000;
				// h264流
				if (dataType == 4) {
					System.out.println("码流大小为" + dwBufSize + "\n" + "码流类型为h264");
					System.out.println("param:" + param.intValue());
					byte[] buffer = pBuffer.getByteArray(0, dwBufSize);
					String filePath = absolutePath + "4444.h264";//此处仅演示windows环境
					addToFile(filePath, buffer);
				} else if (dataType == 5) {
					System.out.println("码流大小为" + dwBufSize + "\n" + "码流类型为flv");
					System.out.println("param:" + param.intValue());
					byte[] buffer = pBuffer.getByteArray(0, dwBufSize);
					String filePath = absolutePath + "5555.flv";//此处仅演示windows环境
					addToFile(filePath, buffer);
				}else if (dataType == 1) {
					System.out.println("码流大小为" + dwBufSize + "\n" + "码流类型为ps");
					System.out.println("param:" + param.intValue());
					byte[] buffer = pBuffer.getByteArray(0, dwBufSize);
					String filePath = absolutePath + "1111.ps";//此处仅演示windows环境
					addToFile(filePath, buffer);
				}else if (dataType == 2) {
					System.out.println("码流大小为" + dwBufSize + "\n" + "码流类型为ts");
					System.out.println("param:" + param.intValue());
					byte[] buffer = pBuffer.getByteArray(0, dwBufSize);
					String filePath = absolutePath + "2222.ts";//此处仅演示windows环境
					addToFile(filePath, buffer);
				}
			}

			if(dwDataType!=0){
				System.out.println("realHandler: "+lRealHandle.longValue()+",dwDataType: "+dwDataType+",bufSize: "+dwBufSize);
			}
		}

		// 保存数据到文件的方法，使用指定的偏移量
		private synchronized void saveToFile(String filePath, byte[] data, long offset) {
			try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw")) { // 以读写模式打开文件
				raf.seek(offset); // 移动到指定的偏移量
				raf.write(data);  // 写入数据
				System.out.println("数据已写入到偏移量 " + offset + ": " + filePath);
			} catch (IOException e) {
				// 处理写入文件时的异常
				e.printStackTrace();
			}
		}

		// 保存数据到文件的方法
		private synchronized void addToFile(String filePath, byte[] buffer) {
			try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw")) {
				raf.seek(raf.length()); // 移动到文件末尾
				raf.write(buffer);
				System.out.println("数据已追加到文件: " + filePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

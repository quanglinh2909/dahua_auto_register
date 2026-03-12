package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 南网接入配置, 对应 NET_EM_CFG_VSP_NFDW
*/
public class NET_CFG_VSP_NFDW_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 接入使能
    */
    public int              bEnable;
    /**
     * 服务器IP
    */
    public byte[]           szServerIp = new byte[16];
    /**
     * 端口号
    */
    public int              nServerPort;
    /**
     * 心跳间隔(minute)
    */
    public int              nHeartBeatInterval;
    /**
     * 采样间隔(minute)
    */
    public int              nSamplingInterval;
    /**
     * 休眠时间
    */
    public int              nSleepingTime;
    /**
     * 在线时长
    */
    public int              nOnlineTime;
    /**
     * 设备密码
    */
    public byte[]           szDevicePassword = new byte[16];
    /**
     * 密文验证码
    */
    public byte[]           szAuthCode = new byte[16];
    /**
     * 终端号码(6位)
    */
    public byte[]           szDeviceNumber = new byte[16];
    /**
     * 卡号限制只能输入11位号码
    */
    public byte[]           szCardNumber = new byte[16];
    /**
     * 图片断网续传使能
    */
    public int              bImageEnable;
    /**
     * 短视频断网续传使能
    */
    public int              bVideoEnable;
    /**
     * 当前设备软件版本号
    */
    public byte[]           szSoftwareVersion = new byte[128];
    /**
     * 南网开始升级时间,参见结构体定义 {@link com.netsdk.lib.structure.NET_VSP_NFDW_TIME}
    */
    public NET_VSP_NFDW_TIME stuStartUpgraderTime = new NET_VSP_NFDW_TIME();
    /**
     * 心跳超时次数
    */
    public int              nHeartBeatTimeoutCount;
    /**
     * 协议版本
    */
    public int              nVersion;
    /**
     * 调试模式配置, 0-非调试，1-aplog模式,，2-tcpdump 调试模式
    */
    public int              nTestMode;
    /**
     * 坐标原点(3D定位), 0：左上 1：左下
    */
    public int              nCoordinateOrigin;
    /**
     * 录像回放播放速度字段解析是否启动大小端转换
    */
    public int              bRecordPlaybackScale;
    /**
     * openCPU服务端口
    */
    public int              nOpenCPUPort;

    public NET_CFG_VSP_NFDW_INFO() {
        this.dwSize = this.size();
    }
}


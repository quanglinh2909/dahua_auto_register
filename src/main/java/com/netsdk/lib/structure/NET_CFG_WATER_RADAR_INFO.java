package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 水利雷达相关参数配置
*/
public class NET_CFG_WATER_RADAR_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 雷达使能
    */
    public int              bEnable;
    /**
     * 安装角度，范围（0-180度）
    */
    public int              nInstallAngle;
    /**
     * 安装高度，雷达模块到水底的距离，范围（0-20000厘米）
    */
    public float            fInstallHeight;
    /**
     * 基准高程，水底的海拔高度，范围（-16000-400000厘米）
    */
    public float            fBaseElevation;
    /**
     * 水位上限报警阈值，范围（-16000-400000厘米）
    */
    public float            fHighLevel;
    /**
     * 水位下线报警阈值，范围（-16000-400000厘米）
    */
    public float            fLowLevel;
    /**
     * 报警时间间隔，范围（1-1440分钟）
    */
    public int              nAlarmInterval;
    /**
     * 是否上报实时水位数据
    */
    public int              bDataUpload;
    /**
     * 实时水位数据上报间隔，范围（1-1440分钟）
    */
    public int              nUploadInterval;
    /**
     * 水位报警联动,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_ALARM_MSG_HANDLE}
    */
    public NetSDKLib.NET_ALARM_MSG_HANDLE stuEventHandler = new NetSDKLib.NET_ALARM_MSG_HANDLE();

    public NET_CFG_WATER_RADAR_INFO() {
        this.dwSize = this.size();
    }
}


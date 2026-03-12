package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 控制外部电源状态的具体信息
*/
public class NET_MISC_POWER_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 外围设备的ID值, 0-单片机 1-雷达 2-3G模块 3-WIFI 4-硬盘 5-GPS 6-频闪灯 7-防雾装置 8-RFID , 9-SCR 可控硅通断锁 0-关闭，1-开启 在操作可控硅前需开启 操作后关闭 , 10-SCR1可控硅0通断 11-SCR1可控硅1通断 12-SCR2可控硅2通断 13-12V可控输出 14-POE电源, 15-12V可控输出 2 16-12V可控输出 3 17-12V可控输出 4 18-12V可控输出 5 19-12V可控输出 6 , 20-蓝牙模块 21-sd卡模块 22-电池模块 23-人证模块 24-PSTN输出信号切换开关
    */
    public int              nTypeId;
    /**
     * 控制外部设备电源状态 0-关,1-开
    */
    public int              nCmd;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[256];

    public NET_MISC_POWER_INFO() {
    }
}


package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 获取SIM卡的IMSI值出参
*/
public class NET_OUT_SIMINFO_GET_IMSI extends NetSDKLib.SdkStructure
{
    /**
     * 赋值为结构体大小
    */
    public int              dwSize;
    /**
     * IMSI值
    */
    public byte[]           szIMSI = new byte[32];
    /**
     * ICCID号
    */
    public byte[]           szICCID = new byte[32];

    public NET_OUT_SIMINFO_GET_IMSI() {
        this.dwSize = this.size();
    }
}


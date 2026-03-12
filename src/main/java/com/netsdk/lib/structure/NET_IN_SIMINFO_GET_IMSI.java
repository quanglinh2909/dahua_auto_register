package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 获取SIM卡的IMSI值入参
*/
public class NET_IN_SIMINFO_GET_IMSI extends NetSDKLib.SdkStructure
{
    /**
     * 赋值为结构体大小
    */
    public int              dwSize;
    /**
     * 无线模块名称,参见枚举定义 {@link com.netsdk.lib.enumeration.EM_WIRELESS_MODE}
    */
    public int              emMode;

    public NET_IN_SIMINFO_GET_IMSI() {
        this.dwSize = this.size();
    }
}


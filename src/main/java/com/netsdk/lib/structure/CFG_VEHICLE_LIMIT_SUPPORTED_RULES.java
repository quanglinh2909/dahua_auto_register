package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 停车上限规则
*/
public class CFG_VEHICLE_LIMIT_SUPPORTED_RULES extends NetSDKLib.SdkStructure
{
    /**
     * 是否支持报表
    */
    public int              bSupportLocalDataStore;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1020];

    public CFG_VEHICLE_LIMIT_SUPPORTED_RULES() {
    }
}


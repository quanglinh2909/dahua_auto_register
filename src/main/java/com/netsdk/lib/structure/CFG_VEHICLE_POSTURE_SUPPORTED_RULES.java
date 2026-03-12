package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 车辆态势规则
*/
public class CFG_VEHICLE_POSTURE_SUPPORTED_RULES extends NetSDKLib.SdkStructure
{
    /**
     * 是否支持报表
    */
    public int              bSupportLocalDataStore;
    /**
     * 是否支持灵敏度
    */
    public int              bSupportSensitivity;
    /**
     * 支持的态势检测模式:"Percent",按百分比;"Vehicle",按车辆数
    */
    public BYTE_ARRAY_16[]  szSupportDetectTypeList = new BYTE_ARRAY_16[32];
    /**
     * 态势检测模式数量
    */
    public int              nSupportDetectTypeListNum;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1020];

    public CFG_VEHICLE_POSTURE_SUPPORTED_RULES() {
        for(int i = 0; i < szSupportDetectTypeList.length; i++){
            szSupportDetectTypeList[i] = new BYTE_ARRAY_16();
        }
    }
}


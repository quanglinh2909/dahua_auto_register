package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 车辆密度
*/
public class CFG_VEHICLES_DISTRI extends NetSDKLib.SdkStructure
{
    /**
     * 支持的规则,参见结构体定义 {@link com.netsdk.lib.structure.CFG_VEHICLES_SUPPORTED_RULES}
    */
    public CFG_VEHICLES_SUPPORTED_RULES stuSupportedRules = new CFG_VEHICLES_SUPPORTED_RULES();
    /**
     * 标定线段相关能力,参见结构体定义 {@link com.netsdk.lib.structure.CFG_VEHICLES_CALIBRATION}
    */
    public CFG_VEHICLES_CALIBRATION stuCalibration = new CFG_VEHICLES_CALIBRATION();
    /**
     * 支持的最大规则数
    */
    public int              nMaxRules;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1020];

    public CFG_VEHICLES_DISTRI() {
    }
}


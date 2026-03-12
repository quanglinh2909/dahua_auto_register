package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 人群分布图
*/
public class CFG_CROWD_DISTRI_MAP extends NetSDKLib.SdkStructure
{
    /**
     * 支持的规则,参见结构体定义 {@link com.netsdk.lib.structure.CFG_CROWD_SUPPORTED_RULES}
    */
    public CFG_CROWD_SUPPORTED_RULES stuSupportedRules = new CFG_CROWD_SUPPORTED_RULES();
    /**
     * 标定线段相关能力,参见结构体定义 {@link com.netsdk.lib.structure.CFG_VEHICLES_CALIBRATION}
    */
    public CFG_VEHICLES_CALIBRATION stuCalibration = new CFG_VEHICLES_CALIBRATION();
    /**
     * 大模型能力,参见结构体定义 {@link com.netsdk.lib.structure.CFG_CROWD_LLM_INFO}
    */
    public CFG_CROWD_LLM_INFO stuLLM = new CFG_CROWD_LLM_INFO();
    /**
     * 支持的最大规则数
    */
    public int              nMaxRules;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1020];

    public CFG_CROWD_DISTRI_MAP() {
    }
}


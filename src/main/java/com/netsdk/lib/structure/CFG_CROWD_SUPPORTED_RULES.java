package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 支持的规则
*/
public class CFG_CROWD_SUPPORTED_RULES extends NetSDKLib.SdkStructure
{
    /**
     * 人群分布图规则,参见结构体定义 {@link com.netsdk.lib.structure.CFG_CROWD_DISTRI_MAP_RULE}
    */
    public CFG_CROWD_DISTRI_MAP_RULE stuCrowdDistriMap = new CFG_CROWD_DISTRI_MAP_RULE();
    /**
     * 人群态势检测,参见结构体定义 {@link com.netsdk.lib.structure.CFG_CROWD_POSTURE_DETECTION_RULE}
    */
    public CFG_CROWD_POSTURE_DETECTION_RULE stuCrowdPostureDetection = new CFG_CROWD_POSTURE_DETECTION_RULE();
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public CFG_CROWD_SUPPORTED_RULES() {
    }
}


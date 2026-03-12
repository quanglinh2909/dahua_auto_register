package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 人群态势检测
*/
public class CFG_CROWD_POSTURE_DETECTION_RULE extends NetSDKLib.SdkStructure
{
    /**
     * 是否支持灵敏度
    */
    public int              bSupportSensitivity;
    /**
     * 是否支持报表
    */
    public int              bSupportLocalDataStore;
    /**
     * 人群态势小模型下人数检测范围
    */
    public int[]            nMiniNumLimit = new int[2];
    /**
     * AI预览中支持展示的属性:"Event",事件;"Type",报警类型;"PeopleNum",人数;"Percent",百分比
    */
    public BYTE_ARRAY_16[]  szFeatureList = new BYTE_ARRAY_16[32];
    /**
     * 支持的态势检测模式:"Percent",按百分比;"Person",按人数
    */
    public BYTE_ARRAY_16[]  szSupportDetectTypeList = new BYTE_ARRAY_16[32];
    /**
     * 属性列表数量
    */
    public int              nFeatureListNum;
    /**
     * 态势检测模式列表数量
    */
    public int              nSupportDetectTypeListNum;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public CFG_CROWD_POSTURE_DETECTION_RULE() {
        for(int i = 0; i < szFeatureList.length; i++){
            szFeatureList[i] = new BYTE_ARRAY_16();
        }
        for(int i = 0; i < szSupportDetectTypeList.length; i++){
            szSupportDetectTypeList[i] = new BYTE_ARRAY_16();
        }
    }
}


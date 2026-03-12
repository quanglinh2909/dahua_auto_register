package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 人群分布图规则
*/
public class CFG_CROWD_DISTRI_MAP_RULE extends NetSDKLib.SdkStructure
{
    /**
     * 人群分布图统计区是否支持任意边形
    */
    public int              bSupportPolygon;
    /**
     * 是否支持报表
    */
    public int              bSupportLocalDataStore;
    /**
     * AI预览中支持展示的属性:"Event",事件;"Type",报警类型;"PeopleNum",人数;"Percent",百分比
    */
    public BYTE_ARRAY_16[]  szFeatureList = new BYTE_ARRAY_16[32];
    /**
     * 属性列表数量
    */
    public int              nFeatureListNum;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public CFG_CROWD_DISTRI_MAP_RULE() {
        for(int i = 0; i < szFeatureList.length; i++){
            szFeatureList[i] = new BYTE_ARRAY_16();
        }
    }
}


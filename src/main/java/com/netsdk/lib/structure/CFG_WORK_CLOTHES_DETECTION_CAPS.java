package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 工装检测信息
*/
public class CFG_WORK_CLOTHES_DETECTION_CAPS extends NetSDKLib.SdkStructure
{
    /**
     * 鞋子支持的颜色列表个数
    */
    public int              nShoesMultiColorSupportListNum;
    /**
     * 鞋子支持的的检测款式列表个数
    */
    public int              nShoesMultTypeSupportListNum;
    /**
     * 眼镜支持的的检测款式列表个数
    */
    public int              nGlassesMultTypeSupportListNum;
    /**
     * 支持独立配置检测灵敏度的工装属性列表个数
    */
    public int              nSupportSensitivityListNum;
    /**
     * 鞋子支持的颜色
    */
    public BYTE_ARRAY_16[]  szShoesMultiColorSupportList = new BYTE_ARRAY_16[32];
    /**
     * 鞋子支持的颜色
    */
    public BYTE_ARRAY_16[]  szShoesMultTypeSupportList = new BYTE_ARRAY_16[32];
    /**
     * 鞋子支持的颜色
    */
    public BYTE_ARRAY_16[]  szGlassesMultTypeSupportList = new BYTE_ARRAY_16[32];
    /**
     * 鞋子支持的颜色
    */
    public BYTE_ARRAY_16[]  szSupportSensitivityList = new BYTE_ARRAY_16[32];
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public CFG_WORK_CLOTHES_DETECTION_CAPS() {
        for(int i = 0; i < szShoesMultiColorSupportList.length; i++){
            szShoesMultiColorSupportList[i] = new BYTE_ARRAY_16();
        }
        for(int i = 0; i < szShoesMultTypeSupportList.length; i++){
            szShoesMultTypeSupportList[i] = new BYTE_ARRAY_16();
        }
        for(int i = 0; i < szGlassesMultTypeSupportList.length; i++){
            szGlassesMultTypeSupportList[i] = new BYTE_ARRAY_16();
        }
        for(int i = 0; i < szSupportSensitivityList.length; i++){
            szSupportSensitivityList[i] = new BYTE_ARRAY_16();
        }
    }
}


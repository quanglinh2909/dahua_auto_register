package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 大模型能力
*/
public class CFG_CROWD_LLM_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 人数检测范围
    */
    public int[]            nLargeNumLimit = new int[2];
    /**
     * 大模型下是否支持打伞检测
    */
    public int              bSupportUmbrella;
    /**
     * 大模型是否支持全局灵敏度
    */
    public int              bSupportGlobalSensitivity;
    /**
     * 是否支持大模型下AI预览
    */
    public int              bSupportAiView;
    /**
     * 是否支持大模型
    */
    public int              bSupport;
    /**
     * 是否支持码流显示大模型信息
    */
    public int              bSupportOSDDisplay;
    /**
     * 大模型的使能控制策略:0,不支持控制;1,支持控制;2,支持控制
    */
    public int              nEnableCtrl;
    /**
     * 支持的大模型列表
    */
    public BYTE_ARRAY_64[]  szModelList = new BYTE_ARRAY_64[32];
    /**
     * 支持的大模型列表数量
    */
    public int              nModelListNum;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public CFG_CROWD_LLM_INFO() {
        for(int i = 0; i < szModelList.length; i++){
            szModelList[i] = new BYTE_ARRAY_64();
        }
    }
}


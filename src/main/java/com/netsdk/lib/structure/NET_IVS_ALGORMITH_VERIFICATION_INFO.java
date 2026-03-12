package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * IVS算法验证信息
*/
public class NET_IVS_ALGORMITH_VERIFICATION_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 负样本内容描述列表
    */
    public BYTE_ARRAY_512[] szNegativeContent = new BYTE_ARRAY_512[8];
    /**
     * 正样本内容描述
    */
    public byte[]           szAlarmContent = new byte[512];
    /**
     * 归一化阈值
    */
    public int              nAuxSimilarity;
    /**
     * 相似度阈值
    */
    public int              nSimilarity;
    /**
     * 负样本内容描述列表个数
    */
    public int              nNegativeContentNum;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[252];

    public NET_IVS_ALGORMITH_VERIFICATION_INFO() {
        for(int i = 0; i < szNegativeContent.length; i++){
            szNegativeContent[i] = new BYTE_ARRAY_512();
        }
    }
}


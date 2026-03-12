package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 大图信息
*/
public class NET_GLOBAL_SCENE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 在上传图片数据中的图片序号, ~0u：未知
    */
    public int              nIndexInData;
    /**
     * 大小
    */
    public int              nLength;
    /**
     * 偏移
    */
    public int              nOffset;
    /**
     * 图片宽度
    */
    public int              nWidth;
    /**
     * 图片高度
    */
    public int              nHeight;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[60];

    public NET_GLOBAL_SCENE_INFO() {
    }
}


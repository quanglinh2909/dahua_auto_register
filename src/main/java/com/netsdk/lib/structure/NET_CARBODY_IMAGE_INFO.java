package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 车身图片信息
*/
public class NET_CARBODY_IMAGE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 图片文件路径
    */
    public byte[]           szFilePath = new byte[128];
    /**
     * 图片文件大小，单位:字节
    */
    public int              nLength;
    /**
     * 图片偏移字节数
    */
    public int              nOffset;
    /**
     * 预留字段
    */
    public byte[]           szReserved = new byte[248];

    public NET_CARBODY_IMAGE_INFO() {
    }
}


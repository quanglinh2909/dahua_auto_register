package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 视频浓缩能力
*/
public class CFG_SUPPORT_VIDEO_COMPRESS_ENCODECAP extends NetSDKLib.SdkStructure
{
    /**
     * 视频浓缩能力数组，4个元素，对应主码流、辅1、辅2、辅3
    */
    public int[]            bSupport = new int[4];
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[64];

    public CFG_SUPPORT_VIDEO_COMPRESS_ENCODECAP() {
    }
}


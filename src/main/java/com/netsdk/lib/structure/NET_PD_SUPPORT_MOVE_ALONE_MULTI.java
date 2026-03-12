package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 是否支持多通道自定义标题支持单块拖动
*/
public class NET_PD_SUPPORT_MOVE_ALONE_MULTI extends NetSDKLib.SdkStructure
{
    /**
     * false或无此字段，表示不支持；true，表示支持
    */
    public int              bSupport;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[252];

    public NET_PD_SUPPORT_MOVE_ALONE_MULTI() {
    }
}


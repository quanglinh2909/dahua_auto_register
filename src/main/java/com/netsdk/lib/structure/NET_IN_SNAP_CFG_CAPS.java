package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 获取设备编码配置对应能力输入参数
*/
public class NET_IN_SNAP_CFG_CAPS extends NetSDKLib.SdkStructure
{
    /**
     * 通道号(起始通道0)
    */
    public int              nChannelId;
    /**
     * 保留
    */
    public byte[]           bReserved = new byte[1024];

    public NET_IN_SNAP_CFG_CAPS() {
    }
}


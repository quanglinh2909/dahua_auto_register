package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 对应CLIENT_StartSearchDevicesEx接口TTLV回调
*/
public class DEVICE_NET_INFO_TTLV extends NetSDKLib.SdkStructure
{
    public byte[]           szMac = new byte[32];
    /**
     * 参见结构体定义 {@link com.netsdk.lib.structure.NET_PG4_DEVICE_INFO}
    */
    public NET_PG4_DEVICE_INFO stBaseInfo = new NET_PG4_DEVICE_INFO();
    /**
     * 发起搜索的本地IP
    */
    public byte[]           szLocalIP = new byte[64];
    /**
     * 扩展字段
    */
    public byte[]           szReserved = new byte[1984];

    public DEVICE_NET_INFO_TTLV() {
    }
}


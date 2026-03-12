package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 雷达和相机的网络数据交互方式
*/
public class NET_STJ77D5_RADAR_NET_PARAM extends NetSDKLib.SdkStructure
{
    /**
     * 网络雷达的IP地址(v4/v6)
    */
    public byte[]           szServerIP = new byte[40];
    /**
     * 网络雷达的配置端口号
    */
    public int              nServerConfigPort;
    /**
     * 网络雷达的数据端口号
    */
    public int              nServerDataPort;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[32];

    public NET_STJ77D5_RADAR_NET_PARAM() {
    }
}


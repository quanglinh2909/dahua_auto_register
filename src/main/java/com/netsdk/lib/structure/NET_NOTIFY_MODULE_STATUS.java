package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 工作状态信息
*/
public class NET_NOTIFY_MODULE_STATUS extends NetSDKLib.SdkStructure
{
    /**
     * 工作状态,"Normal":正常工作;"Abnormal":不正常工作
    */
    public byte[]           szState = new byte[32];
    /**
     * 预留字段
    */
    public byte[]           szResvered = new byte[1024];

    public NET_NOTIFY_MODULE_STATUS() {
    }
}


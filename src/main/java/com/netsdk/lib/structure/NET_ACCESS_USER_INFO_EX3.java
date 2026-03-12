package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 扩展用户信息V3
*/
public class NET_ACCESS_USER_INFO_EX3 extends NetSDKLib.SdkStructure
{
    /**
     * 工作说明
    */
    public byte[]           szWorkInfo = new byte[1024];
    /**
     * 验证方式,0:同设备方式,1:自定义
    */
    public int[]            nVerifyType = new int[256];
    /**
     * 个人解锁方式.
    */
    public int[]            nPersonalMethod = new int[256];
    /**
     * 验证方式数组数量
    */
    public int              nVerifyTypeNum;
    /**
     * 个人解锁方式数组数量
    */
    public int              nPersonalMethodNum;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[18424];

    public NET_ACCESS_USER_INFO_EX3() {
    }
}


package com.netsdk.lib.structure;

import com.netsdk.lib.NetSDKLib;

/**
 * @author 251823
 * @description 区间测速信息
 * @date 2022/12/07 20:58:01
 */
public class NET_REGION_INFO extends NetSDKLib.SdkStructure {
	/**
	 * 驶入时间
	 */
    public NET_TIME         stuDriveInTime = new NET_TIME();
	/**
	 * 驶出时间
	 */
    public NET_TIME         stuDriveOutTime = new NET_TIME();
    /**
     * 区间名称
    */
    public byte[]           szName = new byte[64];
    /**
     * 区间代码
    */
    public byte[]           szCode = new byte[32];
    /**
     * 区间距离,单位 米
    */
    public int              nDistance;
    /**
     * 区间通行时间,单位 秒
    */
    public int              nThroughTime;
    /**
     * 区间限速，区间内速度的上限和下限，单位 km/h
    */
    public int[]            nSpeedLimit = new int[2];
    /**
     * 驶入速度，单位 km/h
    */
    public int              nDriveInSpeed;
    /**
     * 驶出速度，单位 km/h
    */
    public int              nDriveOutSpeed;
    /**
     * 驶入路口
    */
    public byte[]           szDriveInAddr = new byte[64];
    /**
     * 驶出路口
    */
    public byte[]           szDriveOutAddr = new byte[64];
	/**
	 * 保留字节
	 */
    public byte[]           bReserved = new byte[776];

	public NET_REGION_INFO() {
	}
}


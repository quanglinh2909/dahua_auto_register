package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 云台控制坐标,速度单元
*/
public class PTZ_LOCATION_SPEED_UNIT extends NetSDKLib.SdkStructure
{
    /**
     * 云台水平角速度的真实值,无范围限定(超过云台最大速度时以云台最大速度移动),左为负、右为正,1000代表10°/s，扩大100倍表示
    */
    public int              nSpeedX;
    /**
     * 云台垂直角速度的真实值,无范围限定(超过云台最大速度时以云台最大速度移动),上为负、下为正,1000代表10°/s，扩大100倍表示
    */
    public int              nSpeedY;
    /**
     * 预留32字节
    */
    public byte[]           szReserve = new byte[32];

    public PTZ_LOCATION_SPEED_UNIT() {
    }
}


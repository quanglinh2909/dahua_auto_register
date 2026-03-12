package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 川速雷达配置
*/
public class NET_TRANS_RADAR_CONFIG extends NetSDKLib.SdkStructure
{
    /**
     * 雷达覆盖起始车道号,1-8,0表示无此字段
    */
    public int              nStartLane;
    /**
     * 雷达覆盖车道数,范围1-8
    */
    public int              nLaneNumber;
    /**
     * 工作模式,-1:未知,0:持续发送模式,1:触发发送模式,2:车尾触发送数模式,3:应答模式,4:计量院送数模式
    */
    public int              nWorkMode;
    /**
     * 触发速度,单位km/h,范围1-240
    */
    public int              nTriggerSpeed;
    /**
     * 修正角度,单位度,范围0-45
    */
    public int              nAdjustAngle;
    /**
     * 灵敏度,范围11-200
    */
    public int              nSensitivity;
    /**
     * 过滤方向,-1:未知,0:不过滤方向,1:只输出来向,2:只输出去向
    */
    public int              nFilterDirection;
    /**
     * 车速数据格式,-1:未知,0:单字节,1:双字节,2:ASCII格式
    */
    public int              nSpeedDataFormat;
    /**
     * 低速下限调整,1-20,单位为km/h
    */
    public int              nAdjustSpeed;
    /**
     * 超速报警阈值,单位为km/h
    */
    public int              nOverSpeed;
    /**
     * 判断大车小车的系数,范围1-240
    */
    public int              nCarSizeAdapt;
    /**
     * 统计周期,单位s,范围0-240
    */
    public int              nPeriod;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_TRANS_RADAR_CONFIG() {
    }
}


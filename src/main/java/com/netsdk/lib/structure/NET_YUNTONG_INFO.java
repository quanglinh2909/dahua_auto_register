package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 云瞳平台的部分图片信息
*/
public class NET_YUNTONG_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 号牌状态, -1:未知, 0:正常, 1:遮挡, 2:脏污、损坏, 3:倾斜,  4:前后号牌不一致
    */
    public int              nPlateStatus;
    /**
     * 号牌状态, -1:未知, 0:非进出京及内外环路, 1:进京, 2:出京, 3:内环, 4:外环
    */
    public int              nDirectionType;
    /**
     * 检测车道的限速值，单位km/h
    */
    public int              nLaneSpeedLimit;
    /**
     * 信号输出车道匹配, -1:未知, 0:全部车道, 1:左转, 2:直行, 3:右转, 4:直行左转, 5:直行右转
    */
    public int              nRedLightLaneType;
    /**
     * 起拍速度值
    */
    public byte[]           szSnapSpeed = new byte[32];
    /**
     * 红灯结束时间
    */
    public byte[]           szRedLightOverTime = new byte[32];
    /**
     * 禁行时间段
    */
    public byte[]           szNoPassingTimeSection = new byte[256];
    /**
     * 车辆品牌，字符串，字符串内容为纯数字
    */
    public byte[]           szVehicleText = new byte[32];
    /**
     * 车辆子品牌，字符串，字符串内容为纯数字
    */
    public byte[]           szVehicleSubText = new byte[32];
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[512];

    public NET_YUNTONG_INFO() {
    }
}


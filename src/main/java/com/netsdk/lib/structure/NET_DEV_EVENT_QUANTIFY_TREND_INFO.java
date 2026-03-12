package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 事件类型 EVENT_IVS_QUANTIFY_TREND（量化指标异常趋势报警事件）对应的数据块描述信息
*/
public class NET_DEV_EVENT_QUANTIFY_TREND_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 通道号
    */
    public int              nChannelID;
    /**
     * 0：脉冲，1：开始，2：停止
    */
    public int              nAction;
    /**
     * 扩展协议字段,参见结构体定义 {@link com.netsdk.lib.structure.NET_EVENT_INFO_EXTEND}
    */
    public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND();
    /**
     * 事件发生的时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME_EX}
    */
    public NetSDKLib.NET_TIME_EX stuUTC = new NetSDKLib.NET_TIME_EX();
    /**
     * 事件编号
    */
    public int              nEventID;
    /**
     * 相对事件时间戳，单位毫秒
    */
    public double           dbPTS;
    /**
     * 事件名称
    */
    public byte[]           szName = new byte[128];
    /**
     * 突变报警类型，"Energy": 能量值 "Temperature": 温度
    */
    public byte[]           szType = new byte[16];
    /**
     * 均值波动次数超限报警信息,参见结构体定义 {@link com.netsdk.lib.structure.NET_VOLATILITY_ALARM_INFO}
    */
    public NET_VOLATILITY_ALARM_INFO stuVolatilityAlarm = new NET_VOLATILITY_ALARM_INFO();
    /**
     * 均值斜率超限报警信息,参见结构体定义 {@link com.netsdk.lib.structure.NET_SLOPE_ALARM_INFO}
    */
    public NET_SLOPE_ALARM_INFO stuSlopeAlarm = new NET_SLOPE_ALARM_INFO();
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_DEV_EVENT_QUANTIFY_TREND_INFO() {
    }
}


package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 事件类型 EVENT_IVS_QUANTIFY_MUTATION（量化指标异常突变报警事件）对应的数据块描述信息
*/
public class NET_DEV_EVENT_QUANTIFY_MUTATION_INFO extends NetSDKLib.SdkStructure
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
     * 异常突变方向，"Up": 向上 "Down": 向下
    */
    public byte[]           szDirection = new byte[16];
    /**
     * 报警前量化均值
    */
    public float            fAverageValue;
    /**
     * 报警时的采样量化值
    */
    public float            fAlarmValue;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_DEV_EVENT_QUANTIFY_MUTATION_INFO() {
    }
}


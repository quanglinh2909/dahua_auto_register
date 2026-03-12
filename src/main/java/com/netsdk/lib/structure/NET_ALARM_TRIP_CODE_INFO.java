package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 事件类型 DH_ALARM_TRIP_CODE (行程码事件)对应的数据块描述信息
*/
public class NET_ALARM_TRIP_CODE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 通道号
    */
    public int              nChannelID;
    /**
     * 0:脉冲,1:开始, 2:停止
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
     * 类型，0未知，1绿码，2黄码，3红码
    */
    public int              nType;
    /**
     * 事件名称
    */
    public byte[]           szName = new byte[128];
    /**
     * 智能事件所属大类
    */
    public byte[]           szClass = new byte[16];
    /**
     * 时间戳
    */
    public double           dbPTS;
    /**
     * 事件编号，用来唯一标志一个事件
    */
    public int              nEventID;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_ALARM_TRIP_CODE_INFO() {
    }
}


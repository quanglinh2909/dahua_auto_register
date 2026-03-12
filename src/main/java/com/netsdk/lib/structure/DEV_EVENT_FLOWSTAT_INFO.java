package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 事件类型EVENT_IVS_FLOWSTAT(流量统计事件)对应的数据块描述信息
*/
public class DEV_EVENT_FLOWSTAT_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 通道号
    */
    public int              nChannelID;
    /**
     * 事件名称
    */
    public byte[]           szName = new byte[128];
    /**
     * 字节对齐
    */
    public byte[]           bReserved1 = new byte[4];
    /**
     * 时间戳(单位是毫秒)
    */
    public double           PTS;
    /**
     * 事件发生的时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME_EX}
    */
    public NetSDKLib.NET_TIME_EX UTC = new NetSDKLib.NET_TIME_EX();
    /**
     * 事件ID
    */
    public int              nEventID;
    /**
     * 从左边穿越的人的个数
    */
    public int              nNumberLeft;
    /**
     * 从右边穿越的人的个数
    */
    public int              nNumberRight;
    /**
     * 设置的上限
    */
    public int              nUpperLimit;
    /**
     * 事件对应文件信息,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_EVENT_FILE_INFO}
    */
    public NetSDKLib.NET_EVENT_FILE_INFO stuFileInfo = new NetSDKLib.NET_EVENT_FILE_INFO();
    /**
     * 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
    */
    public byte             bEventAction;
    public byte[]           byReserved = new byte[2];
    /**
     * 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
    */
    public byte             byImageIndex;
    /**
     * 抓图标志(按位),具体见NET_RESERVED_COMMON
    */
    public int              dwSnapFlagMask;
    /**
     * 事件触发累计次数
    */
    public int              nOccurrenceCount;
    /**
     * 事件公共扩展字段结构体,参见结构体定义 {@link com.netsdk.lib.structure.NET_EVENT_INFO_EXTEND}
    */
    public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND();
    /**
     * 保留字节,留待扩展.
    */
    public byte[]           bReserved = new byte[972];

    public DEV_EVENT_FLOWSTAT_INFO() {
    }
}


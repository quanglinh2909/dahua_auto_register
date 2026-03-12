package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 事件类型 EVENT_IVS_BURNING_WARNING 对应的数据块描述信息
*/
public class NET_DEV_EVENT_BURNING_WARNING_INFO extends NetSDKLib.SdkStructure
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
     * 报警点云台位成员个数
    */
    public int              nPositionNum;
    /**
     * 报警点云台位置,是一个数组，每个成员是int类型, 第一个参数是水平坐标，范围[0,3599]，表示0度到359.9度，度数扩大10倍表示。, 第二个参数是垂直坐标，范围[-1800,1800]，表示-180.0度到+180.0度，度数扩大10倍表示。, 第三个参数是放大参数，范围[0,127]，表示最小倍到最大倍的变倍位置
    */
    public int[]            nPosition = new int[3];
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_DEV_EVENT_BURNING_WARNING_INFO() {
    }
}


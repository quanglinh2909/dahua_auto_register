package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 事件类型 EVENT_IVS_INTELLITASK_CUSTOM_EVENT (智能组合自定义报警事件)对应的数据块描述信息
*/
public class NET_INTELLITASK_CUSTOM_EVENT_INFO extends NetSDKLib.SdkStructure
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
     * 自定义报警id
    */
    public int              nAlarmID;
    /**
     * 事件名称
    */
    public byte[]           szName = new byte[128];
    /**
     * 编排信息,参见结构体定义 {@link com.netsdk.lib.structure.NET_FLOWTASK_INFO}
    */
    public NET_FLOWTASK_INFO stuFlowTaskInfo = new NET_FLOWTASK_INFO();
    /**
     * 图片信息数组,参见结构体定义 {@link com.netsdk.lib.structure.NET_IMAGE_INFO_EX3}
    */
    public Pointer          pstuImageInfo;
    /**
     * 图片信息个数
    */
    public int              nImageInfoNum;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_INTELLITASK_CUSTOM_EVENT_INFO() {
    }
}


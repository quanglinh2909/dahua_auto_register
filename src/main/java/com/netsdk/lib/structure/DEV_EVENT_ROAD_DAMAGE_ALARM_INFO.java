package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 事件类型 EVENT_IVS_ROAD_DAMAGE (道路损坏报警事件)对应的数据块描述信息
*/
public class DEV_EVENT_ROAD_DAMAGE_ALARM_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 通道号
    */
    public int              nChannelID;
    /**
     * 事件类型 0:脉冲,1:开始, 2:停止
    */
    public int              nAction;
    /**
     * 事件名称
    */
    public byte[]           szName = new byte[128];
    /**
     * 智能事件所属大类
    */
    public byte[]           szClass = new byte[16];
    /**
     * GroupID事件组ID，同一物体抓拍过程内GroupID相同
    */
    public int              nGroupID;
    /**
     * CountInGroup一个事件组内的抓拍张数
    */
    public int              nCountInGroup;
    /**
     * IndexInGroup一个事件组内的抓拍序号，从1开始
    */
    public int              nIndexInGroup;
    /**
     * 事件时间毫秒数
    */
    public int              nUTCMS;
    /**
     * 相对事件时间戳,(单位是毫秒)
    */
    public double           dbPTS;
    /**
     * 事件发生的时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME_EX}
    */
    public NetSDKLib.NET_TIME_EX stuUTC = new NetSDKLib.NET_TIME_EX();
    /**
     * 事件ID
    */
    public int              nEventID;
    /**
     * 图片信息数组,参见结构体定义 {@link com.netsdk.lib.structure.NET_IMAGE_INFO_EX2}
    */
    public Pointer          pstuImageInfo;
    /**
     * 图片信息个数0
    */
    public int              nImageInfoNum;
    /**
     * 检测到的物体信息有效个数，最大值为64
    */
    public int              nObjectsNum;
    /**
     * 检测到的物体信息，内存由NetSDK申请释放,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_MSG_OBJECT_EX2}
    */
    public Pointer          pstuObjects;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1016-NetSDKLib.POINTERSIZE*2];

    public DEV_EVENT_ROAD_DAMAGE_ALARM_INFO() {
    }
}


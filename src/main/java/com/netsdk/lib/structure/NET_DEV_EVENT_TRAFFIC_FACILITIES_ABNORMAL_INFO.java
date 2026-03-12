package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 事件类型 EVENT_IVS_TRAFFIC_FACILITIES_ABNORMAL (交安设施异常检测事件)对应的数据块描述信息
*/
public class NET_DEV_EVENT_TRAFFIC_FACILITIES_ABNORMAL_INFO extends NetSDKLib.SdkStructure
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
     * 事件名称
    */
    public byte[]           szName = new byte[128];
    /**
     * 智能事件所属大类
    */
    public byte[]           szClass = new byte[32];
    /**
     * 相对事件时间戳,单位毫秒
    */
    public double           dbPTS;
    /**
     * 事件编号，用来唯一标志一个事件
    */
    public int              nEventID;
    /**
     * 预置点编号,从1开始
    */
    public int              nPresetID;
    /**
     * 检测到的物体信息,参见结构体定义 {@link com.netsdk.lib.structure.NET_TRAFFIC_FACILITIES_ABNORMAL_OBJECTS}
    */
    public NET_TRAFFIC_FACILITIES_ABNORMAL_OBJECTS[] stuObjects = new NET_TRAFFIC_FACILITIES_ABNORMAL_OBJECTS[16];
    /**
     * 检测到的物体信息个数
    */
    public int              nObjectsNum;
    /**
     * 图片类型, 0-未知, 1-原始图, 2-合成图（底图+原始图）
    */
    public int              nPictureType;
    /**
     * 智能事件规则编号
    */
    public int              nRuleID;
    /**
     * 全景广角图在上传图片数据中的图片序号
    */
    public int              nIndexInDataBySceneImage;
    /**
     * 事件触发的预置点名称
    */
    public byte[]           szPresetName = new byte[64];
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_DEV_EVENT_TRAFFIC_FACILITIES_ABNORMAL_INFO() {
        for(int i = 0; i < stuObjects.length; i++){
            stuObjects[i] = new NET_TRAFFIC_FACILITIES_ABNORMAL_OBJECTS();
        }
    }
}


package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 事件类型 EVENT_IVS_GAS_TANK_DETECTION(煤气罐检测事件)对应的数据块描述信息
*/
public class NET_DEV_EVENT_GAS_TANK_DETECTION_INFO extends NetSDKLib.SdkStructure
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
    public byte[]           szClass = new byte[128];
    /**
     * 智能事件规则编号
    */
    public int              nRuleID;
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
     * 相对事件时间戳,(单位是毫秒)
    */
    public double           dbPTS;
    /**
     * 事件发生的时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME_EX}
    */
    public NetSDKLib.NET_TIME_EX stuUTC = new NetSDKLib.NET_TIME_EX();
    /**
     * 仅用于字节对齐,无实际意义
    */
    public byte[]           byReserved = new byte[4];
    /**
     * 事件时间毫秒数
    */
    public int              nUTCMS;
    /**
     * 事件ID
    */
    public int              nEventID;
    /**
     * 检测目标的物体信息,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_MSG_OBJECT_EX2}
    */
    public NetSDKLib.NET_MSG_OBJECT_EX2 stuObject = new NetSDKLib.NET_MSG_OBJECT_EX2();
    /**
     * 检测目标的物体信息数组,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_MSG_OBJECT_EX2}
    */
    public NetSDKLib.NET_MSG_OBJECT_EX2[] stuObjects = new NetSDKLib.NET_MSG_OBJECT_EX2[100];
    /**
     * 检测到的物体个数(配合stuObjects使用)
    */
    public int              nObjectNum;
    /**
     * 检测区个数
    */
    public int              nDetectRegionNum;
    /**
     * 检测区,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_POINT}
    */
    public NetSDKLib.NET_POINT[] stuDetectRegion = new NetSDKLib.NET_POINT[20];
    /**
     * 规则被触发生次数
    */
    public int              nCount;
    /**
     * 事件级别,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_EVENT_LEVEL}
    */
    public int              emEventLevel;
    /**
     * 事件触发的预置点号，从1开始,没有该字段，表示预置点未知
    */
    public int              nPresetID;
    /**
     * 事件触发的预置点名称
    */
    public byte[]           szPresetName = new byte[64];
    /**
     * GPS信息,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_GPS_STATUS_INFO}
    */
    public NetSDKLib.NET_GPS_STATUS_INFO stuGPSStatusInfo = new NetSDKLib.NET_GPS_STATUS_INFO();
    /**
     * 全景广角图,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.SCENE_IMAGE_INFO}
    */
    public NetSDKLib.SCENE_IMAGE_INFO stuSceneImage = new NetSDKLib.SCENE_IMAGE_INFO();
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_DEV_EVENT_GAS_TANK_DETECTION_INFO() {
        for(int i = 0; i < stuObjects.length; i++){
            stuObjects[i] = new NetSDKLib.NET_MSG_OBJECT_EX2();
        }
        for(int i = 0; i < stuDetectRegion.length; i++){
            stuDetectRegion[i] = new NetSDKLib.NET_POINT();
        }
    }
}


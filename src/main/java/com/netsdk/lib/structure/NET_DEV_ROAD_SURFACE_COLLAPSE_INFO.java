package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 事件类型 EVENT_IVS_ROAD_SURFACE_COLLAPSE（路面塌陷事件）对应的数据块描述信息
*/
public class NET_DEV_ROAD_SURFACE_COLLAPSE_INFO extends NetSDKLib.SdkStructure
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
     * 事件组ID
    */
    public int              nGroupID;
    /**
     * 一个事件组内的抓拍张数
    */
    public int              nCountInGroup;
    /**
     * 一个事件组内的抓拍序号，从1开始
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
     * 时间戳
    */
    public double           dbPTS;
    /**
     * 事件码，用于事件确认
    */
    public int              nEventID;
    /**
     * 预置点编号，从1开始
    */
    public int              nPresetID;
    /**
     * 图片类型，0-原始图，1-合成图，~0u-未知
    */
    public int              nPictureType;
    /**
     * 检测到的物体信息有效个数
    */
    public int              nObjectsNum;
    /**
     * 检测到的物体信息,参见结构体定义 {@link com.netsdk.lib.structure.NET_ROAD_SURFACE_COLLAPSE_OBJECT_INFO}
    */
    public NET_ROAD_SURFACE_COLLAPSE_OBJECT_INFO[] stuObjects = new NET_ROAD_SURFACE_COLLAPSE_OBJECT_INFO[16];
    /**
     * 事件触发的预置点名称
    */
    public byte[]           szPresetName = new byte[64];
    /**
     * 智能事件规则编号
    */
    public int              nRuleID;
    /**
     * 在上传图片数据中的图片序号，~0u：未知
    */
    public int              nIndexInDataInSceneImage;
    /**
     * 图片信息有效个数
    */
    public int              nImageInfoNum;
    /**
     * 图片信息数组，内存由NetSDK申请释放,参见结构体定义 {@link com.netsdk.lib.structure.NET_IMAGE_INFO_EX3}
    */
    public Pointer          pstuImageInfo;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1020-NetSDKLib.POINTERSIZE];

    public NET_DEV_ROAD_SURFACE_COLLAPSE_INFO() {
        for(int i = 0; i < stuObjects.length; i++){
            stuObjects[i] = new NET_ROAD_SURFACE_COLLAPSE_OBJECT_INFO();
        }
    }
}


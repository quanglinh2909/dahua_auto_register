package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 事件类型 EVENT_IVS_VEHICLE_POSTURE_DETECTION 对应的数据块描述信息
*/
public class NET_DEV_EVENT_VEHICLE_POSTURE_DETECTION_INFO extends NetSDKLib.SdkStructure
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
     * 事件名称
    */
    public byte[]           szName = new byte[128];
    /**
     * 智能事件所属大类
    */
    public byte[]           szClass = new byte[16];
    /**
     * 相对事件时间戳，单位毫秒
    */
    public double           dbPTS;
    /**
     * 事件触发的预置点号
    */
    public int              nPresetID;
    /**
     * 规则区域数量
    */
    public int              nDetectRegionNum;
    /**
     * 规则区域信息,参见结构体定义 {@link com.netsdk.lib.structure.NET_POINT_EX}
    */
    public NET_POINT_EX[]   stuDetectRegion = new NET_POINT_EX[20];
    /**
     * 触发模式:Vehicle",按车辆数模式;"Percent",百分比模式
    */
    public byte[]           szTriggerMode = new byte[32];
    /**
     * 拥挤程度等级名称
    */
    public byte[]           szVehicleGroupName = new byte[128];
    /**
     * 拥挤程度等级
    */
    public int              nVehicleRankResult;
    /**
     * 检测到的规则区域内的车辆数
    */
    public int              nVehicleDetectNum;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_DEV_EVENT_VEHICLE_POSTURE_DETECTION_INFO() {
        for(int i = 0; i < stuDetectRegion.length; i++){
            stuDetectRegion[i] = new NET_POINT_EX();
        }
    }
}


package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 事件类型EVENT_IVS_VEHICLELIMIT_DETECTION(停车场场景下停车车辆上限报警)对应的数据块描述信息
*/
public class DEV_EVENT_VEHICLELIMIT_DETECTION_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 通道号
    */
    public int              nChannelID;
    /**
     * 1:开始 2:停止
    */
    public int              nAction;
    /**
     * 事件名称
    */
    public byte[]           szName = new byte[128];
    /**
     * 时间戳(单位是毫秒)
    */
    public double           PTS;
    /**
     * 事件发生的时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME_EX}
    */
    public NetSDKLib.NET_TIME_EX UTC = new NetSDKLib.NET_TIME_EX();
    /**
     * 事件对应文件信息,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_EVENT_FILE_INFO}
    */
    public NetSDKLib.NET_EVENT_FILE_INFO stuFileInfo = new NetSDKLib.NET_EVENT_FILE_INFO();
    /**
     * 智能事件所属大类,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_CLASS_TYPE}
    */
    public int              emClassType;
    /**
     * 事件ID
    */
    public int              nEventID;
    /**
     * 智能事件规则编号，用于标示哪个规则触发的事件
    */
    public int              nRuleID;
    /**
     * 事件触发的预置点号，从1开始
    */
    public int              nPresetID;
    /**
     * 事件触发的预置名称
    */
    public byte[]           szPresetName = new byte[64];
    /**
     * 车辆数量
    */
    public int              nVehicleQuantity;
    /**
     * stuDetectRegion中有效坐标点个数
    */
    public int              nDetectRegionNum;
    /**
     * 发生报警的区域坐标,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_POINT}
    */
    public NetSDKLib.NET_POINT[] stuDetectRegion = new NetSDKLib.NET_POINT[32];
    /**
     * 车位类型,参见枚举定义 {@link com.netsdk.lib.enumeration.EM_SPACE_TYPE}
    */
    public int              emSpaceType;
    /**
     * 更新配置信息的个数
    */
    public int              nUpdateInfoNum;
    /**
     * 更新配置信息,每个数组代表一条更新信息,参见结构体定义 {@link com.netsdk.lib.structure.NET_UPDATE_INFO}
    */
    public NET_UPDATE_INFO[] stuUpdateInfo = new NET_UPDATE_INFO[20];
    /**
     * 预留字节
    */
    public byte[]           byReserved = new byte[1024];

    public DEV_EVENT_VEHICLELIMIT_DETECTION_INFO() {
        for(int i = 0; i < stuDetectRegion.length; i++){
            stuDetectRegion[i] = new NetSDKLib.NET_POINT();
        }
        for(int i = 0; i < stuUpdateInfo.length; i++){
            stuUpdateInfo[i] = new NET_UPDATE_INFO();
        }
    }
}


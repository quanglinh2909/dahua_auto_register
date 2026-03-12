package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 人体历史库以图搜图主动上报的候选人信息
*/
public class NET_VEHICLEHISTORY_CANDIDATE extends NetSDKLib.SdkStructure
{
    /**
     * 相似度
    */
    public int              nSimilarity;
    /**
     * 抓拍通道号
    */
    public int              nChannelID;
    /**
     * 历史库人体信息,参见结构体定义 {@link com.netsdk.lib.structure.NET_HISTORY_TRAFFIC_CAR_INFO}
    */
    public NET_HISTORY_TRAFFIC_CAR_INFO stuHistoryTrafficCar = new NET_HISTORY_TRAFFIC_CAR_INFO();
    /**
     * 查询人员出现的时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuTime = new NetSDKLib.NET_TIME();
    /**
     * 人体全景图,参见结构体定义 {@link com.netsdk.lib.structure.NET_FACE_IMAGE_INFO}
    */
    public NET_FACE_IMAGE_INFO stuSceneImage = new NET_FACE_IMAGE_INFO();
    /**
     * 历史库中人员出现的时间(UTC时间格式),参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuTimeRealUTC = new NetSDKLib.NET_TIME();
    /**
     * stuTimeRealUTC是否有效
    */
    public int              bIsTimeRealUTCValid;
    /**
     * 目标时间记录是否有效
    */
    public int              bTimeRecordVaild;
    /**
     * 唯一标识一个事件
    */
    public long             nEventUUID;
    /**
     * 目标时间记录,参见结构体定义 {@link com.netsdk.lib.structure.NET_VEHICLEHISTORY_CANDIDATE_TIME_RECORD}
    */
    public NET_VEHICLEHISTORY_CANDIDATE_TIME_RECORD stuTimeRecord = new NET_VEHICLEHISTORY_CANDIDATE_TIME_RECORD();
    /**
     * 事件关联ID
    */
    public byte[]           szSourceID = new byte[32];
    /**
     * 预留字节数
    */
    public byte[]           szReserved = new byte[516];

    public NET_VEHICLEHISTORY_CANDIDATE() {
    }
}


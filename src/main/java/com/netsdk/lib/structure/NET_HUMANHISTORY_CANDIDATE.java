package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 人体历史库以图搜图主动上报的候选人信息
*/
public class NET_HUMANHISTORY_CANDIDATE extends NetSDKLib.SdkStructure
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
     * 历史库人体信息,参见结构体定义 {@link com.netsdk.lib.structure.NET_HISTORY_HUMAN_INFO}
    */
    public NET_HISTORY_HUMAN_INFO stuHistoryHuman = new NET_HISTORY_HUMAN_INFO();
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
     * 人员出现的地点(通常就是摄像机安装地点)
    */
    public byte[]           szAddress = new byte[260];
    /**
     * 目标时间记录是否有效
    */
    public int              bTimeRecordVaild;
    /**
     * 唯一标识一个事件
    */
    public long             nEventUUID;
    /**
     * 目标时间记录,参见结构体定义 {@link com.netsdk.lib.structure.NET_HUMANHISTORY_CANDIDATE_TIME_RECORD}
    */
    public NET_HUMANHISTORY_CANDIDATE_TIME_RECORD stuTimeRecord = new NET_HUMANHISTORY_CANDIDATE_TIME_RECORD();
    /**
     * 事件关联ID
    */
    public byte[]           szSourceID = new byte[32];
    /**
     * 预留字节
    */
    public byte[]           bReserved = new byte[256];

    public NET_HUMANHISTORY_CANDIDATE() {
    }
}


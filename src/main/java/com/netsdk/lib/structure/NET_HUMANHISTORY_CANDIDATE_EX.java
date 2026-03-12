package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 人体历史库以图搜图主动上报的候选人信息
*/
public class NET_HUMANHISTORY_CANDIDATE_EX extends NetSDKLib.SdkStructure
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
     * 目标图片文件路径
    */
    public byte[]           szSceneImagePicFilePath = new byte[256];
    /**
     * 目标时间记录,参见结构体定义 {@link com.netsdk.lib.structure.NET_HUMANHISTORY_CANDIDATE_TIME_RECORD}
    */
    public NET_HUMANHISTORY_CANDIDATE_TIME_RECORD stuTimeRecord = new NET_HUMANHISTORY_CANDIDATE_TIME_RECORD();
    /**
     * 历史库人体信息,参见结构体定义 {@link com.netsdk.lib.structure.NET_HISTORY_HUMAN_INFO}
    */
    public NET_HISTORY_HUMAN_INFO stuHistoryHuman = new NET_HISTORY_HUMAN_INFO();
    /**
     * 目标时间记录是否有效
    */
    public int              bTimeRecordVaild;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[260];

    public NET_HUMANHISTORY_CANDIDATE_EX() {
    }
}


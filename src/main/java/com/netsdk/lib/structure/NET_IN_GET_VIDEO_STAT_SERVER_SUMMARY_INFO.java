package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_GetVideoStatServerSummary 接口输入参数
*/
public class NET_IN_GET_VIDEO_STAT_SERVER_SUMMARY_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 视频通道号
    */
    public int              nChannel;
    /**
     * 子类
    */
    public byte[]           szSubClassID = new byte[32];
    /**
     * 人数统计类型:ManNumDetection\QueueDetection时表示区域人数统计,NumberStat表示进出人数统计,为空时默认获取NumberStat进出人数统计
    */
    public byte[]           szRuleName = new byte[32];
    /**
     * 区域ID,获取不同区域ID的区域内人数
    */
    public int              nAreaID;

    public NET_IN_GET_VIDEO_STAT_SERVER_SUMMARY_INFO() {
        this.dwSize = this.size();
    }
}


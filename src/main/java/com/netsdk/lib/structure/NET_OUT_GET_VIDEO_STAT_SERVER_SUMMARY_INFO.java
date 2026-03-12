package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_GetVideoStatServerSummary 接口输出参数
*/
public class NET_OUT_GET_VIDEO_STAT_SERVER_SUMMARY_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 人数统计摘要信息,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_VIDEOSTAT_SUMMARY}
    */
    public NetSDKLib.NET_VIDEOSTAT_SUMMARY stuSummaryInfo = new NetSDKLib.NET_VIDEOSTAT_SUMMARY();

    public NET_OUT_GET_VIDEO_STAT_SERVER_SUMMARY_INFO() {
        this.dwSize = this.size();
    }
}


package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 监管类事件查询 ( CLIENT_FindFileEx + DH_FILE_QUERY_INTELLI_PRISON )
*/
public class NET_MEDIAFILE_INTELLI_PRISON_PARAM extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 通道号
    */
    public int              nChannelID;
    /**
     * 通道号数组有效个数
    */
    public int              nChannelsNum;
    /**
     * 通道号数组
    */
    public int[]            nChannels = new int[256];
    /**
     * 起始时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuStartTime = new NetSDKLib.NET_TIME();
    /**
     * 结束时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuEndTime = new NetSDKLib.NET_TIME();
    /**
     * 文件类型,参见枚举定义 {@link com.netsdk.lib.enumeration.EM_FINDFILE_TYPE}
    */
    public int[]            emFileType = new int[8];
    /**
     * 文件类型个数, 为0表示查询任意类型
    */
    public int              nFileTypeNum;
    /**
     * 视频码流 0-未知 1-主码流 2-辅码流1 3-辅码流2 4-辅码流3 5-辅码流4 6-辅码流5 7-辅码流6 8-辅码流7 9-辅码流8 10-辅码流9 11-辅码流10 12-辅码流11 13-辅码流12
    */
    public int              nVideoStream;
    /**
     * 事件类型列表,参见智能分析事件类型
    */
    public int[]            nEventLists = new int[256];
    /**
     * 事件总数
    */
    public int              nEventCount;
    /**
     * 标志总数
    */
    public int              nFlagCount;
    /**
     * 录像或抓图文件标志, 不设置标志表示查询所有文件,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_RECORD_SNAP_FLAG_TYPE}
    */
    public int[]            emFlagLists = new int[128];
    /**
     * UTC开始时间(标准UTC时间), 与stuEndTimeRealUTC配对使用，与(stuStartTime, stuEndTime)互斥,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuStartTimeRealUTC = new NetSDKLib.NET_TIME();
    /**
     * UTC结束时间(标准UTC时间), 与stuStartTimeRealUTC配对使用，与(stuStartTime, stuEndTime)互斥,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuEndTimeRealUTC = new NetSDKLib.NET_TIME();
    /**
     * 为TRUE表示仅下发stuStartTimeRealUTC和stuEndTimeRealUTC(不下发stuStartTime, stuEndTime), 为FALSE表示仅下发stuStartTime, stuEndTime(不下发stuStartTimeRealUTC和stuEndTimeRealUTC)
    */
    public int              bOnlySupportRealUTC;
    /**
     * 事件类型个数
    */
    public int              nEventTypeNum;
    /**
     * 事件类型
    */
    public BYTE_ARRAY_32[]  szEventType = new BYTE_ARRAY_32[16];
    /**
     * 支持标记类型个数
    */
    public int              nMaskTypeNum;
    /**
     * 支持标记类型, 0: 正报 1: 误报 2: 未处理 3: 重复正报 4: 重复误报 5: 待定，需要二次确认 6: 经验库过滤 7: 删除 8：疑似误报 9：漏报
    */
    public int[]            nMaskType = new int[10];

    public NET_MEDIAFILE_INTELLI_PRISON_PARAM() {
        this.dwSize = this.size();
        for(int i = 0; i < szEventType.length; i++){
            szEventType[i] = new BYTE_ARRAY_32();
        }
    }
}


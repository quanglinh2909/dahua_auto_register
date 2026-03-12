package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 智能分析查询 ( CLIENT_FindFileEx + DH_FILE_QUERY_STEREO_BEHAVIOR )
*/
public class NET_MEDIAFILE_STEREO_BEHAVIOR_PARAM extends NetSDKLib.SdkStructure
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
     * 立体行为分析事件, 支持事件"TumbleDetection", "StayDetection", "DistanceDetection", "FightDetection", "ManNumDetection", "ClimbDetection"
    */
    public byte[]           szEvent = new byte[32];
    /**
     * 按照开始时间的排序方式, "Ascent": 升序 "Descent": 降序
    */
    public byte[]           szOrder = new byte[16];
    /**
     * 组合智能任务中产生的事件个数
    */
    public int              nEventUUIDsNum;
    /**
     * 组合智能任务中产生的事件
    */
    public BYTE_ARRAY_64[]  szEventUUIDs = new BYTE_ARRAY_64[64];

    public NET_MEDIAFILE_STEREO_BEHAVIOR_PARAM() {
        this.dwSize = this.size();
        for(int i = 0; i < szEventUUIDs.length; i++){
            szEventUUIDs[i] = new BYTE_ARRAY_64();
        }
    }
}


package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 监管类事件查询结果 ( CLIENT_FindNextFileEx + DH_FILE_QUERY_INTELLI_PRISON )
*/
public class NET_MEDIA_INTELLI_PRISON_INFO extends NetSDKLib.SdkStructure
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
     * 起始时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuStartTime = new NetSDKLib.NET_TIME();
    /**
     * 结束时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuEndTime = new NetSDKLib.NET_TIME();
    /**
     * 文件路径
    */
    public byte[]           szFilePath = new byte[256];
    /**
     * 文件类型,0:任意类型, 1:jpg图片, 2:dav文件
    */
    public int              nMediaType;
    /**
     * 磁盘号
    */
    public int              nDisk;
    /**
     * 簇号
    */
    public int              nCluster;
    /**
     * 精确定位号Hint
    */
    public int              nRepeat;
    /**
     * 精确定位号Hint(另一种表示), 表示在簇中的图片序号(与Repeat互斥)
    */
    public int              nPicIndex;
    /**
     * 精确定位号Hint
    */
    public int              nPartition;
    /**
     * 视频码流 0-未知 1-主码流 2-辅码流1 3-辅码流2 4-辅码流3 5-辅码流4 6-辅码流5 7-辅码流6 8-辅码流7 9-辅码流8 10-辅码流9 11-辅码流10 12-辅码流11 13-辅码流12
    */
    public int              nVideoStream;
    /**
     * 事件总数
    */
    public int              nEventCount;
    /**
     * 事件类型列表,参见智能分析事件类型
    */
    public int[]            nEventLists = new int[256];
    /**
     * 录像或抓图文件标志, 不设置标志表示查询所有文件,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_RECORD_SNAP_FLAG_TYPE}
    */
    public int[]            emFlagLists = new int[128];
    /**
     * 标志总数
    */
    public int              nFlagCount;
    /**
     * 为TRUE表示仅stuStartTimeRealUTC和stuEndTimeRealUTC有效(仅使用stuStartTimeRealUTC和stuEndTimeRealUTC), 为FALSE表示仅stuStartTime和stuEndTime有效(仅使用stuStartTime和stuEndTime)
    */
    public int              bRealUTC;
    /**
     * UTC开始时间(标准UTC时间), 与stuEndTimeRealUTC配对使用,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuStartTimeRealUTC = new NetSDKLib.NET_TIME();
    /**
     * UTC结束时间(标准UTC时间), 与stuStartTimeRealUTC配对使用,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuEndTimeRealUTC = new NetSDKLib.NET_TIME();
    /**
     * 事件类型
    */
    public byte[]           szEvent = new byte[32];
    /**
     * 检测区,参见结构体定义 {@link com.netsdk.lib.structure.NET_POINT_EX}
    */
    public NET_POINT_EX[]   stuDetectRegion = new NET_POINT_EX[4];
    /**
     * 标记类型
    */
    public int              nMaskType;
    /**
     * 报警类型, 0：未知 1：小模型报警 2：大模型实时流报警 3：大模型二次研判报警
    */
    public int              nAlarmSourceType;

    public NET_MEDIA_INTELLI_PRISON_INFO() {
        this.dwSize = this.size();
        for(int i = 0; i < stuDetectRegion.length; i++){
            stuDetectRegion[i] = new NET_POINT_EX();
        }
    }
}


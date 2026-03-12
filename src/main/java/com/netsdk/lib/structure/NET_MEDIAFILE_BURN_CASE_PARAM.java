package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 刻录案件录像查找 ( CLIENT_FindFileEx + DH_FILE_QUERY_BURN_CASE )
*/
public class NET_MEDIAFILE_BURN_CASE_PARAM extends NetSDKLib.SdkStructure
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
     * 文件类型,参见枚举定义 {@link com.netsdk.lib.enumeration.EM_FINDFILE_TYPE}
    */
    public int[]            emFileType = new int[8];
    /**
     * 文件类型个数, 为0表示查询任意类型
    */
    public int              nFileTypeNum;
    /**
     * 事件总数
    */
    public int              nEventCount;
    /**
     * 事件类型列表,参见智能分析事件类型
    */
    public int[]            nEventLists = new int[256];
    /**
     * 刻录案件录像专用查询条件,参见结构体定义 {@link com.netsdk.lib.structure.NET_BURN_CASE_RECORD_FILTER_INFO}
    */
    public NET_BURN_CASE_RECORD_FILTER_INFO stuBurnCaseRecordFilter = new NET_BURN_CASE_RECORD_FILTER_INFO();
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

    public NET_MEDIAFILE_BURN_CASE_PARAM() {
        this.dwSize = this.size();
    }
}


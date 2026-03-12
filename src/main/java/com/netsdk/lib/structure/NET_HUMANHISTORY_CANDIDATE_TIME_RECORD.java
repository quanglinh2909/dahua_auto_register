package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 目标时间记录
*/
public class NET_HUMANHISTORY_CANDIDATE_TIME_RECORD extends NetSDKLib.SdkStructure
{
    /**
     * 目标进入时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuObjEnterTime = new NetSDKLib.NET_TIME();
    /**
     * 目标离开时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuObjLeaveTime = new NetSDKLib.NET_TIME();
    /**
     * 目标进入UTC时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuObjEnterTimeRealUTC = new NetSDKLib.NET_TIME();
    /**
     * 目标离开UTC时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuObjLeaveTimeRealUTC = new NetSDKLib.NET_TIME();
    /**
     * 视频记录信息，内存由NetSDK申请释放,参见结构体定义 {@link com.netsdk.lib.structure.NET_VIDEO_RECORD_INFO}
    */
    public Pointer          pstuVideoRecord;
    /**
     * 视频记录信息有效个数，最多10个
    */
    public int              nVideoRecordNum;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[60-NetSDKLib.POINTERSIZE];

    public NET_HUMANHISTORY_CANDIDATE_TIME_RECORD() {
    }
}


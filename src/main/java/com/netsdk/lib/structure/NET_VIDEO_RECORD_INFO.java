package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 目标时间记录
*/
public class NET_VIDEO_RECORD_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 通道号
    */
    public int              nChannel;
    /**
     * 录像路径
    */
    public byte[]           szFilePath = new byte[260];
    /**
     * 文件开始时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuStartTime = new NetSDKLib.NET_TIME();
    /**
     * 文件结束时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuEndTime = new NetSDKLib.NET_TIME();
    /**
     * 视频码流，"Main": 主码流 "Extra1": 辅码流1 "Extra2": 辅码流2 "Extra3": 辅码流3
    */
    public byte[]           szVideoStream = new byte[8];
    /**
     * 如果文件的开始结束时间超出指定时间段，返回按查询时间段截取过的文件长度。, 如果未发生过截取，则会等于Length长度，单位字节
    */
    public int              nCutLength;
    /**
     * 如果查询条件中指定的时间在文件内，此处Length表示整个文件长度，而不表示指定时间段的长度，单位字节
    */
    public int              nLength;
    /**
     * 当文件长度超过4G，CutLength无法支持，HighLength表示高32位
    */
    public int              nHighLength;
    /**
     * UTC时间偏移，文件中的本地时间相对于UTC时间的偏移量，单位秒
    */
    public int              nUTCOffset;
    /**
     * 录像文件的状态
    */
    public byte[]           szFileState = new byte[16];
    /**
     * 文件类型
    */
    public byte[]           szType = new byte[8];
    /**
     * 录像文件的存储目录
    */
    public byte[]           szWorkDir = new byte[256];
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_VIDEO_RECORD_INFO() {
    }
}


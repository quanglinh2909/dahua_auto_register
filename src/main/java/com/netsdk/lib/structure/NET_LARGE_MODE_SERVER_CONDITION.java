package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 查询过滤条件
*/
public class NET_LARGE_MODE_SERVER_CONDITION extends NetSDKLib.SdkStructure
{
    /**
     * 开始时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuStartTime = new NetSDKLib.NET_TIME();
    /**
     * 结束时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuEndTime = new NetSDKLib.NET_TIME();
    /**
     * 数据源,参见枚举定义 {@link com.netsdk.lib.enumeration.EM_LARGE_MODE_SERVER_DATA_SOURCE}
    */
    public int              emDataSource;
    /**
     * 事件名称个数
    */
    public int              nEventsNum;
    /**
     * 事件名称
    */
    public BYTE_ARRAY_32[]  szEvents = new BYTE_ARRAY_32[32];
    /**
     * 内容描述
    */
    public byte[]           szAlarmContent = new byte[512];
    /**
     * 通道
    */
    public int[]            nChannels = new int[512];
    /**
     * 通道个数
    */
    public int              nChannelsNum;
    /**
     * 是否下发相似度数组
    */
    public int              bUseSimilarityRange;
    /**
     * 相似度数组，是一个数组 [0] 表示最小值 [1] 表示最大值
    */
    public int[]            nSimilarityRange = new int[2];
    /**
     * 保留字段
    */
    public byte[]           szReserved = new byte[496];

    public NET_LARGE_MODE_SERVER_CONDITION() {
        for(int i = 0; i < szEvents.length; i++){
            szEvents[i] = new BYTE_ARRAY_32();
        }
    }
}


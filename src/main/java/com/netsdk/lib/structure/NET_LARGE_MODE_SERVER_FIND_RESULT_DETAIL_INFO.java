package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 细节信息
*/
public class NET_LARGE_MODE_SERVER_FIND_RESULT_DETAIL_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 抓图图片PictureID
    */
    public int              nPictureID;
    /**
     * 通道号
    */
    public int              nChannel;
    /**
     * 抓图时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuTime = new NetSDKLib.NET_TIME();
    /**
     * 抓图事件类型
    */
    public byte[]           szEvent = new byte[64];
    /**
     * 数据源,参见枚举定义 {@link com.netsdk.lib.enumeration.EM_LARGE_MODE_SERVER_DATA_SOURCE}
    */
    public int              emDataSource;
    /**
     * 与描述特征的相似度,百分比1~100
    */
    public int              nSimilarity;
    /**
     * 图片文件路径
    */
    public byte[]           szFilePath = new byte[260];
    /**
     * 智能事件所属业务大类，取值见VAClassEnum
    */
    public byte[]           szClass = new byte[32];
    /**
     * 算法名称
    */
    public byte[]           szAlgName = new byte[128];
    /**
     * 包围盒 矩形信息，8192坐标系,参见结构体定义 {@link com.netsdk.lib.structure.NET_RECT_EX}
    */
    public NET_RECT_EX      stuBoundingBox = new NET_RECT_EX();
    /**
     * 唯一标识一个事件，用于确定抓图的原始事件信息
    */
    public long             nEventUUID;
    /**
     * 自定义报警id
    */
    public int              nAlarmId;
    /**
     * 抓图事件ID
    */
    public int              nEventID;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_LARGE_MODE_SERVER_FIND_RESULT_DETAIL_INFO() {
    }
}


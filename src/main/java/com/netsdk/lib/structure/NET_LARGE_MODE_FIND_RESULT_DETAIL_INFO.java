package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 查询到的结果集合
*/
public class NET_LARGE_MODE_FIND_RESULT_DETAIL_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 抓图图片PictureID
    */
    public int              nPictureID;
    /**
     * 抓拍图片的通道号
    */
    public int              nChannel;
    /**
     * 抓图事件类型
    */
    public byte[]           szEvent = new byte[64];
    /**
     * 抓图时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuTime = new NetSDKLib.NET_TIME();
    /**
     * 抓图事件ID
    */
    public int              nEventID;
    /**
     * 数据源，0: 未知 1: 定时抓图 2: 事件抓图 3: 导入文件
    */
    public int              nDataSource;
    /**
     * 与描述特征的相似度，百分比1~100
    */
    public int              nSimilarity;
    /**
     * 图片文件路径
    */
    public byte[]           szFilePath = new byte[260];
    /**
     * 智能事件所属业务大类
    */
    public byte[]           szClass = new byte[32];
    /**
     * 算法名称
    */
    public byte[]           szAlgName = new byte[128];
    /**
     * 包围盒矩形信息，8192坐标系,参见结构体定义 {@link com.netsdk.lib.structure.NET_RECT_EX}
    */
    public NET_RECT_EX      stuBoundingBox = new NET_RECT_EX();
    /**
     * 唯一标识一个事件
    */
    public long             nEventUUID;
    /**
     * 事件触发的前端时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuEventTime = new NetSDKLib.NET_TIME();
    /**
     * 目标小图文件路径
    */
    public byte[]           szObjectUrl = new byte[256];
    /**
     * 检索文本UUID唯一标志
    */
    public byte[]           szContentUUID = new byte[48];
    /**
     * 自定义报警id
    */
    public int              nAlarmId;
    /**
     * 目标ID
    */
    public int              nObjectID;
    /**
     * 目标类型，0-未知 1-人 2-机动车 3-非机动车 4-动物 5-物品
    */
    public int              nObjectType;
    /**
     * 记录唯一编号
    */
    public int              nRecNo;
    /**
     * 是否被收藏
    */
    public int              bFavorite;
    /**
     * 越搜越准推理研判，0：未知 1：正报 2：误报
    */
    public int              nSecondJudge;
    /**
     * 越搜越准推理研判置信度
    */
    public int              nConfidence;
    /**
     * 预留字段
    */
    public byte[]           szReserved = new byte[1020];

    public NET_LARGE_MODE_FIND_RESULT_DETAIL_INFO() {
    }
}


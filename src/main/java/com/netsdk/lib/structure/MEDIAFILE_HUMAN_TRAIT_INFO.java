package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * DH_FILE_QUERY_HUMAN_TRAIT人体检测对应FINDNEXT查询返回参数
*/
public class MEDIAFILE_HUMAN_TRAIT_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 通道
    */
    public int              nChannel;
    /**
     * 开始时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stStartTime = new NetSDKLib.NET_TIME();
    /**
     * 结束时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stEndTime = new NetSDKLib.NET_TIME();
    /**
     * 文件路径
    */
    public byte[]           szFilePath = new byte[260];
    /**
     * 文件大小
    */
    public int              nFileSize;
    /**
     * 目标小图路径
    */
    public byte[]           szFacePath = new byte[260];
    /**
     * 目标小图对应的全景图路径
    */
    public byte[]           szFaceScenePath = new byte[260];
    /**
     * 人体小图路径
    */
    public byte[]           szHumanPath = new byte[260];
    /**
     * 人体小图对应的全景图路径
    */
    public byte[]           szHumanSceneImage = new byte[260];
    /**
     * 目标小图大小, 单位: 字节
    */
    public int              nFaceImageLength;
    /**
     * 目标小图对应的全景图大小, 单位: 字节
    */
    public int              nFaceSceneImageLength;
    /**
     * 人体小图大小, 单位: 字节
    */
    public int              nHumanImageLength;
    /**
     * 人体小图对应的全景图大小, 单位: 字节
    */
    public int              nHumanSceneImageLength;
    /**
     * 人体检测信息,参见结构体定义 {@link com.netsdk.lib.structure.HUMAN_TRAIT_INFO}
    */
    public HUMAN_TRAIT_INFO stuHumanTrait = new HUMAN_TRAIT_INFO();
    /**
     * 目标属性信息,参见结构体定义 {@link com.netsdk.lib.structure.NET_HUMAN_FACE_ATTRIBUTE}
    */
    public NET_HUMAN_FACE_ATTRIBUTE stuFaceAttribute = new NET_HUMAN_FACE_ATTRIBUTE();
    /**
     * 目标型心(不是包围盒中心), 0-8191相对坐标, 相对于小图,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_POINT}
    */
    public NetSDKLib.NET_POINT stuFaceCenter = new NetSDKLib.NET_POINT();
    /**
     * 人体型心(不是包围盒中心), 0-8191相对坐标, 相对于小图,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_POINT}
    */
    public NetSDKLib.NET_POINT stuHumanCenter = new NetSDKLib.NET_POINT();
    /**
     * 为TRUE表示仅stuStartTimeRealUTC和stuEndTimeRealUTC有效(仅使用stuStartTimeRealUTC和stuEndTimeRealUTC), 为FALSE表示仅stStartTime和stEndTime有效(仅使用stStartTime和stEndTime)
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
     * 离线补传字段，唯一标识一个事件
    */
    public byte[]           szEventUUIDStr = new byte[32];

    public MEDIAFILE_HUMAN_TRAIT_INFO() {
        this.dwSize = this.size();
    }
}


package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * DH_FILE_QUERY_HUMAN_TRAIT人体检测对应查询参数
*/
public class MEDIAFILE_HUMAN_TRAIT_PARAM extends NetSDKLib.SdkStructure
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
     * 上衣颜色,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_CLOTHES_COLOR}
    */
    public int[]            emCoatColor = new int[4];
    /**
     * 裤子颜色,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_CLOTHES_COLOR}
    */
    public int[]            emTrousersColor = new int[4];
    /**
     * 上衣类型,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_COAT_TYPE}
    */
    public int[]            emCoatType = new int[2];
    /**
     * 裤子类型,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_TROUSERS_TYPE}
    */
    public int[]            emTrousersType = new int[2];
    /**
     * 需要查询的上衣颜色个数
    */
    public int              nCoatColorNum;
    /**
     * 需要查询的裤子颜色个数
    */
    public int              nTrousersColorNum;
    /**
     * 需要查询的上衣类型个数
    */
    public int              nCoatTypeNum;
    /**
     * 需要查询的裤子类型个数
    */
    public int              nTrousersTypeNum;
    /**
     * 是否戴帽子,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_HAS_HAT}
    */
    public int              emHasHat;
    /**
     * 是否带包,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_HAS_BAG}
    */
    public int              emHasBag;
    /**
     * 性别,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_SEX_TYPE}
    */
    public int              emSex;
    /**
     * 头发样式,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_HAIR_STYLE}
    */
    public int              emHairStyle;
    /**
     * 查询年龄段的最小年龄
    */
    public int              nAgeMin;
    /**
     * 查询年龄段的最大年龄
    */
    public int              nAgeMax;
    /**
     * 为TRUE表示仅下发stuStartTimeRealUTC和stuEndTimeRealUTC(不下发stStartTime, stEndTime), 为FALSE表示仅下发stStartTime, stEndTime(不下发stuStartTimeRealUTC和stuEndTimeRealUTC)
    */
    public int              bOnlySupportRealUTC;
    /**
     * UTC开始时间(标准UTC时间), 与stuEndTimeRealUTC配对使用，与(stuStartTime, stuEndTime)互斥,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuStartTimeRealUTC = new NetSDKLib.NET_TIME();
    /**
     * UTC结束时间(标准UTC时间), 与stuStartTimeRealUTC配对使用，与(stuStartTime, stuEndTime)互斥,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuEndTimeRealUTC = new NetSDKLib.NET_TIME();
    /**
     * 是否打伞,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_HAS_UMBRELLA}
    */
    public int              emHasUmbrella;
    /**
     * 帽类型,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_CAP_TYPE}
    */
    public int              emCap;
    /**
     * 事件类型列表,参见智能分析事件类型
    */
    public int[]            nEventLists = new int[256];
    /**
     * 事件总数
    */
    public int              nEventCount;
    /**
     * 通道号数组有效个数
    */
    public int              nChannelsNum;
    /**
     * 通道号数组
    */
    public int[]            nChannels = new int[256];
    /**
     * 是否不下发 Age 字段
    */
    public int              bUnEnableSupportAge;
    /**
     * 是否不下发 HasBag 字段
    */
    public int              bUnEnableSupportHasBag;
    /**
     * 是否不下发 HasHat 字段
    */
    public int              bUnEnableSupportHasHat;

    public MEDIAFILE_HUMAN_TRAIT_PARAM() {
        this.dwSize = this.size();
    }
}


package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 查询到的人体检测相关信息
*/
public class HUMAN_TRAIT_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 上衣颜色,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_CLOTHES_COLOR}
    */
    public int              emCoatColor;
    /**
     * 裤子颜色,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_CLOTHES_COLOR}
    */
    public int              emTrousersColor;
    /**
     * 上衣类型,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_COAT_TYPE}
    */
    public int              emCoatType;
    /**
     * 裤子类型,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_TROUSERS_TYPE}
    */
    public int              emTrousersType;
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
     * 年龄
    */
    public int              nAge;
    /**
     * 头发样式,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_HAIR_STYLE}
    */
    public int              emHairStyle;
    /**
     * 是否打伞,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_HAS_UMBRELLA}
    */
    public int              emHasUmbrella;
    /**
     * 包类型,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_BAG_TYPE}
    */
    public int              emBagType;
    /**
     * 帽子类型,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_CAP_TYPE}
    */
    public int              emCapType;
    /**
     * 图片ID有效数据41位，用于目标关联,1-20 ,1.对自动采集对象,应使用在线视频图像信息采集设备/系统、分析设备/系统统一标识编码；,2.对人工采集对象,应使用对应的公安视频图像信息应用平台或其他公安信息系统统一标识编码，包括所有通过数据服务接口接入视图库的系统,21-22 ,表示视频图像信息基本对象的类型：01-视频片段 02-图像 03-文件 99-其他,23-36,视频图像信息基本对象生成时间， 精确到秒级：YYYYMMDDhhmmss，年月日时分秒 ,37-41 ,视频图像信息基本对象序号
    */
    public byte[]           szSourceID = new byte[42];
    /**
     * 字节对齐
    */
    public byte[]           szAlign = new byte[6];
    /**
     * 保留字段
    */
    public byte[]           byReserved = new byte[976];

    public HUMAN_TRAIT_INFO() {
    }
}


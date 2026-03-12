package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 智能卡信息
*/
public class NET_CHIP_USAGE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 指定子卡Id, 为All时表示获取所有智能卡的运行情况
    */
    public byte[]           szchip = new byte[16];
    /**
     * 智能卡功能状态,参见枚举定义 {@link com.netsdk.lib.enumeration.EM_CHIP_USAGE_FUNCTION_STATE}
    */
    public int              emFunctionalState;
    /**
     * 智能压缩功能运行使用情况,压缩能力利用百分比单位：%
    */
    public int              nCompressUsagePercent;
    /**
     * 智能分析功能运行使用情况,智能卡上正在进行智能分析的本地通道号
    */
    public int[]            nVideoChannel = new int[128];
    /**
     * 智能卡上正在进行智能分析的本地通道号个数
    */
    public int              nVideoChannelNum;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[512];

    public NET_CHIP_USAGE_INFO() {
    }
}


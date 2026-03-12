package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * ,**************************智能卡接口Start**************************************************,, CLIENT_GetChipUsageInfo 接口输入参数
*/
public class NET_IN_GET_CHIP_USAGE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 指定子卡Id, 为All时表示获取所有智能卡的运行情况
    */
    public byte[]           szchip = new byte[16];

    public NET_IN_GET_CHIP_USAGE_INFO() {
        this.dwSize = this.size();
    }
}


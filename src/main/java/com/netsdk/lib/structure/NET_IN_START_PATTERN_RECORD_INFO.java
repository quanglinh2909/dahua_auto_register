package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 开始模式记录 对应DH_EXTPTZ_START_PATTERN_RECORD枚举
*/
public class NET_IN_START_PATTERN_RECORD_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 模式名称
    */
    public byte[]           szName = new byte[64];
    /**
     * 模式编号
    */
    public int              nIndex;

    public NET_IN_START_PATTERN_RECORD_INFO() {
        this.dwSize = this.size();
    }
}


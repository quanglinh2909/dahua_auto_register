package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_BurnGetStateEx 接口输入参数
*/
public class NET_IN_BURN_GET_STATE_EX extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小，必须赋值
    */
    public int              dwSize;
    /**
     * 刻录会话编号
    */
    public int              nChannel;

    public NET_IN_BURN_GET_STATE_EX() {
        this.dwSize = this.size();
    }
}


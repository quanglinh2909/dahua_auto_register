package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_StartFindLargeModeServer 接口输入参数
*/
public class NET_IN_START_FIND_LARGE_MODE_SERVER extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 查询过滤条件,参见结构体定义 {@link com.netsdk.lib.structure.NET_LARGE_MODE_SERVER_CONDITION}
    */
    public NET_LARGE_MODE_SERVER_CONDITION stuCondition = new NET_LARGE_MODE_SERVER_CONDITION();

    public NET_IN_START_FIND_LARGE_MODE_SERVER() {
        this.dwSize = this.size();
    }
}


package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_RemoteRemoveFiles 接口输出参数
*/
public class NET_OUT_REMOTE_REMOVE_FILES extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_OUT_REMOTE_REMOVE_FILES() {
        this.dwSize = this.size();
    }
}


package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_AttachModuleStatus 接口出参
*/
public class NET_OUT_ATTACH_MODULE_STATUS extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;

    public NET_OUT_ATTACH_MODULE_STATUS() {
        this.dwSize = this.size();
    }
}


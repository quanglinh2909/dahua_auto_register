package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * CLIENT_AttachXRayPackageManualExport 接口入参
*/
public class NET_IN_ATTACH_XRAY_PACKAGE_MANUAL_EXPORT extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 字节对齐
    */
    public byte[]           szAlign = new byte[4];
    /**
     * 回调函数,参见回调函数定义 {@link com.netsdk.lib.NetSDKLib.fNotifyPackageManualExport}
    */
    public NetSDKLib.fNotifyPackageManualExport cbNotify;
    /**
     * 用户自定义参数
    */
    public Pointer          dwUser;

    public NET_IN_ATTACH_XRAY_PACKAGE_MANUAL_EXPORT() {
        this.dwSize = this.size();
    }
}


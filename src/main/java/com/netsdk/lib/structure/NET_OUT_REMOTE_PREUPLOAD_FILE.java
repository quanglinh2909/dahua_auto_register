package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_RemotePreUploadFile 接口输出参数(前端设备预上传文件)
*/
public class NET_OUT_REMOTE_PREUPLOAD_FILE extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 标志是否继续上传该文件。因如文件过大，设备可拒绝接收上传。
    */
    public int              bContinue2Upload;
    /**
     * 错误原因, 当bContinue2Upload为false时emType有效,参见枚举定义 {@link com.netsdk.lib.enumeration.EM_REASON_TYPE}
    */
    public int              emType;

    public NET_OUT_REMOTE_PREUPLOAD_FILE() {
        this.dwSize = this.size();
    }
}


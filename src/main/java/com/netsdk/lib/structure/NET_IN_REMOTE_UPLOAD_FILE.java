package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * CLIENT_StartRemoteUploadFile 接口输入参数(上传文件到前端设备)
*/
public class NET_IN_REMOTE_UPLOAD_FILE extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 通道号
    */
    public int              nChannel;
    /**
     * 源文件路径
    */
    public byte[]           szFileSrc = new byte[256];
    /**
     * 目标文件保存名称
    */
    public byte[]           szFileNameDst = new byte[128];
    /**
     * 目标文件夹路径：可为空字符串, 空时设备使用默认路径
    */
    public byte[]           szFolderDst = new byte[256];
    /**
     * 上传进度回调,参见回调函数定义 {@link com.netsdk.lib.NetSDKLib.fRemoteUploadFileCallBack}
    */
    public NetSDKLib.fRemoteUploadFileCallBack cbUploadFile;
    /**
     * 用户参数
    */
    public Pointer          dwUser;
    /**
     * 文件传输分包大小(字节): 0表示SDK默认分包
    */
    public int              nPacketLen;

    public NET_IN_REMOTE_UPLOAD_FILE() {
        this.dwSize = this.size();
    }
}


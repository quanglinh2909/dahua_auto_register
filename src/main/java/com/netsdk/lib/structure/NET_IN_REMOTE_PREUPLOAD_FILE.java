package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_RemotePreUploadFile 接口输入参数(前端设备预上传文件)
*/
public class NET_IN_REMOTE_PREUPLOAD_FILE extends NetSDKLib.SdkStructure
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
     * 源文件路径，用于计算文件大小，为空且nFileLength不为0时，使用nFileLength作为文件大小
    */
    public byte[]           szFileSrc = new byte[256];
    /**
     * 目标文件保存名称
    */
    public byte[]           szFileNameDst = new byte[128];
    /**
     * 目标文件夹路径：可为空字符串, 空时设备使用默认路径校验。
    */
    public byte[]           szFolderDst = new byte[256];
    /**
     * 文件大小，单位字节，szFileSrc为空时有效
    */
    public int              nFileLength;

    public NET_IN_REMOTE_PREUPLOAD_FILE() {
        this.dwSize = this.size();
    }
}


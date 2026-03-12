package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_RemoteRename 接口输入参数
*/
public class NET_IN_REMOTE_RENAME extends NetSDKLib.SdkStructure
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
     * 旧的文件名
    */
    public byte[]           szOldName = new byte[128];
    /**
     * 新的文件名
    */
    public byte[]           szNewName = new byte[128];

    public NET_IN_REMOTE_RENAME() {
        this.dwSize = this.size();
    }
}


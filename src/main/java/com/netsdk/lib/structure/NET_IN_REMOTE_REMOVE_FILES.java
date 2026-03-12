package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * CLIENT_RemoteRemoveFiles 接口输入参数
*/
public class NET_IN_REMOTE_REMOVE_FILES extends NetSDKLib.SdkStructure
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
     * 文件个数
    */
    public int              nFileNum;
    /**
     * 文件名数组指针
    */
    public Pointer          pszFileName;

    public NET_IN_REMOTE_REMOVE_FILES() {
        this.dwSize = this.size();
    }
}


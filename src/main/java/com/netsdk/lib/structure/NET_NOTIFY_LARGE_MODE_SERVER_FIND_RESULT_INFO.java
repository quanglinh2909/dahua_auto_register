package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 内容检索结果
*/
public class NET_NOTIFY_LARGE_MODE_SERVER_FIND_RESULT_INFO extends NetSDKLib.SdkStructure
{
    /**
     * Token值
    */
    public int              nToken;
    /**
     * 进度
    */
    public int              nProgress;
    /**
     * 当前满足条件的总数
    */
    public int              nCurrentCount;
    /**
     * 细节信息数组实际个数
    */
    public int              nDetailNum;
    /**
     * 细节信息,参见结构体定义 {@link com.netsdk.lib.structure.NET_LARGE_MODE_SERVER_FIND_RESULT_DETAIL_INFO}
    */
    public Pointer          pstuDetail;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_NOTIFY_LARGE_MODE_SERVER_FIND_RESULT_INFO() {
    }
}


package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * CLIENT_DoFindLargeModeServer 接口输出参数
*/
public class NET_OUT_DO_FIND_LARGE_MODE_SERVER extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 查询到的结果集合内存申请个数
    */
    public int              nMaxResultInfosNum;
    /**
     * 查询到的结果集合，内存由用户申请释放,参见结构体定义 {@link com.netsdk.lib.structure.NET_LARGE_MODE_FIND_RESULT_DETAIL_INFO}
    */
    public Pointer          pstuResultInfos;
    /**
     * 查询到的结果有效返回个数
    */
    public int              nRetResultInfosNum;

    public NET_OUT_DO_FIND_LARGE_MODE_SERVER() {
        this.dwSize = this.size();
    }
}


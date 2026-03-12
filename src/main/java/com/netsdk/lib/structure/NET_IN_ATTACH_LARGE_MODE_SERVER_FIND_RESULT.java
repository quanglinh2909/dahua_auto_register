package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * CLIENT_AttachLargeModeServerFindResult 输入参数
*/
public class NET_IN_ATTACH_LARGE_MODE_SERVER_FIND_RESULT extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * startFind 返回的Token值
    */
    public int              nToken;
    /**
     * 回调函数,参见回调函数定义 {@link com.netsdk.lib.NetSDKLib.fNotifyLargeModeFindResult}
    */
    public NetSDKLib.fNotifyLargeModeFindResult cbNotifyLargeModeFindResult;
    /**
     * 用户信息
    */
    public Pointer          dwUser;

    public NET_IN_ATTACH_LARGE_MODE_SERVER_FIND_RESULT() {
        this.dwSize = this.size();
    }
}


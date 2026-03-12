package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * CLIENT_AttachResultOfVehicleHistoryByPic 接口输入参数
*/
public class NET_IN_ATTACH_RESULT_VEHICLE_HISTORY_BYPIC extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 查询令牌, 由CLIENT_StartFindFaceRecognition返回
    */
    public int              nToken;
    /**
     * 订阅人体历史库以图搜图回调函数,参见回调函数定义 {@link com.netsdk.lib.NetSDKLib.fResultOfVehicleHistory}
    */
    public NetSDKLib.fResultOfVehicleHistory cbResultOfVehicleHistory;
    /**
     * 用户数据
    */
    public Pointer          dwUser;

    public NET_IN_ATTACH_RESULT_VEHICLE_HISTORY_BYPIC() {
        this.dwSize = this.size();
    }
}


package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_SetPtzMoveZoomFocusMapValue 接口输出参数
*/
public class NET_OUT_SET_PTZ_MOVE_ZOOM_FOCUS_MAP_VALUE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;

    public NET_OUT_SET_PTZ_MOVE_ZOOM_FOCUS_MAP_VALUE_INFO() {
        this.dwSize = this.size();
    }
}


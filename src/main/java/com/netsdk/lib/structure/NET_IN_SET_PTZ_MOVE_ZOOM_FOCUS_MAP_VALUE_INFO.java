package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_SetPtzMoveZoomFocusMapValue 接口输入参数
*/
public class NET_IN_SET_PTZ_MOVE_ZOOM_FOCUS_MAP_VALUE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 云台通道号,创建实例使用
    */
    public int              nChannel;
    /**
     * 镜头变倍映射值，通过获取云台状态获取的变倍映射值
    */
    public int              nZoomMapValue;
    /**
     * 通道号,通道号从0开始
    */
    public int              nChannelEx;
    /**
     * 镜头聚焦映射值，通过获取云台状态获取的聚焦映射值
    */
    public int              nFocusMapValue;

    public NET_IN_SET_PTZ_MOVE_ZOOM_FOCUS_MAP_VALUE_INFO() {
        this.dwSize = this.size();
    }
}


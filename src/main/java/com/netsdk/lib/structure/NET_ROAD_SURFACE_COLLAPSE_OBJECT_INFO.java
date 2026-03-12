package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 路面塌陷事件检测到的物体信息
*/
public class NET_ROAD_SURFACE_COLLAPSE_OBJECT_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 目标包围盒,参见结构体定义 {@link com.netsdk.lib.structure.NET_RECT_EX}
    */
    public NET_RECT_EX      stuBoundingBox = new NET_RECT_EX();
    /**
     * 目标ID
    */
    public int              nObjectID;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[252];

    public NET_ROAD_SURFACE_COLLAPSE_OBJECT_INFO() {
    }
}


package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 检测到的物体信息
*/
public class NET_TRAFFIC_FACILITIES_ABNORMAL_OBJECTS extends NetSDKLib.SdkStructure
{
    /**
     * 目标包围盒,8192坐标系,参见结构体定义 {@link com.netsdk.lib.structure.NET_RECT_EX}
    */
    public NET_RECT_EX      stuBoundingBox = new NET_RECT_EX();
    /**
     * 目标ID
    */
    public int              nObjectID;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[260];

    public NET_TRAFFIC_FACILITIES_ABNORMAL_OBJECTS() {
    }
}


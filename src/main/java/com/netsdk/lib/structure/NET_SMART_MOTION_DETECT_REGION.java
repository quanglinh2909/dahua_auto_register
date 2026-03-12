package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 智能动检检测区域信息
*/
public class NET_SMART_MOTION_DETECT_REGION extends NetSDKLib.SdkStructure
{
    /**
     * 检测区域名称
    */
    public byte[]           szName = new byte[128];
    /**
     * 智能动态检测对象,参见结构体定义 {@link com.netsdk.lib.structure.NET_SMART_MOTION_DETECT_OBJECT}
    */
    public NET_SMART_MOTION_DETECT_OBJECT stuObjectType = new NET_SMART_MOTION_DETECT_OBJECT();
    /**
     * 多边形位置,参见结构体定义 {@link com.netsdk.lib.structure.NET_POINT_EX}
    */
    public NET_POINT_EX[]   stuRegion = new NET_POINT_EX[20];
    /**
     * 多边形个数
    */
    public int              nRegionNum;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_SMART_MOTION_DETECT_REGION() {
        for(int i = 0; i < stuRegion.length; i++){
            stuRegion[i] = new NET_POINT_EX();
        }
    }
}


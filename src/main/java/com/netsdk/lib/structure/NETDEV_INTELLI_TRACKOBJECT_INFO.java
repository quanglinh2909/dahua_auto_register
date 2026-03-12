package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 手动跟踪目标结构体信息
*/
public class NETDEV_INTELLI_TRACKOBJECT_INFO extends NetSDKLib.SdkStructure
{
    public int              dwSize;
    /**
     * 物体ID,-1 表示web上框选的是智能帧上传框外任意位置物体,>=0表示框选的是智能帧上传的物体
    */
    public int              nObjectID;
    /**
     * 矩形范围,点的坐标归一化到[0,8191]区间,参见结构体定义 {@link com.netsdk.lib.structure.NET_RECT}
    */
    public NET_RECT         stuBound = new NET_RECT();
    /**
     * 跟踪持续时间，单位为秒
    */
    public int              nTrackTimeOut;
    /**
     * 静止等待时间，单位为秒
    */
    public int              nStaticTimeOut;
    /**
     * 用于传输手动控制跟踪时从外部（平台）传入的对象标识
    */
    public byte[]           szLinkTargetUUID = new byte[32];

    public NETDEV_INTELLI_TRACKOBJECT_INFO() {
        this.dwSize = this.size();
    }
}


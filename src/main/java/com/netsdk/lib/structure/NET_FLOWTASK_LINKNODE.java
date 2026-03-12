package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 关联事件数据信息
*/
public class NET_FLOWTASK_LINKNODE extends NetSDKLib.SdkStructure
{
    /**
     * 节点业务大类
    */
    public byte[]           szClass = new byte[64];
    /**
     * 节点事件Code
    */
    public byte[]           szCode = new byte[64];
    /**
     * 唯一标识一个事件
    */
    public long             nEventUUID;
    /**
     * 节点事件触发时间
    */
    public int              nUTC;
    /**
     * 在上传图片数据中的图片序号
    */
    public int              nSceneImageIndexInData;
    /**
     * 通道编号
    */
    public int              nChannel;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[260];

    public NET_FLOWTASK_LINKNODE() {
    }
}


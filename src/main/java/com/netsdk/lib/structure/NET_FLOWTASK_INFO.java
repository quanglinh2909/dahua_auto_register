package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 编排信息
*/
public class NET_FLOWTASK_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 编排Id
    */
    public byte[]           szFlowID = new byte[64];
    /**
     * 关联事件数据信息 来源于编排子事件,参见结构体定义 {@link com.netsdk.lib.structure.NET_FLOWTASK_LINKNODE}
    */
    public NET_FLOWTASK_LINKNODE[] stuLinkNode = new NET_FLOWTASK_LINKNODE[32];
    /**
     * 关联事件数据信息数量
    */
    public int              nLinkNodeNum;
    /**
     * 字节对齐
    */
    public byte[]           szReserved1 = new byte[4];
    /**
     * 智能编排应用名称
    */
    public byte[]           szFlowName = new byte[256];
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[512];

    public NET_FLOWTASK_INFO() {
        for(int i = 0; i < stuLinkNode.length; i++){
            stuLinkNode[i] = new NET_FLOWTASK_LINKNODE();
        }
    }
}


package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 事件类型 EVENT_IVS_POWERLINE_FOREIGN_DETECITON 对应数据
*/
public class DEV_EVENT_POWERLINE_FOREIGN_DETEC_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 通道号
    */
    public int              nChannelID;
    /**
     * 事件动作 1-开始. 2-结束
    */
    public int              nAction;
    /**
     * 事件名称
    */
    public byte[]           szName = new byte[128];
    /**
     * 时间戳(单位是毫秒)
    */
    public int              PTS;
    /**
     * 事件发生的时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME_EX}
    */
    public NetSDKLib.NET_TIME_EX UTC = new NetSDKLib.NET_TIME_EX();
    /**
     * 事件ID
    */
    public int              nEventID;
    /**
     * 智能事件所属大类,参见枚举定义 {@link com.netsdk.lib.NetSDKLib.EM_CLASS_TYPE}
    */
    public int              emClassType;
    /**
     * 智能事件规则编号, 缺省为0
    */
    public int              nRuleID;
    /**
     * 事件触发次数
    */
    public int              nCount;
    /**
     * 事件触发的预置点号, 缺省为0
    */
    public int              nPresetID;
    /**
     * 规则检测区域顶点数
    */
    public int              nDetectRegionNum;
    /**
     * 规则检测区域,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_POINT}
    */
    public NetSDKLib.NET_POINT[] stuDetectRegion = new NetSDKLib.NET_POINT[20];
    /**
     * 异物物体实际个数
    */
    public int              nForeignMatterNum;
    /**
     * 异物物体信息,参见结构体定义 {@link com.netsdk.lib.structure.FOREIGN_MATTER_OBJECT}
    */
    public Pointer          pForeignMatter;
    /**
     * 字节对齐
    */
    public Pointer          pReserved;
    /**
     * 保留
    */
    public byte[]           byReserved = new byte[1024];

    public DEV_EVENT_POWERLINE_FOREIGN_DETEC_INFO() {
        for(int i = 0; i < stuDetectRegion.length; i++){
            stuDetectRegion[i] = new NetSDKLib.NET_POINT();
        }
    }
}


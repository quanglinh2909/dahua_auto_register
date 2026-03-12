package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 智能球机控制输入参数
*/
public class NET_IN_CONTROL_INTELLITRACKER extends NetSDKLib.SdkStructure
{
    public int              dwSize;
    /**
     * 通道ID
    */
    public int              nChannelID;
    /**
     * 控制类型,参见枚举定义 {@link com.netsdk.lib.enumeration.NET_TRACKCONTROL_TYPE}
    */
    public int              emCtrlType;
    /**
     * 控制类型对应控制信息结构体指针,参见DH_TRACKCONTROL_TYPE定义。由用户申请内存
    */
    public Pointer          pCtrlInfo;
    /**
     * 控制信息结构体大小
    */
    public int              nMaxLen;
    /**
     * 等待超时时间
    */
    public int              nWaittime;

    public NET_IN_CONTROL_INTELLITRACKER() {
        this.dwSize = this.size();
    }
}


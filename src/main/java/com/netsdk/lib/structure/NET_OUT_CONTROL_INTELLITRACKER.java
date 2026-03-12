package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 智能球机控制输出参数
*/
public class NET_OUT_CONTROL_INTELLITRACKER extends NetSDKLib.SdkStructure
{
    public int              dwSize;

    public NET_OUT_CONTROL_INTELLITRACKER() {
        this.dwSize = this.size();
    }
}


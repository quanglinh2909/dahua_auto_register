package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_AttachRadarAISInfo接口出参
*/
public class NET_OUT_ATTACH_RADAR_AIS_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_OUT_ATTACH_RADAR_AIS_INFO() {
        this.dwSize = this.size();
    }
}


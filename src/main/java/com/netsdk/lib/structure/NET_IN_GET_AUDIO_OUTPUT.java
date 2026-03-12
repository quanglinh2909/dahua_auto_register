package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_GetSplitAudioOuput接口输入参数(获取音频输出模式)
*/
public class NET_IN_GET_AUDIO_OUTPUT extends NetSDKLib.SdkStructure
{
    public int              dwSize;
    /**
     * 通道号
    */
    public int              nChannel;

    public NET_IN_GET_AUDIO_OUTPUT() {
        this.dwSize = this.size();
    }
}


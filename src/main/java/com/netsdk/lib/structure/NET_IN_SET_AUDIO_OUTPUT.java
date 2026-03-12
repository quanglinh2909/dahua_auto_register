package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_SetSplitAudioOuput接口输入参数(设置音频输出模式)
*/
public class NET_IN_SET_AUDIO_OUTPUT extends NetSDKLib.SdkStructure
{
    public int              dwSize;
    /**
     * 通道号
    */
    public int              nChannel;
    /**
     * 音频输出模式,参见枚举定义 {@link com.netsdk.lib.enumeration.NET_AUDIO_OUTPUT_MODE}
    */
    public int              emMode;
    /**
     * 输出窗口号, emMode为DH_AUDIO_FORCE/DH_AUDIO_ENABLE_ONE/DH_AUDIO_DISABLE_ONE时有效, 指定输出音频的窗口号
    */
    public int              nWindow;

    public NET_IN_SET_AUDIO_OUTPUT() {
        this.dwSize = this.size();
    }
}


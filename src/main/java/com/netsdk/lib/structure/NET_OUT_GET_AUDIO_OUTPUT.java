package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * CLIENT_GetSplitAudioOuput接口输出参数(获取音频输出模式)
*/
public class NET_OUT_GET_AUDIO_OUTPUT extends NetSDKLib.SdkStructure
{
    public int              dwSize;
    /**
     * 音频输出模式,参见枚举定义 {@link com.netsdk.lib.enumeration.NET_AUDIO_OUTPUT_MODE}
    */
    public int              emMode;
    /**
     * 输出窗口号, emMode为DH_AUDIO_FORCE时有效
    */
    public int              nWindow;
    /**
     * 输出窗口号列表, emMode为DH_AUDIO_MULTI时有效, 用户分配内存,大小为sizeof(int)*nMaxMultiWindowCount
    */
    public Pointer          pMultiWindows;
    /**
     * 输出窗口号列表最大数量, 用户填写
    */
    public int              nMaxMultiWindowCount;
    /**
     * 输出窗口号数量, emMode为DH_AUDIO_MULTI时有效
    */
    public int              nRetMultiWindowCount;

    public NET_OUT_GET_AUDIO_OUTPUT() {
        this.dwSize = this.size();
    }
}


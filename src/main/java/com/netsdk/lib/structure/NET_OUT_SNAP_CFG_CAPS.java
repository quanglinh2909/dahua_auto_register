package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 抓图配置能力
*/
public class NET_OUT_SNAP_CFG_CAPS extends NetSDKLib.SdkStructure
{
    /**
     * 支持的视频分辨率信息
    */
    public int              nResolutionTypeNum;
    /**
     * 参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_RESOLUTION_INFO}
    */
    public NetSDKLib.NET_RESOLUTION_INFO[] stuResolutionTypes = new NetSDKLib.NET_RESOLUTION_INFO[64];
    /**
     * 支持的帧率信息
    */
    public int              dwFramesPerSecNum;
    /**
     * -25：25秒1帧；-24：24秒1帧；……	 	 	 , 0：无效；1：1秒1帧；2：1秒2帧；3：1秒3帧	 	 	 , 4：1秒4帧；5：1秒5帧；17：1秒17帧；18：1秒18帧	 	 	 , 19：1秒19帧；20：1秒20帧……
    */
    public int[]            nFramesPerSecList = new int[128];
    /**
     * 支持的画质信息
    */
    public int              dwQualityMun;
    /**
     * 1-6档（6质量最高）
    */
    public int[]            nQualityList = new int[32];
    /**
     * 模式,按位：第一位：定时；第二位：手动。
    */
    public int              dwMode;
    /**
     * 图片格式模式,按位：第一位：bmp；第二位：jpg。
    */
    public int              dwFormat;
    /**
     * 保留
    */
    public byte[]           bReserved = new byte[2048];

    public NET_OUT_SNAP_CFG_CAPS() {
        for(int i = 0; i < stuResolutionTypes.length; i++){
            stuResolutionTypes[i] = new NetSDKLib.NET_RESOLUTION_INFO();
        }
    }
}


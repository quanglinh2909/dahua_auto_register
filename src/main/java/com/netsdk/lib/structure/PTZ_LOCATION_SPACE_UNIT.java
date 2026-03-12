package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 云台控制坐标，持续移动
*/
public class PTZ_LOCATION_SPACE_UNIT extends NetSDKLib.SdkStructure
{
    /**
     * 云台水平坐标，范围[0,3599]，表示0度到359.9度，度数扩大10倍表示。（3600表示水平不定位）
    */
    public int              nPositionX;
    /**
     * 云台垂直坐标，范围[-1800,1800]，表示-180.0度到+180.0度，度数扩大10倍表示。（3600表示垂直不定位）
    */
    public int              nPositionY;
    /**
     * 云台真实倍率变倍参数，无范围限定(超过机芯最大倍率时只变倍至机芯最大倍率)，表示最小倍到最大倍的变倍位置，扩大10倍表示
    */
    public int              nZoom;
    /**
     * 预留32字节
    */
    public byte[]           szReserve = new byte[32];

    public PTZ_LOCATION_SPACE_UNIT() {
    }
}


package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * @beirf 雷达速度修正配置
*/
public class NET_RADAR_SPEED_CORRECTION extends NetSDKLib.SdkStructure
{
    /**
     * 速度区间起始值（包含），单位km/h
    */
    public int              nBegin;
    /**
     * 速度区间结束值（不包含），单位km/h
    */
    public int              nEnd;
    /**
     * 该区间速度修正值，单位km/h，可正可负
    */
    public int              nValue;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[68];

    public NET_RADAR_SPEED_CORRECTION() {
    }
}


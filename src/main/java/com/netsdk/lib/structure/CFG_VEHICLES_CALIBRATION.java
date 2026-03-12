package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 标定线段相关能力
*/
public class CFG_VEHICLES_CALIBRATION extends NetSDKLib.SdkStructure
{
    /**
     * 水平线段数量
    */
    public int              nHorizontalLines;
    /**
     * 垂直线段数量
    */
    public int              nVerticalLines;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public CFG_VEHICLES_CALIBRATION() {
    }
}


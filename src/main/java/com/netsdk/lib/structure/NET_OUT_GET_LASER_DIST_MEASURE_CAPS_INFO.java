package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_GetLaserDistMeasureCaps 接口输出参数
*/
public class NET_OUT_GET_LASER_DIST_MEASURE_CAPS_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 是否支持测距
    */
    public int              bSupport;
    /**
     * "Image": 图像测距, "Laser": 激光测距
    */
    public byte[]           szType = new byte[32];

    public NET_OUT_GET_LASER_DIST_MEASURE_CAPS_INFO() {
        this.dwSize = this.size();
    }
}


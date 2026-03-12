package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_FuelPowerCutoffCommand 接口输入参数
*/
public class NET_IN_FUEL_POWER_CUTOFF_COMMAND extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小，必须赋值
    */
    public int              dwSize;
    /**
     * 操作类型, 1:断油断电 2:恢复正常
    */
    public int              nOptType;

    public NET_IN_FUEL_POWER_CUTOFF_COMMAND() {
        this.dwSize = this.size();
    }
}


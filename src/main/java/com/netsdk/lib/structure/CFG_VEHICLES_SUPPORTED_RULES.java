package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 支持的规则
*/
public class CFG_VEHICLES_SUPPORTED_RULES extends NetSDKLib.SdkStructure
{
    /**
     * 道路拥堵规则,参见结构体定义 {@link com.netsdk.lib.structure.CFG_CONGESTION_SUPPORTED_RULES}
    */
    public CFG_CONGESTION_SUPPORTED_RULES stuCongestionDetection = new CFG_CONGESTION_SUPPORTED_RULES();
    /**
     * 停车上限规则,参见结构体定义 {@link com.netsdk.lib.structure.CFG_VEHICLE_LIMIT_SUPPORTED_RULES}
    */
    public CFG_VEHICLE_LIMIT_SUPPORTED_RULES stuVehicleLimitDetection = new CFG_VEHICLE_LIMIT_SUPPORTED_RULES();
    /**
     * 车辆态势规则,参见结构体定义 {@link com.netsdk.lib.structure.CFG_VEHICLE_POSTURE_SUPPORTED_RULES}
    */
    public CFG_VEHICLE_POSTURE_SUPPORTED_RULES stuVehiclePostureDetection = new CFG_VEHICLE_POSTURE_SUPPORTED_RULES();
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public CFG_VEHICLES_SUPPORTED_RULES() {
    }
}


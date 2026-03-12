package com.netsdk.lib.structure;


import com.netsdk.lib.NetSDKLib;

/**
 * @author 260611
 * @description 闸门控制输入参数(对应 EM_THINGS_SERVICE_TYPE_VALVECONTROL)
 * @date 2022/04/20 10:50:18
 */
public class NET_IN_THINGS_SERVICE_VALVECONTROL extends NetSDKLib.SdkStructure {
    /**
     * 结构体大小, 调用者必须初始化该字段
     */
    public int              dwSize;
    /**
     * 开关
     */
    public int              bSwitch;
    /**
     * 产品ID
     */
    public byte[]           szProductID = new byte[64];
    /**
     * 设备ID
     */
    public byte[]           szDeviceID = new byte[64];
    /**
     * 服务ID
    */
    public byte[]           szServiceID = new byte[64];
    /**
     * OnOff 开停
    */
    public int              bOnOffEnable;
    /**
     * 是否使用batchBtrlSwitch扩展信息,与bSwitch互斥,bSwitch字段无效
    */
    public int              bUseExtendInfo;
    /**
     * 设备列表信息
    */
    public BYTE_ARRAY_8[]   szDevList = new BYTE_ARRAY_8[96];
    /**
     * 设备列表个数
    */
    public int              nDevListNum;

    public NET_IN_THINGS_SERVICE_VALVECONTROL() {
        this.dwSize = this.size();
    }
}


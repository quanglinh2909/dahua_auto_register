package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_ControlMiscPower 输入参数
*/
public class NET_IN_CONTROL_MISC_POWER_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 控制外部电源状态具体信息数量
    */
    public int              nMiscPowerInfoNum;
    /**
     * 控制外部电源状态具体信息,参见结构体定义 {@link com.netsdk.lib.structure.NET_MISC_POWER_INFO}
    */
    public NET_MISC_POWER_INFO[] stuMiscPowerInfo = new NET_MISC_POWER_INFO[8];

    public NET_IN_CONTROL_MISC_POWER_INFO() {
        this.dwSize = this.size();
        for(int i = 0; i < stuMiscPowerInfo.length; i++){
            stuMiscPowerInfo[i] = new NET_MISC_POWER_INFO();
        }
    }
}


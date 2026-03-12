package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 人员定额消费配置，对应 NET_EM_CFG_USER_QUOTA_CONSUMPTION
*/
public class NET_CFG_USER_QUOTA_CONSUMPTION_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 数组个数
    */
    public int              nConfigNum;
    /**
     * 配置数组,参见结构体定义 {@link com.netsdk.lib.structure.NET_CFG_USER_QUOTA_CONSUMPTION_INFO_OBJECT}
    */
    public NET_CFG_USER_QUOTA_CONSUMPTION_INFO_OBJECT[] stuConfig = new NET_CFG_USER_QUOTA_CONSUMPTION_INFO_OBJECT[16];

    public NET_CFG_USER_QUOTA_CONSUMPTION_INFO() {
        this.dwSize = this.size();
        for(int i = 0; i < stuConfig.length; i++){
            stuConfig[i] = new NET_CFG_USER_QUOTA_CONSUMPTION_INFO_OBJECT();
        }
    }
}


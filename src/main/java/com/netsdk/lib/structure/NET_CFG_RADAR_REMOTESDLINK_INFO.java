package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 雷达远程球机联动使能配置(对应 NET_EM_CFG_RADAR_REMOTESDLINK)
*/
public class NET_CFG_RADAR_REMOTESDLINK_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 联动个数
    */
    public int              nLinkNum;
    /**
     * 远程球机联动使能信息,参见结构体定义 {@link com.netsdk.lib.structure.NET_REMOTESDLINK_INFO}
    */
    public NET_REMOTESDLINK_INFO[] stuLinkInfos = new NET_REMOTESDLINK_INFO[24];

    public NET_CFG_RADAR_REMOTESDLINK_INFO() {
        this.dwSize = this.size();
        for(int i = 0; i < stuLinkInfos.length; i++){
            stuLinkInfos[i] = new NET_REMOTESDLINK_INFO();
        }
    }
}


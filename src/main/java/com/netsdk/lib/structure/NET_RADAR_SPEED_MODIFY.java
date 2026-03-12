package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * @beirf 速度修正
*/
public class NET_RADAR_SPEED_MODIFY extends NetSDKLib.SdkStructure
{
    /**
     * 速度修正使能项
    */
    public int              bEnable;
    /**
     * 修正信息个数
    */
    public int              nModifyInfoNum;
    /**
     * 修正信息,参见结构体定义 {@link com.netsdk.lib.structure.NET_SPEED_MODIFY_INFO}
    */
    public NET_SPEED_MODIFY_INFO[] stuModifyInfo = new NET_SPEED_MODIFY_INFO[5];
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_RADAR_SPEED_MODIFY() {
        for(int i = 0; i < stuModifyInfo.length; i++){
            stuModifyInfo[i] = new NET_SPEED_MODIFY_INFO();
        }
    }
}


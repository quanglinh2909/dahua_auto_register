package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 场景边界限位信息
*/
public class NETDEV_INTELLI_SCENELIMIT_INFO extends NetSDKLib.SdkStructure
{
    public int              dwSize;
    /**
     * 场景编号
    */
    public int              nScene;
    /**
     * 0:上边界限位,1:下边界限位,2:左边界,3:右边界
    */
    public int              nType;

    public NETDEV_INTELLI_SCENELIMIT_INFO() {
        this.dwSize = this.size();
    }
}


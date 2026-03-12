package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_DestroyBackupTask接口输出参数
*/
public class NET_OUT_DESTROY_BACKUP_TASK_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;

    public NET_OUT_DESTROY_BACKUP_TASK_INFO() {
        this.dwSize = this.size();
    }
}


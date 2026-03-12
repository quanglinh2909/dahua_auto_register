package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_MandateBackupTask 接口输入参数
*/
public class NET_IN_MANDATE_BACKUP_TASK_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小，必须赋值
    */
    public int              dwSize;
    /**
     * 备份任务ID
    */
    public int              nTaskID;

    public NET_IN_MANDATE_BACKUP_TASK_INFO() {
        this.dwSize = this.size();
    }
}


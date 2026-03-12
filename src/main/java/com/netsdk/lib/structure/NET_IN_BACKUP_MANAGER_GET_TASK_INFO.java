package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_BackupManagerGetTaskInfo 接口输入参数
*/
public class NET_IN_BACKUP_MANAGER_GET_TASK_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小，必须赋值
    */
    public int              dwSize;
    /**
     * 备份任务组id, 0表示所有
    */
    public int              nGroup;

    public NET_IN_BACKUP_MANAGER_GET_TASK_INFO() {
        this.dwSize = this.size();
    }
}


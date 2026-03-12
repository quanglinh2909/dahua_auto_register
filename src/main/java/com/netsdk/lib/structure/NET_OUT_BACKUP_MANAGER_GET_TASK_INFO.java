package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_BackupManagerGetTaskInfo 接口输出参数
*/
public class NET_OUT_BACKUP_MANAGER_GET_TASK_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小，必须赋值
    */
    public int              dwSize;
    /**
     * 备份任务简要状态信息列表个数
    */
    public int              nTaskInfoNum;
    /**
     * 备份任务简要状态信息列表,参见结构体定义 {@link com.netsdk.lib.structure.NET_BACKUP_MANAGER_TASK_INFO}
    */
    public NET_BACKUP_MANAGER_TASK_INFO[] stuTaskInfo = new NET_BACKUP_MANAGER_TASK_INFO[32];

    public NET_OUT_BACKUP_MANAGER_GET_TASK_INFO() {
        this.dwSize = this.size();
        for(int i = 0; i < stuTaskInfo.length; i++){
            stuTaskInfo[i] = new NET_BACKUP_MANAGER_TASK_INFO();
        }
    }
}


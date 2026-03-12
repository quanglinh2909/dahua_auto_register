package com.netsdk.lib.structure;

import com.netsdk.lib.NetSDKLib;
import com.netsdk.lib.NetSDKLib.SdkStructure;
import com.sun.jna.Pointer;

/**
 * 
 * @author 119178
 * CLIENT_StartBackupTask接口输入参数
 * {@link NetSDKLib#CLIENT_StartBackupTask}
 */
public class NET_IN_START_BACKUP_TASK_INFO extends SdkStructure {
    public int              dwSize;
    public int              emSourceMode;                         //  源模式(EM_BACKUP_SOURCE_MODE)
    public int              emTargetMode;                         //  备份模式(EM_BACKUP_TARGET_MODE)
    public int              nSourceNum;                           //  备份源个数
    public Pointer          pstuSource;                           //  备份源,用户分配内存,大小为sizeof(NET_BACKUP_SOURCE)*nSourceNum
    public Pointer          pstuTarget;                           //  备份目的?用户分配内存,大小为sizeof(NET_BACKUP_TARGET)*nTargetNum
    public int              nTargetNum;                           //  备份目的个数
    public int              emFormat;                             //	期望备份成为的格式类型(EM_BACKUP_FORMAT)
    public int              nGroupID;                             //  任务组ID
    public int              bTakePlayer;                          //	是否带DH播放器,emFormat值dav时，需要DH播放器播放。
    /**
     * 播放器目标文件备份目录,参见结构体定义 {@link com.netsdk.lib.structure.NET_BACKUP_PLAYER}
    */
    public NET_BACKUP_PLAYER[] stuPlayer = new NET_BACKUP_PLAYER[32];
    /**
     * 播放器目标文件备份目录有效个数
    */
    public int              nPlayerNum;
    /**
     * 是否使用实例ID,CLIENT_StartBackupTaskEx使用时为TRUE
    */
    public int              bIsUseInstanceID;
    /**
     * 创建的实例ID,仅CLIENT_StartBackupTaskEx使用,CLIENT_CreateBackupTask的出参
    */
    public int              nInstanceID;

	public NET_IN_START_BACKUP_TASK_INFO(){
        this.dwSize = this.size();
    }
}


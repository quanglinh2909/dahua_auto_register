package com.netsdk.lib.enumeration;
import com.netsdk.lib.NetSDKLib;
/**
 * 物体动作支持类型
*/
public enum EM_OBJECT_ACTION
{
    /**
     * 未知动作
    */
    EM_OBJECT_ACTION_UNKNOWN(0, "未知动作"),
    /**
     * "Appear": 第一次出现在检测区域中, 或者跟踪时物理分离动作中尚未确认的新物体
    */
    EM_OBJECT_ACTION_APPEAR(1, "\"Appear\": 第一次出现在检测区域中, 或者跟踪时物理分离动作中尚未确认的新物体"),
    /**
     * "Move": 正在运动 物体被正常跟踪
    */
    EM_OBJECT_ACTION_MOVE(2, "\"Move\": 正在运动 物体被正常跟踪"),
    /**
     * "Stay": 物体停止运动这个物体不会在出现在下一帧物体列表中，物体开始移动后再恢复在列表中
    */
    EM_OBJECT_ACTION_STAY(3, "\"Stay\": 物体停止运动这个物体不会在出现在下一帧物体列表中，物体开始移动后再恢复在列表中"),
    /**
     * "Remove": 物体从原来的区域移除，或者被遮盖，或者跟踪失败
    */
    EM_OBJECT_ACTION_REMOVE(4, "\"Remove\": 物体从原来的区域移除，或者被遮盖，或者跟踪失败"),
    /**
     * "Disappear": 运动到跟踪区域之外，或者达到了算法跟踪物体的上限而被清除，消失的物体ID将不再出现
    */
    EM_OBJECT_ACTION_DISAPPERAR(5, "\"Disappear\": 运动到跟踪区域之外，或者达到了算法跟踪物体的上限而被清除，消失的物体ID将不再出现"),
    /**
     * "Split": 从其他物体中分离出来，可以用来检测物体遗留，关联ID表示从这个ID对应物体分离
    */
    EM_OBJECT_ACTION_SPLIT(6, "\"Split\": 从其他物体中分离出来，可以用来检测物体遗留，关联ID表示从这个ID对应物体分离"),
    /**
     * "Merge": 合并到其他物体，可以用来检查物体保全，关联ID表示合并到这个ID对相应的物体
    */
    EM_OBJECT_ACTION_MERGE(7, "\"Merge\": 合并到其他物体，可以用来检查物体保全，关联ID表示合并到这个ID对相应的物体"),
    /**
     * "Rename": 无法确定分离动作中某个物体是原先同一个物体，关联ID表示暂时使用的新的ID
    */
    EM_OBJECT_ACTION_RENAME(8, "\"Rename\": 无法确定分离动作中某个物体是原先同一个物体，关联ID表示暂时使用的新的ID");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_OBJECT_ACTION(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_OBJECT_ACTION enumType : EM_OBJECT_ACTION.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_OBJECT_ACTION enumType : EM_OBJECT_ACTION.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}


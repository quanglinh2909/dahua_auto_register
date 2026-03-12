package com.netsdk.lib.enumeration;
import com.netsdk.lib.NetSDKLib;
/**
 * SIM卡状态类型
*/
public enum EM_SIMSTATE_MODE
{
    /**
     * 状态未知
    */
    EM_SIMSTATE_UNKNOWN(0, "状态未知"),
    /**
     * 不存在
    */
    EM_SIMSTATE_UNEXIST(1, "不存在"),
    /**
     * 存在
    */
    EM_SIMSTATE_EXIST(2, "存在"),
    /**
     * 移动网络号码不匹配
    */
    EM_SIMSTATE_UNMATCH(3, "移动网络号码不匹配"),
    /**
     * Pin码锁
    */
    EM_SIMSTATE_PINLOCK(4, "Pin码锁"),
    /**
     * Puk码锁
    */
    EM_SIMSTATE_PUKLOCK(5, "Puk码锁");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_SIMSTATE_MODE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_SIMSTATE_MODE enumType : EM_SIMSTATE_MODE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_SIMSTATE_MODE enumType : EM_SIMSTATE_MODE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}


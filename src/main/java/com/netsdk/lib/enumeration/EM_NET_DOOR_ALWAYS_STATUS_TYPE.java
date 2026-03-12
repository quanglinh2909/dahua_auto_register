package com.netsdk.lib.enumeration;
import com.netsdk.lib.NetSDKLib;
/**
 * 门常开常闭状态
*/
public enum EM_NET_DOOR_ALWAYS_STATUS_TYPE
{
    EM_NET_DOOR_ALWAYS_STATUS_UNKNOWN(0, ""),
    /**
     * 正常
    */
    EM_NET_DOOR_ALWAYS_STATUS_NORMAL(1, "正常"),
    /**
     * 门打开
    */
    EM_NET_DOOR_ALWAYS_STATUS_OPEN(2, "门打开"),
    /**
     * 门关闭
    */
    EM_NET_DOOR_ALWAYS_STATUS_CLOSE(3, "门关闭");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_NET_DOOR_ALWAYS_STATUS_TYPE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_NET_DOOR_ALWAYS_STATUS_TYPE enumType : EM_NET_DOOR_ALWAYS_STATUS_TYPE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_NET_DOOR_ALWAYS_STATUS_TYPE enumType : EM_NET_DOOR_ALWAYS_STATUS_TYPE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}


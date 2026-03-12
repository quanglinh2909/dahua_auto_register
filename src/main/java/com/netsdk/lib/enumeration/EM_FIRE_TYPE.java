package com.netsdk.lib.enumeration;
import com.netsdk.lib.NetSDKLib;
/**
 * 报警框所属位置
*/
public enum EM_FIRE_TYPE
{
    /**
     * 位置未知
    */
    EM_FIRE_TYPE_UNKNOWN(-1, "位置未知"),
    /**
     * 报警框所属检测区
    */
    EM_FIRE_TYPE_DETECT_AREA(0, "报警框所属检测区"),
    /**
     * 报警框所属非检测区
    */
    EM_FIRE_TYPE_NO_DETECT_AREA(1, "报警框所属非检测区"),
    /**
     * 检测区和检测区同时存在
    */
    EM_FIRE_TYPE_DETECT_AREA_ALL(2, "检测区和检测区同时存在");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_FIRE_TYPE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_FIRE_TYPE enumType : EM_FIRE_TYPE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_FIRE_TYPE enumType : EM_FIRE_TYPE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}


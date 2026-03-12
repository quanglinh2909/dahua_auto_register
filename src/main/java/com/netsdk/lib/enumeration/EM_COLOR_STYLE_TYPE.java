package com.netsdk.lib.enumeration;
import com.netsdk.lib.NetSDKLib;
/**
 * 色彩风格
*/
public enum EM_COLOR_STYLE_TYPE
{
    /**
     * 未知
    */
    EM_COLOR_STYLE_UNKNOWN(0, "未知"),
    /**
     * 柔和
    */
    EM_COLOR_STYLE_GENTLE(1, "柔和"),
    /**
     * 标准
    */
    EM_COLOR_STYLE_STANDARD(2, "标准"),
    /**
     * 艳丽
    */
    EM_COLOR_STYLE_FLAMBOYANT(3, "艳丽");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_COLOR_STYLE_TYPE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_COLOR_STYLE_TYPE enumType : EM_COLOR_STYLE_TYPE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_COLOR_STYLE_TYPE enumType : EM_COLOR_STYLE_TYPE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}


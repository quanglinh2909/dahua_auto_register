package com.netsdk.lib.enumeration;
import com.netsdk.lib.NetSDKLib;
/**
 * 报警声音效果
*/
public enum EM_ALARM_SOUND_EFFECT
{
    /**
     * 未知的音效
    */
    EM_ALARM_SOUND_EFFECT_UNKNOWN(0, "未知的音效"),
    /**
     * 静音
    */
    EM_ALARM_SOUND_EFFECT_MUTE(1, "静音"),
    /**
     * 较为轻柔的报警音, 起到提示作用
    */
    EM_ALARM_SOUND_EFFECT_NOTICE(2, "较为轻柔的报警音, 起到提示作用"),
    /**
     * 尖锐响亮的报警音, 起到警示作用
    */
    EM_ALARM_SOUND_EFFECT_ALERT(3, "尖锐响亮的报警音, 起到警示作用");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_ALARM_SOUND_EFFECT(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_ALARM_SOUND_EFFECT enumType : EM_ALARM_SOUND_EFFECT.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_ALARM_SOUND_EFFECT enumType : EM_ALARM_SOUND_EFFECT.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}


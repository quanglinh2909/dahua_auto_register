package com.netsdk.lib.enumeration;
import com.netsdk.lib.NetSDKLib;
/**
 * 音频输出模式
*/
public enum NET_AUDIO_OUTPUT_MODE
{
    /**
     * 自动切换音频输出, 只有一个音频窗口
    */
    NET_AUDIO_AUTO(0, "自动切换音频输出, 只有一个音频窗口"),
    /**
     * 禁止所有音频输出
    */
    NET_AUDIO_DISABLE(1, "禁止所有音频输出"),
    /**
     * 强制输出用户指定的某个窗口的音频, 只有一个音频窗口
    */
    NET_AUDIO_FORCE(2, "强制输出用户指定的某个窗口的音频, 只有一个音频窗口"),
    /**
     * 开启指定窗口音频, 可以有多路音频输出
    */
    NET_AUDIO_ENABLE_ONE(3, "开启指定窗口音频, 可以有多路音频输出"),
    /**
     * 关闭指定窗口音频, 可以有多路音频输出
    */
    NET_AUDIO_DISABLE_ONE(4, "关闭指定窗口音频, 可以有多路音频输出"),
    /**
     * 多路音频输出, 查询时可用, 设置时该值无效
    */
    NET_AUDIO_MULTI(5, "多路音频输出, 查询时可用, 设置时该值无效");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    NET_AUDIO_OUTPUT_MODE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (NET_AUDIO_OUTPUT_MODE enumType : NET_AUDIO_OUTPUT_MODE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (NET_AUDIO_OUTPUT_MODE enumType : NET_AUDIO_OUTPUT_MODE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}


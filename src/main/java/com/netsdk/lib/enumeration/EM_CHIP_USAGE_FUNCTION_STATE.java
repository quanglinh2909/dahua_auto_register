package com.netsdk.lib.enumeration;
import com.netsdk.lib.NetSDKLib;
/**
 * 智能卡功能状态
*/
public enum EM_CHIP_USAGE_FUNCTION_STATE
{
    /**
     * 未知
    */
    EM_CHIP_USAGE_FUNCTION_STATE_UNKNWON(0, "未知"),
    /**
     * 正在进行智能压缩
    */
    EM_CHIP_USAGE_FUNCTION_STATE_COMPRESS(1, "正在进行智能压缩"),
    /**
     * 正在进行智能分析
    */
    EM_CHIP_USAGE_FUNCTION_STATE_INTELLI(2, "正在进行智能分析"),
    /**
     * 正在进行智能分析和智能压缩
    */
    EM_CHIP_USAGE_FUNCTION_STATE_COMBINATION(3, "正在进行智能分析和智能压缩"),
    /**
     * 正在切换到智能压缩功能
    */
    EM_CHIP_USAGE_FUNCTION_STATE_SWITCHTOCOMPRESS(4, "正在切换到智能压缩功能"),
    /**
     * 正在切换到智能分析功能
    */
    EM_CHIP_USAGE_FUNCTION_STATE_SWITCHTOINTELLI(5, "正在切换到智能分析功能"),
    /**
     * 正在切换到混合模式
    */
    EM_CHIP_USAGE_FUNCTION_STATE_SWITCHTOCOMBINA(6, "正在切换到混合模式"),
    /**
     * 智能卡错误不可用
    */
    EM_CHIP_USAGE_FUNCTION_STATE_ERROR(7, "智能卡错误不可用"),
    /**
     * 正在切换到AI开放平台
    */
    EM_CHIP_USAGE_FUNCTION_STATE_SWITCHTOOPENAI(8, "正在切换到AI开放平台"),
    /**
     * 正在进行AI开放平台智能分析
    */
    EM_CHIP_USAGE_FUNCTION_STATE_OPENAI(9, "正在进行AI开放平台智能分析"),
    /**
     * 正在切换到视觉文本大模型
    */
    EM_CHIP_USAGE_FUNCTION_STATE_SWITCHTOVLM(10, "正在切换到视觉文本大模型"),
    /**
     * 正在进行视觉文本大模型
    */
    EM_CHIP_USAGE_FUNCTION_STATE_VLM(11, "正在进行视觉文本大模型"),
    /**
     * 正在进行交通大模型
    */
    EM_CHIP_USAGE_FUNCTION_STATE_TLM(12, "正在进行交通大模型"),
    /**
     * 正在切换到交通大模型
    */
    EM_CHIP_USAGE_FUNCTION_STATE_SWITCHTOTLM(13, "正在切换到交通大模型"),
    /**
     * 正在进行二次研判交通大模型
    */
    EM_CHIP_USAGE_FUNCTION_STATE_SEMTF(14, "正在进行二次研判交通大模型"),
    /**
     * 正在切换到二次研判交通大模型
    */
    EM_CHIP_USAGE_FUNCTION_STATE_SWITCHTOSEMTF(15, "正在切换到二次研判交通大模型");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_CHIP_USAGE_FUNCTION_STATE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_CHIP_USAGE_FUNCTION_STATE enumType : EM_CHIP_USAGE_FUNCTION_STATE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_CHIP_USAGE_FUNCTION_STATE enumType : EM_CHIP_USAGE_FUNCTION_STATE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}


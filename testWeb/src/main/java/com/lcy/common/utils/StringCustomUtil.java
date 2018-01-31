package com.lcy.common.utils;

public class StringCustomUtil {
    /**
     * 校验字符是否为空
     * @param data
     * @return
     * true：空字符串
     * false：非空字符串
     */
    public static boolean isEmpty(String data){
        if(null == data || data == "" || data.length() == 0){
            return true;
        }else {
            return  false;
        }
    }

    /**
     * 校验字符串是否非空
     * @param data
     * @return
     * true：非空字符串
     * false：空字符串
     */
    public static boolean isNotEmpty(String data){
        return isEmpty(data) == true?false:true;
    }
}

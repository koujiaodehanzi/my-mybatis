package com.wyk.minimybatis.utils;

/**
 * @Author: wyk
 * @Description:
 * @Date: Create in 2019/4/26 22:56
 * @Modified By:
 */
public class StringUtil {

    public static String replaceAllChar(String s, char fromChar, char toChar){
        char[] chars = s.toCharArray();

        for (int i=0; i<s.length(); i++){
            char c = chars[i];
            if (fromChar == c){
                chars[i] = toChar;
            }

        }
        return new String(chars);
    }

    /**
     * 首字母小写
     * @param s
     * @return
     */
    public static String firstCharSwapCase(String s){
        if (s != null && s.length() > 0){
            char[] chars = s.toCharArray();
            char c = chars[0];
            if (c >= 'A' && c <= 'Z'){
                c += 32;
                chars[0] = c;
                return new String(chars);
            }
        }
        return s;
    }

    /**
     * 首字母大写
     * @param s
     * @return
     */
    public static String firstCharSwapHigh(String s){
        if (s != null && s.length() > 0){
            char[] chars = s.toCharArray();
            char c = chars[0];
            if (c >= 'a' && c <= 'z'){
                c -= 32;
                chars[0] = c;
                return new String(chars);
            }
        }
        return s;
    }


    public static void main(String[] args) {
        System.out.println(firstCharSwapCase("Assss"));
    }

}

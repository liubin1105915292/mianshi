package com.luki.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * auther win
 * create 2021/5/13 0013 22:13
 **/
public class CommonUtil {
    public static void main(String[] args) {
        Map<String, String> personInfo = getPersonInfoByIdCard("36252619920129411X");
        System.out.println(personInfo.toString());
        String ziMu = getZiMu();
        System.out.println(ziMu);
    }
    /*
    *功能描述  通过身份证获取用户生日性别年龄
    * author liubin
    * date 2021/5/13 0013
    * param [idCard]
    * return java.util.Map<java.lang.String,java.lang.String>
    */
    public static Map<String, String> getPersonInfoByIdCard(String idCard) {
        String birthday = "";
        String age = "";
        String sexCode = "";

        int year = Calendar.getInstance().get(Calendar.YEAR);
        char[] number = idCard.toCharArray();
        boolean flag = true;
        if (number.length == 15) {
            for (int x = 0; x < number.length; x++) {
                if (!flag) return new HashMap<String, String>();
                flag = Character.isDigit(number[x]);
            }
        } else if (number.length == 18) {
            for (int x = 0; x < number.length - 1; x++) {
                if (!flag) return new HashMap<String, String>();
                flag = Character.isDigit(number[x]);
            }
        }
        if (flag && idCard.length() == 15) {
            birthday = "19" + idCard.substring(6, 8) + "-"
                    + idCard.substring(8, 10) + "-"
                    + idCard.substring(10, 12);
            sexCode = Integer.parseInt(idCard.substring(idCard.length() - 3, idCard.length())) % 2 == 0 ? "F" : "M";
            age = (year - Integer.parseInt("19" + idCard.substring(6, 8))) + "";
        } else if (flag && idCard.length() == 18) {
            birthday = idCard.substring(6, 10) + "-"
                    + idCard.substring(10, 12) + "-"
                    + idCard.substring(12, 14);
            sexCode = Integer.parseInt(idCard.substring(idCard.length() - 4, idCard.length() - 1)) % 2 == 0 ? "F" : "M";
            age = (year - Integer.parseInt(idCard.substring(6, 10))) + "";
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("birthday", birthday);
        map.put("age", age);
        map.put("sexCode", sexCode);
        return map;
    }
    /*
    *功能描述 获取两位字母
    * author liubin
    * date 2021/5/13 0013
    * param []
    * return java.lang.String
    */
    public static  String getZiMu() {
        String str = "";
        Random random = new Random();
        for( int i = 0; i < 2; i ++) {
            int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写还是小写
            str += (char)(choice + random.nextInt(26));
        }
        return str;
    }

}

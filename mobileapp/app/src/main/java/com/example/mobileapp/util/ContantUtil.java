package com.example.mobileapp.util;

import com.example.mobileapp.model.Account;

public class ContantUtil {

    private static Account account = null;
    private static String ACCESS_TOKEN = null;
    public static String HOST_URL = "http://192.168.1.133";

    public static void setAccount(Account account) {
        ContantUtil.account = account;
    }

    public static Account getAccount() {
        return account;
    }

    public static String getAccessToken() {
        return ACCESS_TOKEN;
    }

    public static void setAccessToken(String accessToken) {
        ACCESS_TOKEN = accessToken;
    }


}

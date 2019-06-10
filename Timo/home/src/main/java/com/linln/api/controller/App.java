package com.linln.api.controller;
import	java.net.Socket;

import com.linln.common.utils.ToolUtil;

import java.awt.*;

public class App {
    public static void main(String[] args) {

        String rdm = ToolUtil.getRandomString(10);
        System.out.println(rdm);
    }
}

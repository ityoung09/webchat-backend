package com.kedaya.webchatbackend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginDeviceType {

    PC("PC", "PC端"),

    MOBILE("MOBILE", "移动端");

    private String code;

    private String desc;
}

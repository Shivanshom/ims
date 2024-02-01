package com.electrowaveselectronics.inventorymanagement.entity;

import org.springframework.stereotype.Component;

@Component
public class AuthHolder {
    private static final ThreadLocal<Auth> currentAuth = new ThreadLocal<>();

    public static Auth getAuth() {
        return currentAuth.get();
    }

    public static void setAuth(Auth auth) {
        currentAuth.set(auth);
    }

    public static void clearAuth() {
        currentAuth.remove();
    }
}

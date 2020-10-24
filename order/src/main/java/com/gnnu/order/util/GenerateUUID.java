package com.gnnu.order.util;

import java.util.UUID;

public class GenerateUUID {
    private GenerateUUID() {

    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

}

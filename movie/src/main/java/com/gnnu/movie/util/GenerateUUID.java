package com.gnnu.movie.util;

import java.util.UUID;

public class GenerateUUID {
    private GenerateUUID() {

    }

    public static String getUUID() {
        String uuid=UUID.randomUUID().toString();
        String UUID="";
        String [] uids=uuid.split("-");
        for (String str:uids
             ) {
                    UUID+=str;
        }
        return UUID;
    }

}

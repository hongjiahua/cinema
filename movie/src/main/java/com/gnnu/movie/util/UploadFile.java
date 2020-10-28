package com.gnnu.movie.util;

import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class UploadFile {

    private UploadFile() {
    }

    public static String uploadFile(MultipartFile mf, String path) throws IOException {
        String originalFilename = mf.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = GenerateUUID.getUUID() + suffix;
        File file = new File(path+fileName);
        mf.transferTo(file);
        return fileName;

    }
}

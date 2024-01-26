package com.travel.busan.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;


@Service
@Log
public class FileServie {

    public void uploadFile(String uploadPath, String fileName, MultipartFile multipartFile) throws Exception {
        FileOutputStream fos = new FileOutputStream(uploadPath);
        fos.write(multipartFile.getBytes());
        fos.close();
    }

    public void deleteFile(String filePath) throws  Exception{
        File deleteFile = new File(filePath);

        if(deleteFile.exists()){
            deleteFile.delete();
            log.info("파일을 삭제하였습니다.");
        }
        else
            log.info("파일이 존재하지 않습니다.");
    }
}

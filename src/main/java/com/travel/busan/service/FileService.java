package com.travel.busan.service;

import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;


@Service
@Log
public class FileService {

    @Transactional
    public void uploadFile(MultipartFile multipartFile, String checkFolder, String uploadUrl) throws Exception {
        File file = new File(checkFolder);
        if(!file.exists()){
            file.mkdir();
            System.out.println("파일을 생성하였습니다.");
        }
        FileOutputStream fos = new FileOutputStream(uploadUrl);
        fos.write(multipartFile.getBytes());
        fos.close();
    }

    @Transactional
    public void deleteFile(String filePath) throws  Exception{
        File deleteFile = new File(filePath);

        if(deleteFile.exists()){
            deleteFile.delete();
            log.info("파일을 삭제하였습니다.");
        }
        else
            throw new IllegalStateException("파일이 존재하지 않습니다.");
    }



}

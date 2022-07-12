package com.fixstar.util.file;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Component
public class FileUploader {

    private final static String SAVE_DIR = Paths.get("src","uploadFiles").toString();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void upload(List<MultipartFile> uploadFiles) throws IOException {
        //String path = Paths.get(SAVE_DIR).toAbsolutePath().toString();
        if(uploadFiles != null && uploadFiles.size()>0) {
            for (MultipartFile uploadFile : uploadFiles) {
                String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                String originalFileName = uploadFile.getOriginalFilename();
                String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String fileName = UUID.randomUUID() + "_" + dateTime + "_" + originalFileName;

                File file = new File(SAVE_DIR + "/" + fileName);

                logger.info("Upload Path >> " + file.getPath());

                uploadFile.transferTo(file);

                //FileUtils.deleteQuietly(file);

                FileDto fileDto = new FileDto();
                fileDto.setFileSize(uploadFile.getSize());
                fileDto.setOriginalFileName(uploadFile.getOriginalFilename());
                fileDto.setSaveFileName(fileName);

                // 업로드 정보 저장....  save(fileDto)
            }
        }
    }
}
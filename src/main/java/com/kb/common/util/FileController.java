package com.kb.common.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Log4j
@RestController
@RequestMapping("/api/file")
@NoArgsConstructor @AllArgsConstructor
@PropertySource({"classpath:/application.properties"})
public class FileController {
    @Autowired private S3Uploader uploader;

    @PostMapping
    public ResponseEntity<String> save(@RequestPart("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(uploader.upload(file)); // 업로드된 이미지 파일 이름 반환
        } catch (Exception e) {
            return ResponseEntity.status(500).body("이미지 업로드에 실패했습니다 :: " + e.getMessage());
        }
    }
}

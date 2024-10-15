package com.kb.common.util;

import com.amazonaws.AmazonClientException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Log4j
@Service
@RequiredArgsConstructor
public class S3Uploader {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}") private String bucket;
    @Value("${spring.servlet.multipart.max-file-size}") private String maxSizeString;

    //파일 저장
    public String upload(MultipartFile file) throws IOException {
        String savedName = createNameRandomly(file); //버킷에 저장되는 파일 이름 {UUID}.{확장자}

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize()); //파일 용량
        metadata.setContentType(file.getContentType()); //파일 타입

        try {
            amazonS3.putObject(bucket, savedName, file.getInputStream(), metadata);

        } catch (AmazonS3Exception e) {
            log.error("Amazon S3 error while uploading file: " + e.getMessage());
            throw new AmazonS3Exception(e.getMessage());
        } catch (SdkClientException e) {
            log.error("AWS SDK client error while uploading file: " + e.getMessage());
            throw new AmazonClientException(e.getMessage());
        } catch (IOException e) {
            log.error("IO error while uploading file: " + e.getMessage());
            throw new IOException(e.getMessage());
        }

        return savedName;
    }

    //복수 파일 저장 :: 전체 url 리턴
    public List<String> uploadList(MultipartFile[] files) throws IOException {
        List<String> savedUrlList = new ArrayList<>();

        for(MultipartFile file : files) {
            String savedName = upload(file);
            savedUrlList.add(amazonS3.getUrl(bucket, savedName).toString());
        }

        return savedUrlList;
    }

    private String createNameRandomly(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        String extension = validateFileExtension(originalName); // 파일 확장자 (png..)
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        return uuid + "." + extension;
    }

    // 파일 확장자 체크
    private String validateFileExtension(String originalName) {
        String fileExtension = originalName.substring(originalName.lastIndexOf(".") + 1).toLowerCase();
        List<String> allowedExtensions = Arrays.asList("jpg", "png", "gif", "jpeg", "pdf", "word");

        if (!allowedExtensions.contains(fileExtension)) {
            throw new RuntimeException("파일 확장자가 올바르지 않습니다.");
        }

        return fileExtension;
    }

    //파일 삭제
    public void delete(String url) {
        String[] urlParts = url.split("/");
        String fileBucket = urlParts[2].split("\\.")[0];

        if (!fileBucket.equals(bucket)) {
            throw new RuntimeException("버킷 이름이 올바르지 않습니다.");
        }

        String objectKey = String.join("/", Arrays.copyOfRange(urlParts, 3, urlParts.length));

        if (!amazonS3.doesObjectExist(bucket, objectKey)) {
            throw new RuntimeException("해당 파일이 존재하지 않습니다.");
        }

        try {
            amazonS3.deleteObject(bucket, objectKey);
            
        } catch (AmazonS3Exception e) {
            log.error("File delete fail : " + e.getMessage());
            throw new RuntimeException("파일 삭제 실패");
        } catch (SdkClientException e) {
            log.error("AWS SDK client error : " + e.getMessage());
            throw new RuntimeException("파일 삭제 실패");
        }

        log.info("파일 삭제 완료: " + objectKey);
    }
}

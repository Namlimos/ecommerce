package com.ecommerce.Service.Implement;

import com.ecommerce.DTO.BaseResponse;
import com.ecommerce.DTO.ResponseCode;
import com.ecommerce.Entity.FileApp;
import com.ecommerce.Repository.FileAppRepository;
import com.ecommerce.Service.FileAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileAppServiceImp implements FileAppService {

    private final FileAppRepository fileAppRepository;
    @Override
    public ResponseEntity<BaseResponse<String>> uploadFile(MultipartFile[] files) {
        List<String> fileUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                // Định nghĩa đường dẫn lưu trữ tệp
                String uploadDir = "src/main/resources/file/";
                String originalFileName = file.getOriginalFilename();
                String fileExtension = "";

                // Extract đuôi file
                if (originalFileName != null && originalFileName.contains(".")) {
                    fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                }

                // Tạo tên file mới với timestamp và đuôi file
                String fileName = System.currentTimeMillis() + "_" + originalFileName;

                // Lưu tệp vào hệ thống tệp
                Path path = Paths.get(uploadDir + fileName);
                Files.createDirectories(path.getParent());
                Files.write(path, file.getBytes());

                // Tạo URL truy cập file
                String fileUrl = "http://localhost:8080/file/" + fileName;
                fileUrls.add(fileUrl);

                // Lưu thông tin file vào cơ sở dữ liệu (ví dụ)
                FileApp fileApp = new FileApp();
                fileApp.setFileName(fileName);
                fileApp.setFileUrl(fileUrl);
                fileApp.setType(fileExtension);
                fileAppRepository.save(fileApp);

            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        return ResponseEntity.ok(new BaseResponse<>(
                ResponseCode.SUCCESS.getCode(),
                "Files uploaded successfully",
                "" + fileUrls
        ));

    }
}

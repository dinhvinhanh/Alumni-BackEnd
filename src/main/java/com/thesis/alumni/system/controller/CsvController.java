package com.thesis.alumni.system.controller;

import com.thesis.alumni.system.dto.BaseResponse;
import com.thesis.alumni.system.service.CsvService;
import com.thesis.alumni.system.utils.CsvUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(value = "*", allowedHeaders = "*")
@RequestMapping("/api/files")
@AllArgsConstructor
public class CsvController {

    private final CsvService csvService;
    @PostMapping("/upload-alumni")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (CsvUtil.hasCSVFormat(file)) {
            try {
                csvService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return new ResponseEntity<>(
                        BaseResponse
                                .builder()
                                .message("Upload thành công")
                                .status(200)
                                .timestamp(new Date())
                                .build(), HttpStatus.OK);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return new ResponseEntity<>(
                        BaseResponse
                                .builder()
                                .message("Upload thất bại")
                                .status(200)
                                .timestamp(new Date())
                                .build(), HttpStatus.EXPECTATION_FAILED);
            }
        }
        message = "Please upload a csv file!";
        return new ResponseEntity<>(
                BaseResponse
                        .builder()
                        .message("Thiếu trường 'file'")
                        .status(200)
                        .timestamp(new Date())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String fileName = new Date().getTime() + "-" + multipartFile.getOriginalFilename();
        CsvUtil.saveFile("static", fileName, multipartFile);
        Map<String, String> result = new HashMap<>();
        result.put("url", "/" + fileName);
        return new ResponseEntity<>(
                BaseResponse
                        .builder()
                        .message("OK")
                        .status(200)
                        .data(result)
                        .timestamp(new Date())
                        .build(), HttpStatus.OK);
    }
}

package com.itplh.web.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-03-20 10:55
 * @version: v1.0.0
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private String folder = "/Users/tanpeng/tp-code/tp-practice/itplh-security/itplh-security-demo/src/main/java/com/itplh/web/controller";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File localFile = new File(folder, file.getOriginalFilename());
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletResponse response) throws IOException {
        try (
                OutputStream out = response.getOutputStream();
                InputStream in = new FileInputStream(new File(folder, id + ".txt"));
        ) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + id + ".txt");
            IOUtils.copy(in, out);
            out.flush();
        }
    }
}

@Data
@AllArgsConstructor
class FileInfo {
    private String path;
}

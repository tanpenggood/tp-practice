package com.itplh.chapter5.section2_learn_web_mvc.point2_restful;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author: tanpenggood
 * @since: 2020-06-19 22:43
 */
@Slf4j
@RestController
@RequestMapping("/chapter5/section2/point2/usage11")
public class Usage11MultipartFileController {

    /**
     * 可以通过四种方式来接收 multipart/form-data
     * Resource是Spring对资源的抽象，它的实现可以是文件资源、网址资源、类路径资源或输入流资源等
     *
     * @author: tanpenggood
     * @since : 2020-06-19 23:53
     */
    @PostMapping
    public ResponseEntity<Resource> upload(MultipartFile file,
                                           @RequestPart("file") MultipartFile file2,
                                           @RequestParam("file") MultipartFile file3,
                                           @RequestParam("file") Part part) throws IOException {
        log.info("file.equals(file2) {}", file.equals(file2));
        log.info("file.equals(file3) {}", file.equals(file3));
        log.info("file.getSize() == part.getSize() {}", file.getSize() == part.getSize());

        Resource imageResource = new InputStreamResource(file.getInputStream());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageResource);
    }

    @PostMapping("multiple")
    public String multipleUpload(@RequestParam Map<String, MultipartFile> fileMap) {
        fileMap.forEach((key, file) -> log.info("{} of size is {}", file.getName(), file.getSize()));
        return "multiple ok";
    }

    @PostMapping("multiple2")
    public String multipleUploadByMultiValueMap(@RequestParam MultiValueMap<String, MultipartFile> fileMultiValueMap) {
        List<MultipartFile> files = fileMultiValueMap.get("file");
        Optional<Long> totalSize = files.stream().map(file -> file.getSize()).reduce((p, n) -> p + n);
        log.info("file total size is {}", totalSize.orElse(0L));
        return "multiple2 ok";
    }

}

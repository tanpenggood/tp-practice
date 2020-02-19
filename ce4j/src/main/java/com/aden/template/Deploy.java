package com.aden.template;

import com.aden.command.Git;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-19 14:12
 * @version: v1.0.0
 */
@FunctionalInterface
public interface Deploy {

    void template(Git sourceGit, Git targetGit);

    default String yyyyMMddHHmmss() {
        return DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
    }

}

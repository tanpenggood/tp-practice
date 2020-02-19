package com.aden.biz;

import com.aden.util.FileUtils;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-18 11:47
 * @version: 1.0.0
 */
public interface Git {

//    String ROOT_DIR = "e:/code/aden/Z-jenkins/test";
    String ROOT_DIR = "/Users/tanpeng/tp-code";

    void clone2(String gitURL);

    void pull(String destDir);

    void checkout(String targetProject, String targetBranch);

    void push();

    void template();

    default StringBuilder buildString() {
        return new StringBuilder();
    }
}

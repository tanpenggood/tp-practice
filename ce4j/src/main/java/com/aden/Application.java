package com.aden;

import com.aden.biz.Git;
import com.aden.biz.impl.GitBeiJingLinuxClientImpl;
import com.aden.biz.impl.GitBeiJingWindowsClientImpl;
import com.aden.util.FileUtils;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-18 13:53
 * @version: 1.0.0
 */
public class Application {

    public static void main(String[] args) {
//        new GitBeiJingWindowsClientImpl().template();
//        Git git = new GitBeiJingLinuxClientImpl();
//        git.template();

        FileUtils.copy("/Users/tanpeng/tp-code/bj/arms-front/README.md", "/Users/tanpeng/tp-code/bj/README.md");
    }
}

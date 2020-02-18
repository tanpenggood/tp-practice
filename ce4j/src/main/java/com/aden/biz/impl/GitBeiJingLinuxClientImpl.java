package com.aden.biz.impl;

import com.aden.CommandExecutor;
import com.aden.biz.Git;
import com.aden.util.FileUtils;

import java.util.Arrays;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-18 12:08
 * @version: 1.0.0
 */
public class GitBeiJingLinuxClientImpl implements Git {

    public static final String SOURCE_DIR = "bj";
    public static final String BJ_FRONT_DIR = "arms-front";
    public static final String BJ_BACK_DIR = "arms-backend";
    public static final String BJ_FRONT_URL = "http://git.thextrader.cn/aden/arms-front.git";
    public static final String BJ_BACK_URL = "http://git.thextrader.cn/aden/arms-backend.git";

    @Override
    public void clone2(String gitURL) {
        if (gitURL.contains(BJ_FRONT_DIR)) {
            FileUtils.createDirectoriesIfExistClean(ROOT_DIR, SOURCE_DIR, BJ_FRONT_DIR);
        } else if (gitURL.contains(BJ_BACK_DIR)){
            FileUtils.createDirectoriesIfExistClean(ROOT_DIR, SOURCE_DIR, BJ_BACK_DIR);
        }

        String commandLine = buildString()
                .append(String.format("cd %s/%s;", ROOT_DIR, SOURCE_DIR))
                .append(String.format("git clone %s;", gitURL))
                .toString();
        CommandExecutor.executor.executeMutilShell(Arrays.asList(commandLine.split(";")));
    }

    @Override
    public void pull(String destDir) {

    }

    @Override
    public void checkout(String targetProject, String targetBranch) {
        String commandLine = buildString()
                .append(String.format("cd %s/%s/%s;", ROOT_DIR, SOURCE_DIR, targetProject))
                .append(String.format("git checkout %s;", targetBranch))
                .toString();
        CommandExecutor.executor.executeMutilShell(Arrays.asList(commandLine.split(";")));
    }

    @Override
    public void push() {

    }

    @Override
    public void template() {
        clone2(BJ_FRONT_URL);
        checkout(BJ_FRONT_DIR, "develop");

        clone2(BJ_BACK_URL);
        checkout(BJ_BACK_DIR, "develop");
    }
}

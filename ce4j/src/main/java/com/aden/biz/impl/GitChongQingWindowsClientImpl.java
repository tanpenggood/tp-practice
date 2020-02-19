package com.aden.biz.impl;

import com.aden.biz.Git;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-18 12:09
 * @version: 1.0.0
 */
public class GitChongQingWindowsClientImpl implements Git {

    public static final String TARGET_DIR = "cq";
    public static final String CQ_FRONT_DIR = TARGET_DIR + "/arms-frontend";
    public static final String CQ_FRONT_URL = "git@192.168.0.152:jenkins-group/arms-frontend.git";
    public static final String CQ_BACK_DIR = "cq/arms-backend";
    public static final String CQ_BACK_URL = "git@192.168.0.152:jenkins-group/arms-backend.git";

    @Override
    public void clone2(String gitURL) {

    }

    @Override
    public void pull(String destDir) {

    }

    @Override
    public void checkout(String targetProject, String targetBranch) {

    }

    @Override
    public void push() {

    }

    @Override
    public void template() {

    }
}

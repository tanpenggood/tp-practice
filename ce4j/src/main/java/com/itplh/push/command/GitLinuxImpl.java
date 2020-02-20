package com.itplh.push.command;

import java.util.Map;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-20 21:06
 * @version: v1.0.0
 */
public class GitLinuxImpl extends Git {
    @Override
    public Git clone(String gitURL) {
        return this;
    }

    @Override
    public Git checkout(String targetBranch) {
        return this;
    }

    @Override
    public Git remote(String type, Map<String, String>  gitURLs) {
        return this;
    }

    @Override
    public Git tag(String param) {
        return this;
    }

    @Override
    public Git push(String remoteName, String param) {
        return this;
    }

    @Override
    public Git add() {
        return this;
    }

    @Override
    public Git commit(String message) {
        return this;
    }
}

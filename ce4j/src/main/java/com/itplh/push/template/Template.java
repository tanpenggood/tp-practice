package com.itplh.push.template;

import com.itplh.push.command.Git;
import com.itplh.push.command.GitLinuxImpl;
import com.itplh.push.command.GitWindowsImpl;
import com.itplh.push.util.FileUtils;

import java.util.Map;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-20 21:06
 * @version: v1.0.0
 */
public abstract class Template {

    private Git git;

    protected Template() {
        // 根据操作系统选择git
        this.git = FileUtils.isWindows() ? new GitWindowsImpl() : new GitLinuxImpl();
    }

    public void run() {
        run(git);
    }

    public abstract void run(Git git);

    public void setRootDir(String rootDir) {
        git.setRootDir(rootDir);
    }

    public void setSourceGitURL(String sourceGitURL) {
        git.setSourceGitURL(sourceGitURL);
    }

    public void setTargetGitURLs(Map<String, String> targetGitURLs) {
        git.setTargetGitURLs(targetGitURLs);
    }

}

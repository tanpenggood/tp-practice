package com.itplh.push.command;

import com.itplh.push.util.FileUtils;
import com.itplh.push.util.StringUtils;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-20 21:00
 * @version: v1.0.0
 */
public abstract class Git implements Command {

    private String rootDir;
    private String sourceGitURL;
    private Map<String, String> targetGitURLs;

    protected Git() {
        // 默认配置
        this.rootDir = FileUtils.isWindows() ? "C:/Users/Default/Downloads/easypush" : "/Users/tanpeng/Downloads/easypush";
        this.sourceGitURL = "";
        this.targetGitURLs = Collections.EMPTY_MAP;
    }

    public String getProjectDir() {
        String gitURL = this.sourceGitURL;
        String projectDir = gitURL.substring(gitURL.lastIndexOf("/") + 1, gitURL.lastIndexOf(".git"));
        return Optional.ofNullable(projectDir).orElse("");
    }

    public String getWindowsDisk() {
        String disk = getRootDir().substring(0, getRootDir().indexOf(":") + 1);
        if (StringUtils.isEmpty(disk)) {
            throw new RuntimeException("rootDir请使用绝对路径");
        }
        return disk;
    }

    protected StringBuilder commandBuilder() {
        StringBuilder builder = new StringBuilder();
        if (FileUtils.isWindows()) {
            builder.append(getWindowsDisk())
                    .append(String.format(" && cd %s/%s ", getRootDir(), getProjectDir()));
        } else {
            builder.append(String.format("cd %s;", getRootDir()));
        }
        return builder;
    }

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir = rootDir;
    }

    public String getSourceGitURL() {
        return sourceGitURL;
    }

    public void setSourceGitURL(String sourceGitURL) {
        this.sourceGitURL = sourceGitURL;
    }

    public Map<String, String> getTargetGitURLs() {
        return targetGitURLs;
    }

    public void setTargetGitURLs(Map<String, String> targetGitURLs) {
        this.targetGitURLs = targetGitURLs;
    }

}

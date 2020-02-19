package com.aden.command;

import com.aden.util.FileUtils;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-18 11:47
 * @version: 1.0.0
 */
public abstract class Git {

    private String gitURL;
    private String projectParentDir;
    private String rootDir;

    protected Git(String gitURL, String projectParentDir) {
        this.gitURL = gitURL;
        this.projectParentDir = projectParentDir;
        this.rootDir = FileUtils.isWindows() ? "e:/code/aden/Z-jenkins/test" : "/Users/tanpeng/tp-code/jenkins";
    }

    /**
     * @description: 克隆代码
     * @author: tanpeng
     * @date : 2020-02-19 11:45
     * @version: v1.0.0
     */
    public void clone2() {
        FileUtils.createDirectoriesIfExistClean(getRootDir(), getProjectParentDir(), getProjectDir());

        if (FileUtils.isWindows()) {
            String commandLine = buildString()
                    .append(String.format("cd %s/%s ", getRootDir(), getProjectParentDir()))
                    .append(String.format("&& git clone %s", gitURL))
                    .toString();
            CommandExecutor.executor.execute(commandLine);
        } else {
            String commandLine = buildString()
                    .append(String.format("cd %s/%s;", getRootDir(), getProjectParentDir()))
                    .append(String.format("git clone %s;", gitURL))
                    .toString();
            CommandExecutor.executor.executeMutilShell(Arrays.asList(commandLine.split(";")));
        }
    }

    /**
     * @description: 切换分支
     * @author: tanpeng
     * @date : 2020-02-19 11:44
     * @version: v1.0.0
     */
    public void checkout(String targetBranch) {
        if (FileUtils.isWindows()) {
            String commandLine = buildString()
                    .append(String.format("cd %s ", getProjectAbsPath()))
                    .append(String.format("&& git checkout %s", targetBranch))
                    .toString();
            CommandExecutor.executor.execute(commandLine);
        } else {
            String commandLine = buildString()
                    .append(String.format("cd %s;", getProjectAbsPath()))
                    .append(String.format("git checkout %s;", targetBranch))
                    .toString();
            CommandExecutor.executor.executeMutilShell(Arrays.asList(commandLine.split(";")));
        }
    }

    /**
     * @description: 提交本地代码到远程仓库
     * @author: tanpeng
     * @date : 2020-02-19 14:04
     * @version: v1.0.0
     */
    public void commit(String message) {
        message = (message == null || "".equals(message)) ?
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
                : message;
        if (FileUtils.isWindows()) {
            String commandLine = buildString()
                    .append(String.format("cd %s ", getProjectAbsPath()))
                    .append("&& git add .")
                    .append(String.format("&& git commit -m %s", message))
                    .toString();
            CommandExecutor.executor.execute(commandLine);
        } else {
            String commandLine = buildString()
                    .append(String.format("cd %s;", getProjectAbsPath()))
                    .append("git add .;")
                    .append(String.format("git commit -m %s;", message))
                    .toString();
            CommandExecutor.executor.executeMutilShell(Arrays.asList(commandLine.split(";")));
        }
    }

    /**
     * @description: 新建 tag，并推送到远程仓库
     * @author: tanpeng
     * @date : 2020-02-19 12:47
     * @version: v1.0.0
     */
    public void newTagAndPush(String newTagName) {
        if (FileUtils.isWindows()) {
            String commandLine = buildString()
                    .append(String.format("cd %s ", getProjectAbsPath()))
                    .append(String.format("&& git tag %s ", newTagName))
                    .append(String.format("&& git push origin %s", newTagName))
                    .toString();
            CommandExecutor.executor.execute(commandLine);
        } else {
            String commandLine = buildString()
                    .append(String.format("cd %s;", getProjectAbsPath()))
                    .append(String.format("git tag %s;", newTagName))
                    .append(String.format("git push origin %s;", newTagName))
                    .toString();
            CommandExecutor.executor.executeMutilShell(Arrays.asList(commandLine.split(";")));
        }
    }

    public void push() {
        if (FileUtils.isWindows()) {
            String commandLine = buildString()
                    .append(String.format("cd %s ", getProjectAbsPath()))
                    .append("&& git push")
                    .toString();
            CommandExecutor.executor.execute(commandLine);
        } else {
            String commandLine = buildString()
                    .append(String.format("cd %s;", getProjectAbsPath()))
                    .append("git push;")
                    .toString();
            CommandExecutor.executor.executeMutilShell(Arrays.asList(commandLine.split(";")));
        }
    }

    protected StringBuilder buildString() {
        return new StringBuilder();
    }

    /**
     * @description: 获取git项目名称
     * @author: tanpeng
     * @date : 2020-02-19 11:29
     * @version: v1.0.0
     */
    protected String getProjectDir() {
        String gitURL = this.gitURL;
        return gitURL.substring(gitURL.lastIndexOf("/") + 1, gitURL.lastIndexOf(".git"));
    }

    /**
     * @description: 获取git项目的父目录
     * @author: tanpeng
     * @date : 2020-02-19 11:30
     * @version: v1.0.0
     */
    protected String getProjectParentDir() {
        return projectParentDir;
    }

    protected String getGitURL() {
        return gitURL;
    }

    protected String getRootDir() {
        return rootDir;
    }

    /**
     * @description: 删除 .git 目录
     * @author: tanpeng
     * @date : 2020-02-19 11:00
     * @version: v1.0.0
     */
    public void deletePointGitDir() {
        FileUtils.forceDelete(getProjectAbsPath(), ".git");
    }

    /**
     * @description: 只保留 .git 目录
     * @author: tanpeng
     * @date : 2020-02-19 11:00
     * @version: v1.0.0
     */
    public void onlyKeepPointGitDir() {
        Arrays.stream(Paths.get(getProjectAbsPath()).toFile().listFiles())
                .filter(file -> !".git".equals(file.getName()))
                .forEach(file -> FileUtils.forceDelete(file.getAbsolutePath()));
    }

    public void copy(String sourceAbsPath, String targetAbsPath) {
        FileUtils.copy(sourceAbsPath, targetAbsPath);
    }

    /**
     * @description: 获取git项目绝对路径
     * @author: tanpeng
     * @date : 2020-02-19 12:09
     * @version: v1.0.0
     */
    public String getProjectAbsPath() {
        return String.join("/", getRootDir(), getProjectParentDir(), getProjectDir());
    }

}

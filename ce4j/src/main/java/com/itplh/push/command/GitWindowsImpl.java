package com.itplh.push.command;

import com.aden.command.CommandExecutor;
import com.itplh.push.util.StringUtils;

import java.util.Map;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-20 21:05
 * @version: v1.0.0
 */
public class GitWindowsImpl extends Git {
    @Override
    public Git clone(String gitURL) {
        if (StringUtils.isNotEmpty(gitURL)) {
            String commandLine = new StringBuilder(getWindowsDisk())
                    .append(String.format(" && cd %s", getRootDir()))
                    .append(String.format(" && git clone %s", gitURL))
                    .toString();
            CommandExecutor.executor.execute(commandLine);
        }
        return this;
    }

    @Override
    public Git checkout(String targetBranch) {
        if (StringUtils.isNotEmpty(targetBranch)) {
            String commandLine = commandBuilder()
                    .append(String.format(" && git checkout %s", targetBranch))
                    .toString();
            CommandExecutor.executor.execute(commandLine);
        }
        return this;
    }

    @Override
    public Git remote(String type, Map<String, String> gitURLs) {
        StringBuilder builder = commandBuilder();
        gitURLs.entrySet().stream().forEach(entry ->
                builder.append(String.format(" && git remote %s %s %s", type, entry.getKey(), entry.getValue())));
        CommandExecutor.executor.execute(builder.toString());
        return this;
    }

    @Override
    public Git tag(String tagName) {
        if (StringUtils.isNotEmpty(tagName)) {
            String commandLine = commandBuilder()
                    .append(String.format(" && git tag %s", tagName))
                    .toString();
            CommandExecutor.executor.execute(commandLine);
        }
        return this;
    }

    @Override
    public Git push(String remoteName, String tagNameOrBranchName) {
        String commandLine = commandBuilder()
                .append(String.format(" && git push %s %s", remoteName, tagNameOrBranchName))
                .toString();
        CommandExecutor.executor.execute(commandLine);
        return this;
    }

    @Override
    public Git add() {
        String commandLine = commandBuilder()
                .append(" && git add .")
                .toString();
        CommandExecutor.executor.execute(commandLine);
        return this;
    }

    @Override
    public Git commit(String message) {
        if (StringUtils.isNotEmpty(message)) {
            String commandLine = commandBuilder()
                    .append(String.format(" && git commit -m %s", message))
                    .toString();
            CommandExecutor.executor.execute(commandLine);
        }
        return this;
    }
}

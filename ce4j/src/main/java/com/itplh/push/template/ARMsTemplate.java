package com.itplh.push.template;

import com.itplh.push.command.Git;
import com.itplh.push.util.FileUtils;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-20 23:23
 * @version: v1.0.0
 */
public class ARMsTemplate extends Template{
    @Override
    public void run(Git git) {
        FileUtils.createDirectoriesIfExistClean(git.getRootDir(), git.getProjectDir());

        git.clone(git.getSourceGitURL())
                .checkout("develop")
                .remote("add", git.getTargetGitURLs())
                .tag("tag-v1")
                .push("origin1", "tag-v1");
    }
}

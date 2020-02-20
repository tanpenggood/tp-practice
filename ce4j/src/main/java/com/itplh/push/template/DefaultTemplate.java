package com.itplh.push.template;

import com.itplh.push.command.Git;
import com.itplh.push.util.FileUtils;
import com.itplh.push.util.StringUtils;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-20 21:48
 * @version: v1.0.0
 */
public class DefaultTemplate extends Template {

    @Override
    public void run(Git git) {
        System.out.println(git.getRootDir());
        System.out.println(git.getSourceGitURL());
        System.out.println(git.getProjectDir());
        System.out.println(git.getTargetGitURLs().toString());

        FileUtils.createDirectoriesIfExistClean(git.getRootDir(), git.getProjectDir());

        String tag = StringUtils.buildTag();
        git.clone(git.getSourceGitURL())
                .checkout("develop")
                .remote("add", git.getTargetGitURLs())
                .tag(tag)
                .push("origin", tag);
    }

}

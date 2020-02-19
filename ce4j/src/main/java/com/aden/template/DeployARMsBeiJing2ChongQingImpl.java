package com.aden.template;

import com.aden.command.Git;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-19 14:17
 * @version: v1.0.0
 */
public class DeployARMsBeiJing2ChongQingImpl implements Deploy {

    @Override
    public void template(Git sourceGit, Git targetGit) {
        sourceGit.clone2();
        sourceGit.checkout("develop");
        sourceGit.deletePointGitDir();

        System.out.println();

        targetGit.clone2();
        targetGit.onlyKeepPointGitDir();
        targetGit.copy(sourceGit.getProjectAbsPath(), targetGit.getProjectAbsPath());
        String str = yyyyMMddHHmmss();
        targetGit.commit(str);
        targetGit.push();
        targetGit.newTagAndPush("tag-" + str);
    }

}

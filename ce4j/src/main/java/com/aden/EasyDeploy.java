package com.aden;

import com.aden.command.Git;
import com.aden.template.Deploy;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-19 10:41
 * @version: v1.0.0
 */
public class EasyDeploy {

    public static void run(Deploy deployTemplate,
                           Git sourceGit,
                           Git targetGit) {
        deployTemplate.template(sourceGit, targetGit);
    }
}

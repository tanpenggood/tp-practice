package com.aden;

import com.aden.command.SourceCodeImpl;
import com.aden.command.TargetCodeImpl;
import com.aden.template.DeployGiteeImpl;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-18 13:53
 * @version: 1.0.0
 */
public class Application {

    public static void main(String[] args) {

        String BJ_FRONT_URL = "http://git.thextrader.cn/aden/arms-front.git";
        String BJ_BACK_URL = "http://git.thextrader.cn/aden/arms-backend.git";
        String CQ_FRONT_URL = "git@192.168.0.152:jenkins-group/arms-frontend.git";
        String CQ_BACK_URL = "git@192.168.0.152:jenkins-group/arms-backend.git";

        EasyDeploy.run(new DeployGiteeImpl(),
                new SourceCodeImpl("git@gitee.com:tpswpu/swagger-spring.git"),
                new TargetCodeImpl("git@gitee.com:tpswpu/swagger-spring.git"));

    }
}

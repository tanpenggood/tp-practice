package com.aden;

import com.aden.command.SourceCodeImpl;
import com.aden.command.TargetCodeImpl;
import com.aden.template.DeployARMsBeiJing2ChongQingImpl;

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

        EasyDeploy.run(new DeployARMsBeiJing2ChongQingImpl(),
                new SourceCodeImpl(BJ_FRONT_URL),
                new TargetCodeImpl(CQ_FRONT_URL));

        EasyDeploy.run(new DeployARMsBeiJing2ChongQingImpl(),
                new SourceCodeImpl(BJ_BACK_URL),
                new TargetCodeImpl(CQ_BACK_URL));

    }
}

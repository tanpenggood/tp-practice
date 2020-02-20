package com.itplh.push;

import com.itplh.push.template.ARMsTemplate;
import com.itplh.push.template.DefaultTemplate;

import java.util.HashMap;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-20 22:08
 * @version: v1.0.0
 */
public class App {

    public static void main(String[] args) {

        EasyPush.builder()
                .template(new DefaultTemplate())
                .sourceGitURL("http://192.168.0.152/itplh/easypush.git")
                .targetGitURLs(new HashMap<String, String>(2){{
                    put("origin1", "git@192.168.0.153:jenkins-group/easypush.git");
                }})
                .build()
                .run();

        EasyPush.builder()
                .template(new ARMsTemplate())
                .sourceGitURL("http://192.168.0.152/itplh/easypush.git")
                .targetGitURLs(new HashMap<String, String>(2){{
                    put("origin1", "git@192.168.0.153:jenkins-group/easypush.git");
                }})
                .build()
                .run();
    }
}

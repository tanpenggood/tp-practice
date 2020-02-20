package com.itplh.push.command;

import java.util.Map;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-20 23:29
 * @version: v1.0.0
 */
public interface Command {

    Git clone(String gitURL);

    Git checkout(String targetBranch);

    Git remote(String type, Map<String, String> gitURLs);

    Git tag(String param);

    Git push(String remoteName, String tagNameOrBranchName);

    Git add();

    Git commit(String message);

}

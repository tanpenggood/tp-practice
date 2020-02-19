package com.aden.command;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-19 09:44
 * @version: v1.0.0
 */
public class SourceCodeImpl extends Git {

    public SourceCodeImpl(String gitURL) {
        super(gitURL, "source");
    }

}

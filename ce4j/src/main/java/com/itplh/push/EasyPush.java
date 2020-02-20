package com.itplh.push;

import com.itplh.push.template.DefaultTemplate;
import com.itplh.push.template.Template;

import java.util.Map;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-02-20 21:19
 * @version: v1.0.0
 */
public class EasyPush {

    private final Template template;

    // 私有构造，外部不可new
    private EasyPush(Builder builder) {
        template = builder.template;
    }

    public void run() {
        template.run();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Template template;

        // 内部类私有构造，外部类可new
        private Builder() {
            // 默认配置
            template = new DefaultTemplate();
        }

        public Builder template(Template t) {
            template = t;
            return this;
        }

        public Builder rootDir(String rootDir) {
            template.setRootDir(rootDir);
            return this;
        }

        public Builder sourceGitURL(String sourceGitURL) {
            template.setSourceGitURL(sourceGitURL);
            return this;
        }

        public Builder targetGitURLs(Map<String, String> targetGitURLs) {
            template.setTargetGitURLs(targetGitURLs);
            return this;
        }

        public EasyPush build() {
            return new EasyPush(this);
        }
    }

}

CREATE TABLE IF NOT EXISTS weixin_js_sdk(
  id BIGINT NOT NULL COMMENT '雪花ID',
  access_token VARCHAR(512),
  ticket VARCHAR(512),
  create_time TIMESTAMP,
  PRIMARY KEY (id)
);

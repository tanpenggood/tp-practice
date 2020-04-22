package com.itplh.security.core.validate.code;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: tanpeng
 * @date : 2020/4/22 14:29
 * @version: v1.0.0
 */
@Data
@AllArgsConstructor
public class ImageCode {

	private BufferedImage image;
	private String code;
	private LocalDateTime expireTime;

	public ImageCode(BufferedImage image, String code, int expireIn){
		this.image = image;
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}

	public boolean isExpire() {
		return LocalDateTime.now().isAfter(expireTime);
	}

}

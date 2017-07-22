package com.lzc.shop.utils;

import java.util.UUID;

/**
 * 返回uuid字符串的工具类
 * @author lzc
 *
 */
public class UUIDUtils {
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}

package com.blzg.sms;

import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;

public class SMSRedisService {

	private static final String KEYSMS = "keysms";
	
	/**
	 * 手机号码放入缓存 
	 * @param phone
	 */
	public void setRedisSMS(String phone){
		if(phone!=null){
			Cache cache = Redis.use();
			cache.lpush(KEYSMS, phone);
		}
	}
	
}

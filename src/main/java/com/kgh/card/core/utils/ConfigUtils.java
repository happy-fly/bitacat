package com.kgh.card.core.utils;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.kgh.card.core.config.UserConfig;
import com.kgh.card.core.rule.GeneralValidate;
import com.kgh.card.core.rule.Validate;

/**
 * 配置工具类
 * 
 * @author 孔冠华
 *
 */
public class ConfigUtils {
	
	private static final String user_config_file = "userconfig.json";
	
	/**
	 * 读取用户配置，并封装成为 UserConfig
	 * 
	 * @return
	 */
	public static UserConfig getUserConfig() {
		return JSONObject.parseObject(FileUtils.readFile(user_config_file), UserConfig.class);
	}
	
	/**
	 * 获得游戏的玩法
	 * 
	 * @param userConfig
	 * @return
	 */
	public static Validate getValidate(UserConfig userConfig) {
		String playRule = userConfig.getPlayRule();
		if (StringUtils.isBlank(playRule)) {
			return new GeneralValidate();
		}
		try {
			Object obj = Class.forName(playRule).newInstance();
			if (obj instanceof Validate) {
				return (Validate) obj;
			}
			throw new RuntimeException("指定了错误的规则！" + playRule);
		} catch (Exception e) {
			throw new RuntimeException("指定的规则不存在！", e);
		}
	}
}

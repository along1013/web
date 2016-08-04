package com.demo.common.model;

import com.demo.common.model.base.BaseUserInfo;
import com.jfinal.plugin.activerecord.Page;

/**
 * Generated by JFinal.
 * Blog model.
 * 数据库字段名建议使用驼峰命名规则，便于与 java 代码保持一致，如字段名： userId
 */
@SuppressWarnings("serial")
public class UserInfo extends BaseUserInfo<UserInfo> {
	
	public static final UserInfo me = new UserInfo();
	
	/**
	 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
	 */
	public Page<UserInfo> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from user_info order by id asc");
	}
}



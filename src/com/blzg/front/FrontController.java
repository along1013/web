package com.blzg.front;

import com.blzg.sms.SMSRedisService;
import com.jfinal.core.Controller;

/**
 * 网站页面业务
 * @author Administrator
 *
 */
public class FrontController extends Controller{
	
	public void index(){
		
	}

	/**
	 * 主页面(包含关于白鹭、专业团队、联系我们)
	 */
	public void home(){
		render("home.html");
	}
	
	/**
	 * 企业活动
	 */
	public void active(){
		render("active.html");
	}
	
	/**
	 * 企业旅游
	 */
	public void outing(){
		render("outing.html");
	}
	
	/**
	 * 净值查询
	 */
	public void value(){
		render("value.html");
	}
	
	
	//*****************************登录注册相关***************************
	
	//同意相关内容界面
	public void agree(){
		render("/index/agree.html");
	}
	
	public void sendCode(String phone){
		new SMSRedisService().setRedisSMS(phone);
	}
}

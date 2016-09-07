package com.blzg.back;

import com.demo.common.model.UserInfo;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;

public class BackController extends Controller{

	/**
	 * 后台登录主页面
	 */
	public void index(){
		render("index.html");
	}
	
	/**
	 * 登录帐号、密码请求
	 */
	public void login(){
		String userName = getPara("userName");
		String passWord = getPara("passWord");
		if(userName.isEmpty() || passWord.isEmpty()){
			render("index.html");
			return;
		}
		PropKit.use("a_little_config.txt");
		if(userName.equals(PropKit.get("userName")) && passWord.equals(PropKit.get("passWord"))){
			render("home.html");
			return;
		}
		render("index.html");
	}
	
	public void getUserList(){
		setAttr("userList", UserInfo.me.paginate(getParaToInt("pageNo", 1), 10));
		System.out.println(getAttr("userList"));
		render("userList.html");		
	}
}

package com.blzg.back;

import com.demo.common.model.UserInfo;
import com.jfinal.core.Controller;

/**
 * BlogController
 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
public class UserController extends Controller {
	
	public void index() {
		setAttr("userList", UserInfo.me.paginate(getParaToInt("pageNo", 1), 10));
		System.out.println(getAttr("userList"));
//		render("blog.html");
		render("userList.html");
	}
	
	public void add() {
		
	}
	
	public void save() {
		getModel(UserInfo.class).save();
		redirect("/blog");
	}
	
	public void edit() {
		setAttr("blog", UserInfo.me.findById(getParaToInt()));
	}
	
	public void update() {
		getModel(UserInfo.class).update();
		redirect("/blog");
	}
	
	public void delete() {
		UserInfo.me.deleteById(getParaToInt());
		redirect("/blog");
	}
}



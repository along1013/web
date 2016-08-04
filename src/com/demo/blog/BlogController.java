package com.demo.blog;

import com.demo.common.model.UserInfo;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * BlogController
 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
@Before(BlogInterceptor.class)
public class BlogController extends Controller {
	public void index() {
		setAttr("blogPage", UserInfo.me.paginate(getParaToInt(0, 1), 10));
		render("blog.html");
	}
	
	public void add() {
	}
	
	@Before(BlogValidator.class)
	public void save() {
		getModel(UserInfo.class).save();
		redirect("/blog");
	}
	
	public void edit() {
		setAttr("blog", UserInfo.me.findById(getParaToInt()));
	}
	
	@Before(BlogValidator.class)
	public void update() {
		getModel(UserInfo.class).update();
		redirect("/blog");
	}
	
	public void delete() {
		UserInfo.me.deleteById(getParaToInt());
		redirect("/blog");
	}
}



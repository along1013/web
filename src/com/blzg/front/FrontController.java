package com.blzg.front;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.blzg.sms.SMSRedisService;
import com.demo.common.model.UserInfo;
import com.demo.common.model.ValueInfo;
import com.jfinal.core.Controller;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;

/**
 * 网站页面业务
 * @author Administrator
 *
 */
public class FrontController extends Controller{
	
	public void index(){
		render("website.html");
	}

	/**
	 * 主页面(包含关于白鹭、专业团队、联系我们)
	 */
	public void home(){
		render("website.html");
	}
	
	/**
	 * 企业活动
	 */
	public void active(){
		render("webactive.html");
	}
	
	/**
	 * 企业旅游
	 */
	public void outing(){
//		HttpSession session = getSession();
//		session.setAttribute("username", "");
		render("webouting.html");
	}
	
	/**
	 * 点击value导航判断是否已经存在session，用来判断是否弹出二维码
	 */
	public void checkSession(){
		HttpSession session = getSession();
		if(session.getAttribute("username")!=null){
			renderText("success");
			return;
		}
		renderText("fail");
	}
	
	/**
	 * 净值查询
	 */
	public void value(){
		HttpSession session = getSession();
		if(session.getAttribute("username")!=null){
			render("webvalue.html");
			return;
		}
		renderText("非法查看");
	}
	
	/**
	 * 分页获取净值表格的内容
	 */
	public void getValueInfo(){
		renderJson(ValueInfo.dao.paginate(getParaToInt("pageNo", 1), 6).getList());
	}
	
	/**
	 * 检查redis的二维码
	 */
	public void checkRedis(){
		String code = getPara("code");
		Cache cache = Redis.use();
		String value = cache.get(code);
		if(value!=null){
			//登录创建会话
			setSessionAttr("username", value);
			renderText(value);
			return;
		}
		renderText("");
	}
	
	
	//*****************************登录注册相关,暂时采用二维码方式,不需要了***************************
	
	//同意相关内容界面
	public void agree(){
		render("/index/agree.html");
	}
	
	/**
	 * 发送验证码
	 * @param phone
	 */
	public void sendCode(String phone){
		new SMSRedisService().setRedisSMS(phone);
	}
	
	
	public void login(){
		render("login.html");
	}
	
	public void loginCheck(){
		String phone = getAttrForStr("phone");
		String pwd = getAttrForStr("passWord");
		if(phone==null || pwd==null){
			renderText("参数不能为空");
			return;
		}
		UserInfo info = UserInfo.me.findFirst("select * from user_info where phone = '"+phone+"'");
		if(info!=null && pwd.equals(info.getPwd())){
			renderText("账号或者密码不正确");
			return;
		}
		//TODO 登录信息需要写入缓存
		
		renderText("success");
		return;
	}
	
	public void register(){
		render("register.html");
	}
	
	public void codeCheck(){
		String phone = getAttrForStr("phone");
		String code = getAttrForStr("code");
		if(phone.isEmpty()||code.isEmpty()){
			renderText("参数不能为空");
			return;
		}
		
	}
	
	public void registerCheck(){
		String phone = getAttrForStr("phone");
		String name = getAttrForStr("name");
		String idCard = getAttrForStr("idCard");
		String code = getAttrForStr("code");
		if(phone.isEmpty()||name.isEmpty()||idCard.isEmpty()||code.isEmpty()){
			renderText("参数不能为空");
			return;
		}
		if(UserInfo.me.isExistPhone(phone)){
			renderText("手机号码已存在");
			return;
		}
		renderText("success");
	}
}

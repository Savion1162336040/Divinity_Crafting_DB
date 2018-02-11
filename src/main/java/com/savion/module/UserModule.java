package com.savion.module;

import com.savion.bean.User;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpSession;

/**
 * @at可以声明在模块上也可以声明在模块的入口函数上
 * value跟上路径，未跟value则以filedname的小写作为路径,路径可以有多个如：
 * @At(value={"/user","/yonghu"})
 */
@IocBean
@At("/user")
@Ok("json")
@Fail("http:500")
public class UserModule {
	@Inject
	public Dao dao;
	@At("/count")
	public int getCount() {
		return dao.count(User.class);
	}
	/**
	 * 用户登陆接口@Param声明在入口函数的参数上，用于映射到访问地址参数
	 * @param name 用户名
	 * @param psd
	 * @return
	 */
	@At("/login")
	public boolean login(@Param("name")String name, @Param("psd")String psd, HttpSession session){
		User user1 = dao.fetch(User.class,Cnd.where("name","=",name).and("password","=",psd));
		// = dao.fetch(User.class, Cnd.where("name","=",name).and("password","=",psd));
		if (user1==null) {
			return false;
		}else{
			session.setAttribute("session", user1.getId());
			return true;
		}
	}
	@Ok(">>:/")
	@At
	public void logout(HttpSession session){
		session.invalidate();
	}
}

package cn.book.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.book.user.entities.User;
import cn.book.user.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//用户操作的action类
public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user = new User();
	private UserService userService;
	private String checkCode;


	@Override
	public User getModel() {
		return this.user;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	//跳转到注册页面
	public String registPage() {
		return "registPage";
	}

	//使用AJAX异步校验用户名
	public String findUserName() throws IOException {
		//调用service层
		User userJudge = userService.findUserName(user.getUsername());
		//获取response对象向页面输出内容
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charSet=UTF-8");
		if(userJudge != null){
			//查询到用户名
			response.getWriter().println("<font color='red'>用户名已存在！</font>");
		}else{
			//未查询到用户名
			if(!user.getUsername().matches("[a-zA-Z]\\w{5,}")) {
				response.getWriter().println("<font color='red'>用户名格式不正确,请以字母开头，长度不少于6位，不能包含下划线以外的特殊字符！</font>");
			}else{
				response.getWriter().println("<font color='green'>用户名可使用！</font>");
			}
		}
		return NONE;
	}

	//用户注册
	public String regist() {
		//判断验证码
		String TruecheckCode = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkCode.equalsIgnoreCase(TruecheckCode)) {
			this.addActionError("验证码输入错误！");
			return "registPage";
		}
		userService.save(user);
		this.addActionMessage("注册成功，请去邮箱中激活！");
		return "msg";
	}

	//用户激活
	public String active() {
		//根据激活码查询用户
		User existUser = userService.findCode(user.getCode());
		if(existUser == null) {
			this.addActionMessage("激活码错误！");
		}else {
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("激活成功！");
		}
		return "msg";
	}

	//跳到用户登录页面
	public String loginPage() {
		return "loginPage";
	}

	//用户登录
	public String login() {
		//判断验证码
		String TruecheckCode = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkCode.equalsIgnoreCase(TruecheckCode)) {
			this.addActionError("验证码输入错误！");
			return "loginPage";
		}
		User existUser = userService.login(user);
		if(existUser == null) {
			this.addActionError("用户名密码错误，或帐号未激活！");
			return LOGIN;
		}else {
			//将用户信息存入到Session
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			//跳转页面
			return "loginSuccess";
		}
	}

	//用户退出登录
	public String quit() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}




}

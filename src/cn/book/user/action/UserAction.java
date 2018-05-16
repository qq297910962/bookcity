package cn.book.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.book.user.entities.User;
import cn.book.user.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//�û�������action��
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

	//��ת��ע��ҳ��
	public String registPage() {
		return "registPage";
	}

	//ʹ��AJAX�첽У���û���
	public String findUserName() throws IOException {
		//����service��
		User userJudge = userService.findUserName(user.getUsername());
		//��ȡresponse������ҳ���������
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charSet=UTF-8");
		if(userJudge != null){
			//��ѯ���û���
			response.getWriter().println("<font color='red'>�û����Ѵ��ڣ�</font>");
		}else{
			//δ��ѯ���û���
			if(!user.getUsername().matches("[a-zA-Z]\\w{5,}")) {
				response.getWriter().println("<font color='red'>�û�����ʽ����ȷ,������ĸ��ͷ�����Ȳ�����6λ�����ܰ����»�������������ַ���</font>");
			}else{
				response.getWriter().println("<font color='green'>�û�����ʹ�ã�</font>");
			}
		}
		return NONE;
	}

	//�û�ע��
	public String regist() {
		//�ж���֤��
		String TruecheckCode = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkCode.equalsIgnoreCase(TruecheckCode)) {
			this.addActionError("��֤���������");
			return "registPage";
		}
		userService.save(user);
		this.addActionMessage("ע��ɹ�����ȥ�����м��");
		return "msg";
	}

	//�û�����
	public String active() {
		//���ݼ������ѯ�û�
		User existUser = userService.findCode(user.getCode());
		if(existUser == null) {
			this.addActionMessage("���������");
		}else {
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("����ɹ���");
		}
		return "msg";
	}

	//�����û���¼ҳ��
	public String loginPage() {
		return "loginPage";
	}

	//�û���¼
	public String login() {
		//�ж���֤��
		String TruecheckCode = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkCode.equalsIgnoreCase(TruecheckCode)) {
			this.addActionError("��֤���������");
			return "loginPage";
		}
		User existUser = userService.login(user);
		if(existUser == null) {
			this.addActionError("�û���������󣬻��ʺ�δ���");
			return LOGIN;
		}else {
			//���û���Ϣ���뵽Session
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			//��תҳ��
			return "loginSuccess";
		}
	}

	//�û��˳���¼
	public String quit() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}




}

package cn.book.interceptor;

import org.apache.struts2.ServletActionContext;

import cn.book.adminuser.entities.AdminUser;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

//��̨Ȩ��У��������
public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//�ж�session���Ƿ񱣴��˺�̨�û��ĵ�¼��Ϣ
		AdminUser existAdminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		if(existAdminUser == null) {
			ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();
			actionSupport.addActionError("���ȵ�¼��");
			return "loginFail";
		}else {
			return actionInvocation.invoke();
		}
	}

}

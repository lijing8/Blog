package springTest.web.auth;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthIntercepor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
			AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
			//û��������ҪȨ��,������������֤Ȩ��
			if(authPassport == null || authPassport.validate() < 100){
				return true;
			} else {                
				//������ʵ���Լ���Ȩ����֤�߼�
				if(null != request.getSession().getAttribute("admin")){ //�����֤�ɹ�����true������ֱ��дfalse��ģ����֤ʧ�ܵĴ���
					return true;
				}else{//�����֤ʧ��
					//���ص���¼����
					String jumpto = request.getServletPath();
					if(!StringUtils.isEmpty(request.getQueryString())){
						jumpto = jumpto + "?" + request.getQueryString();
					}
					response.sendRedirect("login?jumpto="+ URLEncoder.encode(jumpto, "utf-8") );
					return false;
				}       
			}
		}	else{
			return true;   
		}
	}


}

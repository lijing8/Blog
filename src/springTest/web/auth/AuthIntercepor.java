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
			//没有声明需要权限,或者声明不验证权限
			if(authPassport == null || authPassport.validate() < 100){
				return true;
			} else {                
				//在这里实现自己的权限验证逻辑
				if(null != request.getSession().getAttribute("admin")){ //如果验证成功返回true（这里直接写false来模拟验证失败的处理）
					return true;
				}else{//如果验证失败
					//返回到登录界面
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

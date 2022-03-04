package com.webtest.intercepter;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/toLogin");
            //request.setAttribute("msg","没有权限，请登录");
            //request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }
        return true;
    }

}
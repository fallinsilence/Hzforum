package cn.free.interceptor;

import cn.free.service.UserService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IPCheck implements HandlerInterceptor{

    @Resource
    private UserService service;

    //执行顺序：preHandle，postHandle，afterCompletion
    //preHandle在目标请求执行前执行这个方法
    //postHandle，afterCompletion在目标请求执行完，执行这两个方法
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //拦截客户端的请求，返回false，不执行拦截到的请求（要跳转的页面）；返回true，执行拦截到的请求
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

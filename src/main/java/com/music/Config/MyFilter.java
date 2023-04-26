package com.music.Config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Order(1) //表示如果有多个拦截器的话就是设置这个拦截器的运行等级，数字越小，越先执行。
@WebFilter(filterName = "MyFilter")
public class MyFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
        System.out.println("过滤器初始化");
    }

    public void destroy() {
        System.out.println("销毁");

    }

    @Override
    public void doFilter(ServletRequest Request, ServletResponse Response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) Request;
        HttpServletResponse response = (HttpServletResponse) Response;
        if (Request != null) {
            System.out.println("执行过滤器");

            String origin = ((HttpServletRequest) Request).getHeader("Origin");
            response.setHeader("Access-Control-Allow-Origin", origin);

            //允许跨域的请求方式
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE");

            //预检请求的间隔时间
            response.setHeader("Access-Control-Max-Age", "3600");

            //允许跨域请求携带的请求头
            response.setHeader("Access-Control-Allow-Headers", "x-auth-token,Origin,Access-Token,X-Requested-With,Content-Type, Accept,token");

            //若要返回cookie、携带session等信息则将此项设置我true
            response.setHeader("Access-Control-Allow-Credentials", "true");

            //简称为HSTS。它允许一个HTTPS网站，要求浏览器总是通过HTTPS来访问它
            response.setHeader("strict-transport-security", "max-age=16070400; includeSubDomains");

            //这个响应头主要是用来定义页面可以加载哪些资源，减少XSS的发生
            response.setHeader("Content-Security-Policy", "default-src 'self'; script-src 'self'; frame-ancestors 'self'; object-src 'none'");

            //互联网上的资源有各种类型，通常浏览器会根据响应头的Content-Type字段来分辨它们的类型。通过这个响应头可以禁用浏览器的类型猜测行为
            response.setHeader("X-Content-Type-Options", "nosniff");

            //1; mode=block：启用XSS保护，并在检查到XSS攻击时，停止渲染页面
            response.setHeader("X-XSS-Protection", "1; mode=block");

            //SAMEORIGIN：不允许被本域以外的页面嵌入
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
        }
        assert request != null;
        String url = request.getRequestURI();
        String method = request.getMethod();
        System.out.println("请求的接口:" + url + "  " + "请求的方式:" + method);
        chain.doFilter(request, response);
    }
}

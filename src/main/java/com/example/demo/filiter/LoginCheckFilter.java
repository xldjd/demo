package com.example.demo.filiter;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.BaseContent;
import com.example.demo.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = " LoginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        log.info("过滤登入请求：{}", request.getRequestURI());

        String requestURI = request.getRequestURI();
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        };
boolean check=check(urls,requestURI);

if (check)
{
    log.info("本次请求{}不需要处理",requestURI);
    filterChain.doFilter(request,response);
    return;
}
//1
if (request.getSession().getAttribute("employee")!=null) {


  Long emid= (Long) request.getSession().getAttribute("employee");
 BaseContent.setcurrentid(emid);
 filterChain.doFilter(request,response);
return;
}
//2
        if (request.getSession().getAttribute("user")!=null) {


            Long emid= (Long) request.getSession().getAttribute("user");
            BaseContent.setcurrentid(emid);
            filterChain.doFilter(request,response);
            return;}


response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
return;

}

    /**
     * 路径匹配，检查本次请求是否需要放行
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls,String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }

}
package me.tomo.server.interceptors;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http://www.mkyong.com/spring-mvc/spring-mvc-handler-interceptors-example/
 *
 */
public class ExecuteTimeInterceptor extends HandlerInterceptorAdapter {

    private static final String SYSTEM_EXECUTE_TIME = "system.execute_time";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        request.setAttribute(SYSTEM_EXECUTE_TIME, System.currentTimeMillis());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        long executeTime = (Long) request.getAttribute(SYSTEM_EXECUTE_TIME);
        request.removeAttribute(SYSTEM_EXECUTE_TIME);

        long nowTime = System.currentTimeMillis();
        if (nowTime - executeTime > 200l) {
            System.out.println("View Rendered - executeTime: " + (nowTime - executeTime));
        }
    }
}

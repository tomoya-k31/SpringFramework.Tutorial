package me.tomo.server;

import me.tomo.server.config.WebMvcConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * どこにも呼び出しの記述が存在しないが、SpringServletContainerInitializerが自動で呼んでくれる
 *
 * web.xmlに
 * `"http://java.sun.com/xml/ns/javaee" is set to "http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"`を追加すれば
 * ↑
 * 嘘、
 *
 * Created by usr0200379 on 15/10/01.
 */
public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebMvcConfig.class);
        ctx.setServletContext(servletContext);

        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        dynamic.addMapping("/");
        dynamic.setLoadOnStartup(1);
    }

}

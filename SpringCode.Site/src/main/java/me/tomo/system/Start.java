package me.tomo.system;

import me.tomo.server.AppInitializer;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.Servlet;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;

/**
 * Created by usr0200379 on 15/10/01.
 */
public class Start {

    private static Tomcat tomcat;

    public static void main(String[] args) throws ServletException, LifecycleException {
        tomcat = new Tomcat();
        tomcat.setPort(8080);

        // ↓を設定しないとmasterディレクトリに"tomcat.8080"フォルダが作成され、そこにコンパイルされたjspクラスが書き出される
        //  設定すれば、指定フォルダ配下に"work"ディレクトリが作成される
        tomcat.setBaseDir("../SpringCode.Site/");

        // WEB-INFが存在する場合は、↓のようにWEB-INFの親ディレクトリへのパスを指定
        File base = new File("../SpringCode.Site/web");
        Context ctx = tomcat.addWebapp("/web", base.getAbsolutePath());
        ServletContext sc = ctx.getServletContext();
//        File base = new File("../SpringCode.Site/src/main/java");
//        Context context = tomcat.addContext("/web", base.getAbsolutePath());

        tomcat.start();
        Runtime.getRuntime().addShutdownHook(new Thread(Start::stop));
        tomcat.getServer().await();
    }

    public static void stop() {
        try {
            if (tomcat == null) return;
            if (!tomcat.getServer().getState().isAvailable()) return;

            tomcat.getServer().stop();

            // 古いクラスが残る可能性があるので、Tomcat終了のタイミングで消してしまう
            FileUtils.deleteDirectory(new File("../SpringCode.Site/work"));
            FileUtils.deleteDirectory(new File("../SpringCode.Site/tomcat.8080"));

        } catch (LifecycleException | IOException e) {
            e.printStackTrace();
        }
    }


    private static void configureSimulatedWebXml(final Context context) {

        /*
        context.setDisplayName("Sandbox Web Application");

        context.addParameter("org.apache.tiles.impl.BasicTilesContainer.DEFINITIONS_CONFIG", "/WEB-INF/tiles-defs.xml,/WEB-INF/tiles-sandbox.xml");

        final FilterDef struts2Filter = new FilterDef();
        struts2Filter.setFilterName("struts2");
        struts2Filter.setFilterClass("org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter");
        struts2Filter.addInitParameter("actionPackages", "ca.statcan.icos.sandbox.web");
        context.addFilterDef(struts2Filter);

        final FilterMap struts2FilterMapping = new FilterMap();
        struts2FilterMapping.setFilterName("struts2");
        struts2FilterMapping.addURLPattern("/*");
        context.addFilterMap(struts2FilterMapping);

        context.addApplicationListener("org.apache.tiles.web.startup.TilesListener");
        context.addApplicationListener("ca.statcan.icos.sandbox.EmbeddedContextLoaderListener");

        context.addWelcomeFile("index.jsp");
         */

        // ApplicationContextをServlet側から取得したい時に記述する必要あり
//        context.addApplicationListener("org.springframework.web.context.ContextLoaderListener");
    }
}

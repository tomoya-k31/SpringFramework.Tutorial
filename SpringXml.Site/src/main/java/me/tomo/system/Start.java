package me.tomo.system;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;

/**
 * Created by usr0200379 on 15/10/02.
 */
public class Start {

    private static Tomcat tomcat;

    public static void main(String[] args) throws LifecycleException, ServletException {
        tomcat = new Tomcat();
        tomcat.setPort(8080);

        // ↓を設定しないとmasterディレクトリに"tomcat.8080"フォルダが作成され、そこにコンパイルされたjspクラスが書き出される
        //  設定すれば、指定フォルダ配下に"work"ディレクトリが作成される
        tomcat.setBaseDir("../SpringXml.Site/");

        // baseの/WEB-INF/web.xmlが自動で呼ばれる
        File base = new File("../SpringXml.Site/web");
        Context context = tomcat.addWebapp("/web", base.getAbsolutePath());


//        Context webContext = tomcat.addWebapp("/yourContextPath", "/web/app/docroot/");
//        webContext.getServletContext().setAttribute(Globals.ALT_DD_ATTR, "/path/to/custom/web.xml");

//        Tomcat.addServlet(context, "default", new DefaultServlet()).addMapping("/");

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
            FileUtils.deleteDirectory(new File("../SpringXml.Site/work"));
            FileUtils.deleteDirectory(new File("../SpringXml.Site/tomcat.8080"));

        } catch (LifecycleException|IOException e) {
            e.printStackTrace();
        }
    }
}

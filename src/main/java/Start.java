import org.apache.wicket.util.time.Duration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {
    public static void main(String[] args) throws Exception {
            startSpring();

//        Server server = new Server();
//        ServerConnector connector = new ServerConnector(server);
//
//        connector.setIdleTimeout(3600000);
//        connector.setPort(8080);
//        server.addConnector(connector);
//
        WebAppContext contextHandler = new WebAppContext();
//        contextHandler.setServer(server);
//        contextHandler.setContextPath("/");
//        contextHandler.setWar("src/main/webapp");
//
//        server.setHandler(contextHandler);
//
//        server.start();
//        server.join();
    }

    private static void startSpring() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "applicationContext.xml"});
        context.getBean(Server.class).start();
    }
}

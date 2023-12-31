package protocol.http;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

/**
 * <?xml version="1.0" encoding="UTF-8"?>
 * <Server port="8005" shutdown="SHUTDOWN">
 *     <Service name="Catalina">
 *         <Connector port="8080" protocol="HTTP/1.1"
 *                    connectionTimeout="20000"
 *                    redirectPort="8443" />
 *
 *         <Engine name="Catalina" defaultHost="localhost">
 *             <Host name="localhost" appBase="webapps"
 *                   unpackWARs="true" autoDeploy="true">
 *                 <Context path="" docBase=""
 *                          reloadable="true" crossContext="true" />
 *             </Host>
 *         </Engine>
 *     </Service>
 * </Server>
 */

public class HttpServer {
    public void start(String hostName, Integer port){
        Tomcat tomcat = new Tomcat();

        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(port);

        StandardEngine engine = new StandardEngine();
        engine.setDefaultHost(hostName);

        StandardHost host = new StandardHost();
        host.setName(hostName);

        String contextPath = "";
        StandardContext context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        host.addChild(context);
        engine.addChild(host);

        service.setContainer(engine);
        service.addConnector(connector);

        tomcat.addServlet(contextPath, "dispatcher", new DispatcherServlet());
        context.addServletMappingDecoded("/*", "dispatcher");

        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}

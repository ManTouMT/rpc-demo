package com.lxy.protocol;

import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

public class HttpServer {
    public void start(String hostname, int port) {
        String contextPath = "";
        Context context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        Host host = new StandardHost();
        host.setName(hostname);
        host.addChild(context);

        Engine engine = new StandardEngine();
        engine.setDefaultHost("localhost");
        engine.addChild(host);

        Tomcat tomcat = new Tomcat();
        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");
        service.setContainer(engine);

        Connector connector = new Connector();
        connector.setPort(port);
        service.addConnector(connector);

        try {
            tomcat.start();
        } catch (LifecycleException e) {
            throw new RuntimeException("Tomcat start failed", e);
        }
    }
}

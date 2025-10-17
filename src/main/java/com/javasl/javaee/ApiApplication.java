package com.javasl.javaee;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Wildfly will use src/main/webapp/WEB-INF/jboss-web.xml to set the context-root to /.
 * If deployed on wildfly, you can access the api on the locahost:8080 via
 * http://localhost:8080/api, for other application servers you may need to change the
 * name to ROOT.war or set the path in the configuration.
 */
@ApplicationPath("/api")
public class ApiApplication extends Application {

}

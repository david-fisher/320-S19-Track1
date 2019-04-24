package com.mcnz.rest_tomcat_eclipse;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class ScoreApplication extends ResourceConfig {
	public ScoreApplication() {
		packages("com.mcnz.rest_tomcat_eclipse");
	}

}

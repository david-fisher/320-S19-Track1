package com.MembersOnly;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
@ApplicationPath("/")
public class MembersOnlyApplication extends ResourceConfig { 
	public MembersOnlyApplication() {
		packages("com.MembersOnly");
		packages("com.Model2");
	}

}

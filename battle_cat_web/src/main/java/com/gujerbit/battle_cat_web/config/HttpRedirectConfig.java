package com.gujerbit.battle_cat_web.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpRedirectConfig {

	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
			protected void postProcessContext(Context context) {
				SecurityConstraint security = new SecurityConstraint();
				security.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				security.addCollection(collection);
				context.addConstraint(security);
			};
		};
		
		tomcat.addAdditionalTomcatConnectors(createSSLConnector());
		
		return tomcat;
	}
	
	private Connector createSSLConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setPort(80);
		connector.setScheme("http");
		connector.setSecure(false);
		connector.setRedirectPort(443);
		
		return connector;
	}
	
}

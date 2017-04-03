package com.example;

import java.io.IOException;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.tuckey.web.filters.urlrewrite.Conf;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

public class BootUrlRewriteFilter extends UrlRewriteFilter {
	
	private static final String CONFIG_LOCATION = "classpath:/urlrewrite.xml";
	
	//Inject the Resource from the given location
    @Value(CONFIG_LOCATION)
    private Resource resource;

	@Override
	protected void loadUrlRewriter(FilterConfig filterConfig) throws ServletException {
        //Create a UrlRewrite Conf object with the injected resource
        Conf conf;
		try {
			conf = new Conf(filterConfig.getServletContext(), resource.getInputStream(), resource.getFilename(), "@@yourOwnSystemId@@");
			checkConf(conf);
		} catch (IOException ex) {
			throw new ServletException("Unable to load URL rewrite configuration file from " + CONFIG_LOCATION, ex);
		}
        
	}
    
    

}

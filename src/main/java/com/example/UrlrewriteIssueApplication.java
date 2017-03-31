package com.example;

import javax.servlet.DispatcherType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

@SpringBootApplication
@EnableZuulProxy
public class UrlrewriteIssueApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlrewriteIssueApplication.class, args);
	}
	
	@Bean
    public FilterRegistrationBean rewriteFilterConfig() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setName("rewriteFilter");
        filter.setFilter(new UrlRewriteFilter());
        filter.addInitParameter("confPath", "urlrewrite.xml");
//        filter.addInitParameter("confReloadCheckInterval", "1");
        filter.addInitParameter("logLevel", "debug");
//        filter.addInitParameter("statusPath", "/redirect");
//        filter.addInitParameter("statusEnabledOnHosts", "*");
//        filter.addInitParameter("logLevel", "WARN");
        filter.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD);
        filter.setOrder(3);
        return filter;
    }
}

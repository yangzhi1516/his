package com.his.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//配置过滤器，解决跨域问题
@Configuration
public class CorsConfig {
	 private CorsConfiguration buildConfig() {
	        CorsConfiguration corsConfiguration = new CorsConfiguration();
	        corsConfiguration.addAllowedOrigin("http://192.168.8.9:8081"); //允许任何域名使用
	        corsConfiguration.addAllowedHeader("*"); //允许任何头
	        corsConfiguration.addAllowedMethod("*"); //允许任何方法（post、get等）
	        corsConfiguration.setAllowCredentials(true);//允许携带cookie
	        return corsConfiguration;
	    }


	    @Bean
	    public CorsFilter corsFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", buildConfig());
	        return new CorsFilter(source);
	    }

}

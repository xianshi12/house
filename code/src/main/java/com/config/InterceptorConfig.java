package com.config;

import java.io.File;
import java.io.UncheckedIOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.interceptor.AuthorizationInterceptor;
import com.utils.UploadUtils;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport{

	@Value("${file.upload-dir:src/main/resources/static/upload}")
	private String uploadDir;

	@Bean
    public AuthorizationInterceptor getAuthorizationInterceptor() {
        return new AuthorizationInterceptor();
    }

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAuthorizationInterceptor()).addPathPatterns("/**")
			.excludePathPatterns("/static/**", "/upload/**");
        super.addInterceptors(registry);
	}

	/**
	 * springboot 2.0配置WebMvcConfigurationSupport之后，会导致默认配置被覆盖，要访问静态资源需要重写addResourceHandlers方法
	 * 上传文件落在磁盘（见 file.upload-dir），须单独映射 /upload/**，否则仅 classpath 下无新文件会导致头像、合同等 404。
	 */
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String uploadLocation = resolveUploadDirectoryAsResourceLocation();
		registry.addResourceHandler("/upload/**").addResourceLocations(uploadLocation);
		registry.addResourceHandler("/**")
        .addResourceLocations("classpath:/resources/")
        .addResourceLocations("classpath:/static/")
        .addResourceLocations("classpath:/admin/")
        .addResourceLocations("classpath:/front/")
        .addResourceLocations("classpath:/front-pc/")
        .addResourceLocations("classpath:/public/");
		super.addResourceHandlers(registry);
    }

	private String resolveUploadDirectoryAsResourceLocation() {
		try {
			return UploadUtils.toResourceLocation(UploadUtils.resolveUploadDir(uploadDir));
		} catch (java.io.IOException e) {
			throw new UncheckedIOException("Cannot resolve upload directory for static mapping", e);
		}
	}
}

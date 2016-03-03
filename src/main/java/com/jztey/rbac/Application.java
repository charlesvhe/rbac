package com.jztey.rbac;

import com.jztey.framework.mvc.PermissionInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.handler.MappedInterceptor;

/**
 * Created by Charles on 2016/2/27.
 */

@SpringBootApplication
@Import({ApplicationCaching.class})
public class Application extends SpringBootServletInitializer {
    @Bean
    public PermissionInterceptor permissionInterceptor() {
        return new PermissionInterceptor();
    }

    @Bean
    public MappedInterceptor mappedPermissionInterceptor() {
        return new MappedInterceptor(new String[]{"/api/**"}, permissionInterceptor());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

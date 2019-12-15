package cs.stockapp.configuration;

import cs.stockapp.interceptor.SecurityInterceptor;
import cs.stockapp.mapping.ActionsMappings;
import cs.stockapp.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SessionService sessionService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityInterceptor(sessionService)).excludePathPatterns(ActionsMappings.LOGIN_MAPPING);
    }
}

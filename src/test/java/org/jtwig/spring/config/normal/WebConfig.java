package org.jtwig.spring.config.normal;

import org.jtwig.spring.JtwigViewResolver;
import org.jtwig.spring.controller.SampleController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@Import(SampleController.class)
public class WebConfig {
    private static final Logger log = LoggerFactory.getLogger(WebConfig.class);
    @Bean
    public ViewResolver viewResolver () {
        log.info("WebConfig");
        JtwigViewResolver jtwigViewResolver = new JtwigViewResolver();
        jtwigViewResolver.setPrefix("classpath:/templates/");
        jtwigViewResolver.setSuffix(".twig");
        return jtwigViewResolver;
    }
}

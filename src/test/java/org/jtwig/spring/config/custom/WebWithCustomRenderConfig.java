package org.jtwig.spring.config.custom;

import org.jtwig.environment.DefaultEnvironmentConfiguration;
import org.jtwig.spring.JtwigViewResolver;
import org.jtwig.spring.controller.SampleController;
import org.jtwig.web.servlet.JtwigRenderer;
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
public class WebWithCustomRenderConfig {
    private static final Logger log = LoggerFactory.getLogger(WebWithCustomRenderConfig.class);

    @Bean
    public ViewResolver viewResolver () {
        log.info("WebWithCustomRenderConfig");
        JtwigViewResolver jtwigViewResolver = new JtwigViewResolver();
        jtwigViewResolver.setPrefix("classpath:/templates/");
        jtwigViewResolver.setSuffix(".twig");
        jtwigViewResolver.setRenderer(new JtwigRenderer(new DefaultEnvironmentConfiguration()));
        return jtwigViewResolver;
    }
}

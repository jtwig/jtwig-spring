package org.jtwig.spring.config;

import org.jtwig.spring.JtwigViewResolver;
import org.jtwig.spring.controller.SampleController;
import org.jtwig.web.servlet.JtwigRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.jtwig.environment.EnvironmentConfigurationBuilder.configuration;

@Configuration
@EnableWebMvc
@Import(SampleController.class)
public class WebConfig {
    @Bean
    public ViewResolver viewResolver () {
        JtwigViewResolver jtwigViewResolver = new JtwigViewResolver();
        jtwigViewResolver.setRenderer(new JtwigRenderer(configuration().build()));
        jtwigViewResolver.setPrefix("classpath:/templates/");
        jtwigViewResolver.setSuffix(".twig");
        return jtwigViewResolver;
    }
}

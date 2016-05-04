package org.jtwig.spring.config.prefix;

import org.jtwig.spring.JtwigViewResolver;
import org.jtwig.spring.controller.SampleController;
import org.jtwig.spring.prefix.ThemePrefixResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.theme.FixedThemeResolver;

@Configuration
@EnableWebMvc
@Import(SampleController.class)
public class WebWithPrefixConfig {
    private static final Logger log = LoggerFactory.getLogger(WebWithPrefixConfig.class);
    @Bean
    public ThemeResolver themeResolver () {
        FixedThemeResolver themeResolver = new FixedThemeResolver();
        themeResolver.setDefaultThemeName("theme");
        return themeResolver;
    }

    @Bean
    public ViewResolver viewResolver () {
        log.info("WebConfig");
        ThemePrefixResolver prefixResolver = new ThemePrefixResolver(themeResolver());
        JtwigViewResolver jtwigViewResolver = new JtwigViewResolver();
        jtwigViewResolver.setPrefix("classpath:/templates/");
        jtwigViewResolver.setSuffix(".twig");
        jtwigViewResolver.setPrefixResolver(prefixResolver);
        return jtwigViewResolver;
    }
}

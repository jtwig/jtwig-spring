package org.jtwig.spring;

import org.jtwig.spring.prefix.DefaultPrefixResolver;
import org.jtwig.spring.prefix.PrefixResolver;
import org.jtwig.web.servlet.JtwigRenderer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;

public class JtwigViewResolver extends AbstractTemplateViewResolver implements ViewResolver, InitializingBean {
    private JtwigRenderer renderer;
    private PrefixResolver prefixResolver = DefaultPrefixResolver.instance();

    public JtwigViewResolver() {
        setViewClass(JtwigView.class);
    }

    @Override
    protected Class<?> requiredViewClass() {
        return JtwigView.class;
    }

    public void setRenderer(JtwigRenderer renderer) {
        this.renderer = renderer;
    }

    public void setPrefixResolver(PrefixResolver prefixResolver) {
        this.prefixResolver = prefixResolver;
    }

    @Override
    protected JtwigView buildView(String viewName) throws Exception {
        JtwigView view = (JtwigView) super.buildView(viewName);
        view.setRenderer(renderer);
        return view;
    }

    @Override
    protected String getPrefix() {
        return prefixResolver.resolve(super.getPrefix());
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if (renderer == null) {
            renderer = JtwigRenderer.defaultRenderer();
        }
    }
}

package org.jtwig.spring;

import org.jtwig.web.servlet.JtwigRenderer;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;

public class JtwigViewResolver extends AbstractTemplateViewResolver implements ViewResolver {
    private JtwigRenderer renderer;

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

    @Override
    protected JtwigView buildView(String viewName) throws Exception {
        JtwigView view = (JtwigView) super.buildView(viewName);
        view.setRenderer(renderer);
        return view;
    }
}

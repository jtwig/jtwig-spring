package org.jtwig.spring;

import org.jtwig.web.servlet.JtwigRenderer;
import org.springframework.web.servlet.view.AbstractTemplateView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JtwigView extends AbstractTemplateView {
    private JtwigRenderer renderer;

    @Override
    protected void renderMergedTemplateModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderer.dispatcherFor(getUrl())
                .with(model)
                .render(request, response);
    }

    public void setRenderer(JtwigRenderer renderer) {
        this.renderer = renderer;
    }

}

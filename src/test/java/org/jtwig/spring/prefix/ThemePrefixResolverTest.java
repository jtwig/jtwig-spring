package org.jtwig.spring.prefix;

import org.junit.Test;
import org.springframework.web.servlet.ThemeResolver;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ThemePrefixResolverTest {
    private final ThemeResolver themeResolver = mock(ThemeResolver.class);
    private final HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
    private ThemePrefixResolver underTest = new ThemePrefixResolver(themeResolver) {
        @Override
        protected HttpServletRequest getRequest() {
            return httpServletRequest;
        }
    };

    @Test
    public void resolve() throws Exception {
        String staticPrefix = "classpath:/templates/";
        when(themeResolver.resolveThemeName(httpServletRequest)).thenReturn("theme");

        String result = underTest.resolve(staticPrefix);

        assertThat(result, is("classpath:/templates/theme/"));
    }
}
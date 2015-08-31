package org.jtwig.spring.prefix;

import org.jtwig.web.servlet.ServletRequestHolder;
import org.springframework.web.servlet.ThemeResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class ThemePrefixResolver implements PrefixResolver {
    private final ThemeResolver themeResolver;

    public ThemePrefixResolver(ThemeResolver themeResolver) {
        this.themeResolver = themeResolver;
    }

    @Override
    public String resolve(String staticPrefix) {
        String themeName = themeResolver.resolveThemeName(getRequest());
        String path = new File(staticPrefix, themeName).getPath();
        if (staticPrefix.endsWith(File.separator) && !path.endsWith(File.separator)) {
            return path + File.separator;
        } else {
            return path;
        }
    }

    protected HttpServletRequest getRequest() {
        return ServletRequestHolder.get();
    }
}

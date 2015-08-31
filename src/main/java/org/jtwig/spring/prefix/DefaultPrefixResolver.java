package org.jtwig.spring.prefix;

public class DefaultPrefixResolver implements PrefixResolver {
    private static final DefaultPrefixResolver instance = new DefaultPrefixResolver();

    public static PrefixResolver instance () {
        return instance;
    }

    private DefaultPrefixResolver () {}

    @Override
    public String resolve(String staticPrefix) {
        return staticPrefix;
    }
}

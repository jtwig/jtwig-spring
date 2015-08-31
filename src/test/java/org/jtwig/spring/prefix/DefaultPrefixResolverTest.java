package org.jtwig.spring.prefix;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DefaultPrefixResolverTest {
    private PrefixResolver underTest = DefaultPrefixResolver.instance();

    @Test
    public void resolve() throws Exception {
        String prefix = "prefix";

        String result = underTest.resolve(prefix);

        assertThat(result, is(prefix));
    }
}
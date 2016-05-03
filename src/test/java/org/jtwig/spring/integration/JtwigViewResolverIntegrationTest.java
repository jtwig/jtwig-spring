package org.jtwig.spring.integration;

import org.apache.http.client.fluent.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.FileResource;
import org.jtwig.spring.config.normal.WebConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class JtwigViewResolverIntegrationTest {
    private static final int DEFAULT_PORT = 12912;
    private static final String CONTEXT_PATH = "/";
    private static final String MAPPING_URL = "/*";
    private Server server;

    private void startJetty() throws Exception {
        server = new Server(DEFAULT_PORT);
        server.setHandler(getServletContextHandler(getContext()));
        server.start();
    }

    private static ServletContextHandler getServletContextHandler(WebApplicationContext context) throws IOException, URISyntaxException {
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setErrorHandler(null);
        contextHandler.setContextPath(CONTEXT_PATH);
        contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL);
        contextHandler.addEventListener(new ContextLoaderListener(context));
        contextHandler.setBaseResource(new FileResource(new File("src/test/webapp").getAbsoluteFile().toURI().toURL()));
        return contextHandler;
    }

    private static WebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(WebConfig.class.getPackage().getName());
        return context;
    }

    @Before
    public void setUp() throws Exception {
        startJetty();
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void example() throws Exception {
        String result = Request.Get(String.format("http://localhost:%d/", DEFAULT_PORT))
                .execute()
                .returnContent()
                .asString();

        assertThat(result, is("Hello Jtwig!"));
    }
}
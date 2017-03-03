package qq.upyachka.js.contest.platform.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import static qq.upyachka.js.contest.core.constants.UrlConst.RESOURCES_PATH_URL;
import static qq.upyachka.js.contest.core.constants.UrlConst.RESOURCES_URL_PATTERN;
import static qq.upyachka.js.contest.platform.configuration.constants.ConfigurationConst.JS_ENGINE;

/**
 * Created on 23.02.17.
 * @author upyachka.
 */
@Configuration
@EnableWebMvc
public class RootConfiguration extends WebMvcConfigurerAdapter {

    /** Internal logger. */
    private static final Logger LOG = LogManager.getLogger(RootConfiguration.class);

    /** Name of Rhino engine. */
    private static final String RHINO_NAME = "rhino";

/*
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp();
    }
*/

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler(RESOURCES_URL_PATTERN).addResourceLocations(RESOURCES_PATH_URL);
    }

    /**
     * Script engine to execute JS from client.
     * @return nashorn engine.
     */
    @Bean(JS_ENGINE)
    public ScriptEngine jsEngine() {
        // TODO provide name of engine through properties.
        return new ScriptEngineManager().getEngineByName(RHINO_NAME);
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}

package qq.upyachka.js.contest.platform.configuration;

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
import static qq.upyachka.js.contest.platform.configuration.constants.ConfigurationConst.RHINO_NAME;

/**
 * Created on 23.02.17.
 * @author upyachka.
 */
@Configuration
@EnableWebMvc
public class RootConfiguration extends WebMvcConfigurerAdapter {

    /**
     * Script interpreter to execute Python scripts from client.
     * @return engine.
     * @see <a href="http://bugs.jython.org/issue2355">ImportError:
     * Cannot import site module and its dependencies: No module named site</a>.
     */
/*    @Bean(JYTHON_NAME)
    public static PythonInterpreter pythonEngine() {
        return new PythonInterpreter();
    }*/
    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /** Internal logger. */
    //private static final Logger LOG = LogManager.getLogger(RootConfiguration.class);

/*
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp();
    }
*/
/*    {
        Properties properties = new Properties();
        properties.put("python.import.site","false");
        System.setProperty("python.import.site","false");
        PythonInterpreter.initialize(System.getProperties(), properties, new String[0]);
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
    @Bean(RHINO_NAME)
    public ScriptEngine jsEngine() {
        // TODO provide name of engine through properties.
        return new ScriptEngineManager().getEngineByName(RHINO_NAME);
    }

/*    public static void main(String[] args) {

        // Create an instance of the PythonInterpreter
        PythonInterpreter interp = new PythonInterpreter();

        // The exec() method executes strings of code
        interp.exec("import sys");
        interp.exec("print sys");

        // Set variable values within the PythonInterpreter instance
        interp.set("a", new PyInteger(42));
        interp.exec("print a");
        interp.exec("x = 2+2");

        // Obtain the value of an object from the PythonInterpreter and store it
        // into a PyObject.
        PyObject x = interp.get("x");
        System.out.println("x: " + x);
    }*/
}

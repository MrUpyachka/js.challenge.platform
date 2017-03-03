package qq.upyachka.js.challenge.scheduler.configuration;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Adds autowiring support to quartz jobs.
 * @see <a href="https://github.com/davidkiss/spring-boot-quartz-demo/blob/master/src/main/java/com/kaviddiss/bootquartz/spring/AutowiringSpringBeanJobFactory.java">
 * spring-boot-quartz-demo</a>
 */
@Component
public class AutoSpringBeanJobFactory extends SpringBeanJobFactory {

    /** Context to be used for job initialization. */
    @Autowired
    private ApplicationContext context;

    /** Bean factory to be used for job creation. */
    private transient AutowireCapableBeanFactory beanFactory;

    @PostConstruct
    public void initializeBeanFactory() {
        beanFactory = context.getAutowireCapableBeanFactory();
    }

    @Override
    protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
        final Object job = super.createJobInstance(bundle);
        beanFactory.autowireBean(job);
        return job;
    }
}

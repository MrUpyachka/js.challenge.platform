package qq.upyachka.js.challenge.scheduler.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import qq.upyachka.js.challenge.core.constants.ConfigurationConst;
import qq.upyachka.js.challenge.scheduler.job.ScriptExecutionJob;

import javax.annotation.PreDestroy;

import static qq.upyachka.js.challenge.scheduler.SchedulerConst.*;

/**
 * Configuration of scheduler and related functionality.
 * Created on 01.03.17.
 * @author upyachka.
 */
@Configuration
public class SchedulerConfiguration {

    /** Internal logger. */
    private static final Logger LOG = LogManager.getLogger(SchedulerConfiguration.class);

    /** Scheduler to be used by platform. */
    private static Scheduler scheduler;

    /**
     * Defines bean for job details factory.
     * @return factory of job details.
     */
    @Bean(JS_EXECUTOR_FACTORY)
    @Scope(ConfigurationConst.PROTOTYPE_SCOPE)
    public JobDetailFactoryBean jobDetailFactoryBean(){
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        factory.setJobClass(ScriptExecutionJob.class);
        factory.setGroup(JOB_GROUP);
        factory.setName(JOB_NAME_PREFIX);
        return factory;
    }

    /**
     * Defines factory of schedulers to run jobs.
     * @return schedulers factory.
     */
    @Bean
    public SchedulerFactory schedulerFactory() {
        return new StdSchedulerFactory();
    }

    /**
     * Defines scheduler for platform jobs.
     * @param factory {@link #schedulerFactory()}.
     * @return started scheduler.
     * @throws SchedulerException in case of configuration issues.
     */
    @Bean(SCHEDULER)
    public Scheduler scheduler(SchedulerFactory factory, AutoSpringBeanJobFactory jobFactory) throws SchedulerException {
        scheduler = factory.getScheduler();
        scheduler.setJobFactory(jobFactory);
        scheduler.start();
        return scheduler;
    }

    @PreDestroy
    public void stopScheduler() {
        LOG.debug("Stop scheduler on destroy.");
        try {
            if (scheduler != null && scheduler.isStarted()) {
                // TODO investigate scheduler issue
                // FIXME scheduler.shutdown(false);
            }
        } catch (SchedulerException e) {
            LOG.warn("Failed to stop scheduler: {}", e);
        }
    }
}

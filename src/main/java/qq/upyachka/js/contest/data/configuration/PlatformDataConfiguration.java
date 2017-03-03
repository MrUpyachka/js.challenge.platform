package qq.upyachka.js.contest.data.configuration;

import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

/**
 * General configuration for platform.
 * Created on 24.02.17.
 * @author upyachka.
 */
@Configuration
@EnableJpaRepositories(PlatformDataConfiguration.REPOSITORIES_PACKAGE)
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class PlatformDataConfiguration {

    /** Path to entity repositories. */
    protected static final String REPOSITORIES_PACKAGE = "qq.upyachka.js.contest";

    @Value("${platform.db.url}")
    private String dbUrl;

    @Value("${platform.db.username}")
    private String dbUser;

    @Value("${platform.db.password}")
    private String dbUserPassword;

    @Value("${platform.db.dialect}")
    private String dbDialect;

    @Bean
    public SimpleDriverDataSource driverManagerDataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbUserPassword);
        dataSource.setDriverClass(Driver.class);
        return dataSource;
    }

    @Bean
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        final HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform(dbDialect);
        return adapter;
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory(SimpleDriverDataSource dataSource,
                                                     HibernateJpaVendorAdapter adapter) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(adapter);
        emf.setPackagesToScan("qq.upyachka.js.contest");
        emf.setPersistenceUnitName("default");   // <- giving 'default' as name
        emf.afterPropertiesSet();
        return emf.getObject();
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory) {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(factory);
        return tm;
    }
}

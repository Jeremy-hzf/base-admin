package cn.huanzi.qch.baseadmin.config.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huzhengfa
 * @Description 从数据源配置
 * @create 2020-10-26 6:33 下午
 */
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        //实体管理
//        entityManagerFactoryRef="entityManagerFactorySecondary",
//        //事务管理
//        transactionManagerRef="transactionManagerSecondary",
//        //实体扫描,设置Repository所在位置
//        basePackages= { "cn.huanzi.qch.baseadmin.repository.dataquery","cn.huanzi.qch.baseadmin.common" })
public class SecondConfig {

    @Autowired
    private Environment env;
    @Autowired
    @Qualifier("secondaryDataSource")
    private DataSource secondaryDataSource;

    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    private HibernateProperties hibernateProperties;

    private Map<String, Object> getVendorProperties() {
//        Map<String, String> properties = new HashMap<>(16);
//        properties.put("hibernate.hbm2ddl.auto", "update");
//        properties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
//        properties.put("hibernate.format_sql", env.getProperty("spring.jpa.hibernate.format_sql"));
//        properties.put("hibernate.dialect", env.getProperty("spring.jpa.hibernate.primary-dialect"));
//        properties.put("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
//        jpaProperties.setProperties(properties);
        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }

    @Bean(name = "entityManagerSecondary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySecondary(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactorySecondary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(secondaryDataSource)
                .packages("cn.huanzi.qch.baseadmin.pojo.dataquery") //设置实体类所在位置
                .persistenceUnit("secondaryPersistenceUnit")
                .properties(getVendorProperties())
                .build();
    }

    @Bean(name = "transactionManagerSecondary")
    PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());
    }
}
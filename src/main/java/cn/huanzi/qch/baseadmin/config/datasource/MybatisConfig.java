package cn.huanzi.qch.baseadmin.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author huzhengfa
 * @Description
 * @create 2020-10-27 8:53 下午
 */
//@Configuration
// 扫描 Mapper 接口并容器管理
//@MapperScan(basePackages = MybatisConfig.PACKAGE)
public class MybatisConfig {

    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "org.spring.springboot.dao.master";
    static final String MAPPER_LOCATION = "classpath:mapper/master/*.xml";

    @Value("${bigdata.datasource.url}")
    private String url;

    @Value("${bigdata.datasource.username}")
    private String user;

    @Value("${bigdata.datasource.password}")
    private String password;

    @Value("${bigdata.datasource.driverClassName}")
    private String driverClass;

    @Bean(name="mybatisDataSource")
    public DataSource createDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "TransactionManager")
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(createDataSource());
    }

    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MybatisConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}

package me.quyen.dbconfigs.student;

import com.zaxxer.hikari.HikariDataSource;
import me.quyen.dbconfigs.DataSourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import java.util.Objects;
import java.util.concurrent.Executors;

@org.springframework.context.annotation.Configuration(
        proxyBeanMethods = false
)
@org.springframework.context.annotation.PropertySource(
        "classpath:databases/student-application.properties"//NOTE!!
) ////NOTE!!
@org.springframework.data.jpa.repository.config.EnableJpaRepositories(
        basePackages = "me.quyen.repositories.student", //NOTE!!
        entityManagerFactoryRef = "studentEntityManagerFactory",//NOTE!!
        transactionManagerRef = "studentTransactionManager"//NOTE!!
)
@org.springframework.transaction.annotation.EnableTransactionManagement
public class StudentHikariCPoolConfig {
    private final String BEAN_PREFIX = "student";//NOTE!!
    private final org.springframework.orm.jpa.vendor.Database DATABASE
            = org.springframework.orm.jpa.vendor.Database.MYSQL;//NOTE!!
    private final String CONFIGURATION_PROPERTIES_PREFIX = "spring." + BEAN_PREFIX + ".datasource";
    private final String[] PACKAGES_TO_SCAN = new String[]{"me.quyen.entities."+BEAN_PREFIX};

    private final String DATA_SOURCE_PROPERTIES = BEAN_PREFIX + "DataSourceProperties";
    private final String DATA_SOURCE_NAME = BEAN_PREFIX + "DataSource";
    private final String ENTITY_MANAGER_FACTORY_NAME = BEAN_PREFIX + "EntityManagerFactory";
    private final String TRANSACTION_MANAGER_NAME = BEAN_PREFIX + "TransactionManager";
    private final String PERSISTENCE_EXCEPTION_TRANSLATION = BEAN_PREFIX + "PersistenceExceptionTranslationPostProcessor";
    private final String DATASOURCE_INITIALIZER =  BEAN_PREFIX + "DataSourceInitializer";

    @Autowired
    private org.springframework.core.env.Environment env;

    @Bean(DATA_SOURCE_PROPERTIES)
    @org.springframework.boot.context.properties.ConfigurationProperties(
            prefix = CONFIGURATION_PROPERTIES_PREFIX
    )
    public org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
    dataSourceProperties() {
        return new org.springframework.boot.autoconfigure.jdbc.DataSourceProperties();
    }


    @Bean(DATA_SOURCE_NAME)
    public javax.sql.DataSource dataSource(
            @Qualifier(DATA_SOURCE_PROPERTIES)
                    org.springframework.boot.autoconfigure.jdbc.DataSourceProperties dataSourceProperties
    ){
        HikariDataSource hikariDataSource = dataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();

        return this.getHikariDataSource(hikariDataSource);
    }

    private HikariDataSource getHikariDataSource(
            HikariDataSource hikariDataSource
    ){
        Objects.requireNonNull(hikariDataSource);
        hikariDataSource.setMaximumPoolSize(
                Integer.parseInt(env.getProperty("spring.student.datasource.hikari.maximumPoolSize","10"))
        );
        hikariDataSource.setMinimumIdle(
                Integer.parseInt(env.getProperty("spring.student.datasource.hikari.minimumIdle","10"))
        );
        hikariDataSource.setMaxLifetime(
                Long.parseLong(env.getProperty("spring.student.datasource.hikari.maxLifetime","1800000"))
        ); // 60 min
        hikariDataSource.setIdleTimeout(
                Long.parseLong(env.getProperty("spring.student.datasource.hikari.idleTimeout","600000"))
        ); // 30 min
        hikariDataSource.setConnectionTimeout(
                Long.parseLong(env.getProperty("spring.student.datasource.hikari.connectionTimeout","30000"))
        ); //10 min
        hikariDataSource.setAutoCommit(
                Boolean.getBoolean(env.getProperty("spring.student.datasource.hikari.autoCommit","false"))
        );
        hikariDataSource.setPoolName(env.getProperty("spring.student.datasource.hikari.poolName","DEFAULT_H2DB_POOL") );

        hikariDataSource.setThreadFactory(Executors.defaultThreadFactory());
        int coreNumber = Runtime.getRuntime().availableProcessors();
        hikariDataSource.setScheduledExecutor(
                Executors.newScheduledThreadPool(coreNumber > 0 ? coreNumber : 4)
        );
        hikariDataSource.setValidationTimeout(
                Long.parseLong(env.getProperty("spring.student.datasource.hikari.validationTimeout","10000"))
        );
        hikariDataSource.setKeepaliveTime(
                Long.parseLong(env.getProperty("spring.student.datasource.hikari.keepaliveTime","300000"))
        );
        hikariDataSource.setLeakDetectionThreshold(
                Long.parseLong(env.getProperty("spring.student.datasource.hikari.leakDetectionThreshold","10000"))
        );
        hikariDataSource.addDataSourceProperty("cachePrepStmts",
                env.getProperty("spring.student.datasource.hikari.cachePrepStmts","true"));
        hikariDataSource.addDataSourceProperty("prepStmtCacheSize",
                env.getProperty("spring.student.datasource.hikari.prepStmtCacheSize","250"));
        hikariDataSource.addDataSourceProperty("prepStmtCacheSqlLimit",
                env.getProperty("spring.student.datasource.hikari.prepStmtCacheSqlLimit","2048"));
        hikariDataSource.addDataSourceProperty("useServerPrepStmts",
                env.getProperty("spring.student.datasource.hikari.useServerPrepStmts","true"));
        hikariDataSource.addDataSourceProperty("useLocalSessionState",
                env.getProperty("spring.student.datasource.hikari.useLocalSessionState","true"));
        hikariDataSource.addDataSourceProperty("rewriteBatchedStatements",
                env.getProperty("spring.student.datasource.hikari.rewriteBatchedStatements","true"));
        hikariDataSource.addDataSourceProperty("cacheResultSetMetadata",
                env.getProperty("spring.student.datasource.hikari.cacheResultSetMetadata","true"));
        hikariDataSource.addDataSourceProperty("cacheServerConfiguration",
                env.getProperty("spring.student.datasource.hikari.cacheServerConfiguration","true"));
        hikariDataSource.addDataSourceProperty("elideSetAutoCommits",
                env.getProperty("spring.student.datasource.hikari.elideSetAutoCommits","true"));
        hikariDataSource.addDataSourceProperty("maintainTimeStats",
                env.getProperty("spring.student.datasource.hikari.maintainTimeStats","false"));
        return hikariDataSource;
    }
    @Bean(ENTITY_MANAGER_FACTORY_NAME)
    public org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
    entityManagerFactory( @Qualifier(DATA_SOURCE_NAME) javax.sql.DataSource dataSource) {
        org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean lcemfBean
                = new org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean();
        lcemfBean.setDataSource(dataSource);
        lcemfBean.setPackagesToScan(PACKAGES_TO_SCAN);
        lcemfBean.setJpaVendorAdapter(DataSourceUtil.vendorAdapter(DATABASE,true,true));
        lcemfBean.setJpaProperties(DataSourceUtil.additionalProperties(env,BEAN_PREFIX,DATABASE));
        return lcemfBean;
    }

    @Bean(TRANSACTION_MANAGER_NAME)
    public org.springframework.transaction.PlatformTransactionManager
    transactionManager(
            @Qualifier(value = ENTITY_MANAGER_FACTORY_NAME)
                    org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean entityManagerFactory
    ) {
        jakarta.persistence.EntityManagerFactory emf = entityManagerFactory.getObject();
        assert emf != null;
        return new org.springframework.orm.jpa.JpaTransactionManager(emf);
    }

    @Bean(PERSISTENCE_EXCEPTION_TRANSLATION)
    public org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
    exceptionTranslation() {
        return new org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor();
    }
//    @Bean(DATASOURCE_INITIALIZER)
//    public org.springframework.jdbc.datasource.init.DataSourceInitializer studentDataSourceInitializer(
//            @Qualifier(DATA_SOURCE_NAME) javax.sql.DataSource dataSource
//    )
//    {
//        org.springframework.jdbc.datasource.init.DataSourceInitializer dataSourceInitializer
//                = new org.springframework.jdbc.datasource.init.DataSourceInitializer();
//        dataSourceInitializer.setDataSource(dataSource);
//        org.springframework.jdbc.datasource.init.ResourceDatabasePopulator databasePopulator
//                = new org.springframework.jdbc.datasource.init.ResourceDatabasePopulator();
//        databasePopulator.addScript(new org.springframework.core.io.ClassPathResource("sql/student-data.sql"));
//        dataSourceInitializer.setDatabasePopulator(databasePopulator);
//        dataSourceInitializer.setEnabled(env.getProperty("spring.student.datasource.initialize", Boolean.class, false));
//        return dataSourceInitializer;
//    }
}
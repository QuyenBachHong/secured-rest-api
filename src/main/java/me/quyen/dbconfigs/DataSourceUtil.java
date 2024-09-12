package me.quyen.dbconfigs;

import java.util.Objects;

public class DataSourceUtil {

    public static org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
    vendorAdapter(org.springframework.orm.jpa.vendor.Database database
            ,boolean generateDdl, boolean showSql){
        org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter vendorAdapter
                = new org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(database);
        //String HBM2DDL_AUTO = "hibernate.hbm2ddl.auto" of org.hibernate.cfg.AvailableSettings
        vendorAdapter.setGenerateDdl(generateDdl);
        //String SHOW_SQL = "hibernate.show_sql" of org.hibernate.cfg.AvailableSettings
        vendorAdapter.setShowSql(showSql);
        return vendorAdapter;
    }
    public static java.util.Properties
    additionalProperties(org.springframework.core.env.Environment env, String beanPrefix
            ,org.springframework.orm.jpa.vendor.Database database){
        Objects.requireNonNull(env);
        Objects.requireNonNull(beanPrefix);
        Objects.requireNonNull(database);
        java.util.Properties properties = new java.util.Properties();
        String defaultDialectName = hibernateDialectName(database);
        String dialectName = env.getProperty("spring." + beanPrefix + ".jpa.properties.hibernate.dialect", defaultDialectName);
        if (dialectName.equals("")){
            throw new IllegalArgumentException("NOT SUPPORT hibernate.dialect");
        }
        properties.setProperty("hibernate.dialect",dialectName);
        properties.setProperty(
                "hibernate.hbm2ddl.auto"
                ,env.getProperty("spring." + beanPrefix + ".jpa.hibernate.ddl-auto","update")
        );

        properties.setProperty("hibernate.current_session_context_class"
                ,env.getProperty("spring." + beanPrefix + ".jpa.properties.hibernate.current_session_context_class","thread")
        );
        properties.setProperty("hibernate.show_sql"
                ,env.getProperty("spring." + beanPrefix + ".jpa.show-sql","true")
        );
        properties.setProperty("hibernate.format_sql"
                ,env.getProperty("spring." + beanPrefix + ".jpa.properties.hibernate.format_sql","true")
        );
        if(database.equals(org.springframework.orm.jpa.vendor.Database.POSTGRESQL)){
            //{ONLY-POSTGRES} Fix Postgres-JPA-Error (Method org.postgresql.jdbc.PgConnection.createClob() is not yet implemented).
            properties.setProperty("hibernate.jdbc.lob.non_contextual_creation"
                    ,env.getProperty("spring." + beanPrefix + ".jpa.properties.hibernate.jdbc.lob.non_contextual_creation","true")
            );
        }

        return properties;
    }
    private static String hibernateDialectName(
            org.springframework.orm.jpa.vendor.Database database
    ){
        if(database == org.springframework.orm.jpa.vendor.Database.H2){
            return "org.hibernate.dialect.H2Dialect";
        }else if(database == org.springframework.orm.jpa.vendor.Database.MYSQL){
            return "org.hibernate.dialect.MySQLDialect";
        }else if(database == org.springframework.orm.jpa.vendor.Database.POSTGRESQL){
            return "org.hibernate.dialect.PostgreSQLDialect";
        }else if(database == org.springframework.orm.jpa.vendor.Database.SQL_SERVER){
            return "org.hibernate.dialect.SQLServerDialect";
        } else if(database == org.springframework.orm.jpa.vendor.Database.ORACLE){
            return "org.hibernate.dialect.OracleDialect";
        }else if(database == org.springframework.orm.jpa.vendor.Database.DB2){
            return "org.hibernate.dialect.DB2Dialect";
        } else if(database == org.springframework.orm.jpa.vendor.Database.DERBY){
            return "org.hibernate.dialect.DerbyDialect";
        }else if(database == org.springframework.orm.jpa.vendor.Database.HANA){
            return "org.hibernate.dialect.HANARowStoreDialect" ;
        } else if(database == org.springframework.orm.jpa.vendor.Database.HSQL){
            return "org.hibernate.dialect.HSQLDialect";
        } else if(database == org.springframework.orm.jpa.vendor.Database.INFORMIX){
            return "org.hibernate.dialect.InformixDialect";
        } else if(database == org.springframework.orm.jpa.vendor.Database.SYBASE){
            return "org.hibernate.dialect.SybaseDialect";
        } else return "";
    }
}
package com.japanredsun.axsserver.config;

import com.japanredsun.axsserver.service.ScheduleScan;
import com.japanredsun.axsserver.dao.ServerInfoDAO;
import com.japanredsun.axsserver.dao.UserInfoDAO;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("com.japanredsun.axsserver.*")
@EnableTransactionManagement
@EnableScheduling
//load to environment
@PropertySource("classpath:datasource-cfg.properties")
public class ApplicationContextConfig {

    @Autowired
    private Environment env;

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private ServerInfoDAO serverInfoDAO;

    @Bean
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
        rb.setBasenames(new String[]{"messages/validation"});
        return rb;
    }

    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource();

        //xem: datasource-cfg.properties
        dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
        dataSource.setUrl(env.getProperty("ds.url"));
        dataSource.setUsername(env.getProperty("ds.username"));
        dataSource.setPassword(env.getProperty("ds.password"));

        System.out.println("## getdatasource " + dataSource);
        return dataSource;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) throws Exception{
        System.out.println("## getSessionFactory");
        try {
            Properties properties = new Properties();

            //xem
            properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
            properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
            properties.put("current_session_context_class", env.getProperty("current_session_context_class"));

            LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
            factoryBean.setPackagesToScan(new String[]{"com.japanredsun.axsserver.entity"});
            factoryBean.setDataSource(dataSource);
            factoryBean.setHibernateProperties(properties);
            factoryBean.afterPropertiesSet();
            //
            SessionFactory sf = factoryBean.getObject();
            System.out.println("## getSessionFactory: " + sf);
            return sf;
        }catch (Exception e)
        {
            System.out.println("Error getSessionFactory: " + e);
            e.printStackTrace();
            throw e;
        }
    }

    // Hibernate Transaction Manager
    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

        return transactionManager;
    }

    // Scheduler
    @Bean
    public ScheduleScan scheduleScan(){
        return new ScheduleScan();
    }

//    @Bean
//    public JavaMailSenderImpl getJavaMailSender(){
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("email-smtp.us-east-1.amazonaws.com");
//        mailSender.setPort(587);
//
//        mailSender.setUsername("AKIAJJFKRB2APQJPVOBQ");
//        mailSender.setPassword("Apzrpa3Ts/n1E204rcG3COUoQqvs+ms9lsCnNy4loXBW");
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//
//        return mailSender;
//    }
}

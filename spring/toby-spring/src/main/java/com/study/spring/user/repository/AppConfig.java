package com.study.spring.user.repository;

import com.study.spring.email.EmailUtils;
import com.study.spring.email.EmailUtilsImpl;
import com.study.spring.user.service.DefaultUserLevelUpgradePolicy;
import com.study.spring.user.service.UserLevelUpgradePolicy;
import com.study.spring.user.service.UserService;
import com.study.spring.user.service.UserServiceImpl;
import com.study.spring.user.service.UserServiceTx;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class AppConfig {

    @Value("${spring.mail.password}")
    private String password;

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl(dataSource());
    }

    @Bean
    public UserService userService() {
//        return new UserServiceImpl(userRepository(), userLevelUpgradePolicy(), emailUtils());
        return new UserServiceTx(
            userServiceImpl(), transactionManager());
//        return new UserServiceImpl(userRepository(), userLevelUpgradePolicy(), emailUtils());
//        return new UserServiceTx(userRepository(), userLevelUpgradePolicy(), transactionManager(), emailUtils());
    }

    @Bean
    public UserServiceImpl userServiceImpl() {
        return new UserServiceImpl(userRepository(), userLevelUpgradePolicy(), emailUtils());
    }


    @Bean
    public UserLevelUpgradePolicy userLevelUpgradePolicy() {
        return new DefaultUserLevelUpgradePolicy();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public EmailUtils emailUtils() {
        return new EmailUtilsImpl(mailSender());
    }


    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource datasource = new SimpleDriverDataSource();

        datasource.setDriverClass(org.mariadb.jdbc.Driver.class);
        datasource.setUrl("jdbc:mariadb://localhost:3306/test");
        datasource.setUsername("root");
        datasource.setPassword("1234");

        return datasource;
    }

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setPort(587);
        mailSender.setHost("smtp.gmail.com");
        mailSender.setUsername("giyeon15@gmail.com");
        mailSender.setPassword(password);

        Properties prop = new Properties();
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.setProperty("mail.smtp.auth", "true");

        mailSender.setJavaMailProperties(prop);

        return mailSender;
    }
}

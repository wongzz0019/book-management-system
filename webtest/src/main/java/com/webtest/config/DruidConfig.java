package com.webtest.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置Druid数据源监控
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        //new DruidDataSource()会从prefix = "spring.datasource"中获取数据
        return new DruidDataSource();
    }

    //后台监控 : 相当于以前的web.xml, ServletRegistrationBean
    //因为springboot 内置了servlet容器，所有没有web.xml,替代方法：ServletRegistrationBean
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //后台需要有人登录，配置账号密码
        HashMap<String, String> initParameters = new HashMap<>(2);
        //增加配置
        initParameters.put("loginUsername","admin");//登录的key是固定的loginUsername
        initParameters.put("loginPassword","123456");//loginPassword
        //允许谁可以访问
        initParameters.put("allow","");
        //设置初始化配置参数
        bean.setInitParameters(initParameters);
        return bean;
    }

}
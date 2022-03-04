package com.webtest.config;

import com.webtest.service.admin.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


@EnableWebSecurity //标识为spring security 的配置类，并启动，含有@Component，注册到了spring的组件中
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启注解方法安全
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected AdminUserService adminUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 定制请求的授权规则
        http.authorizeRequests()
                //首页所有人可以访问.antMatchers("/admin").permitAll()
                .antMatchers("/admin").permitAll()
                .antMatchers("/admin/**").hasRole("admin")
//                .antMatchers("/admin/**").permitAll()
        // 开启自动配置的登录功能，没有权限的时候，会跳转到登录的页面！
        // /login 请求来到登录页
        // /login?error 重定向到这里表示登录失败
        //http.formLogin();
        // 自定义登录页
//        http.
        .and().
                formLogin()
                .loginPage("/admin")//自定义的登录页面
                .loginProcessingUrl("/admin/login") //登录请求转发到 /login 要与表单中action转发的url一致
                //这里的usernameParameter和passwordParameter对应的是表单中input输入框的name属性
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/admin/index").permitAll();//成功登录跳转
//                .failureUrl("/login"); //认证失败的处理url，暂时就重定向到登录页
        //关闭跨站伪造请求攻击
        http.csrf().disable();
        //开启记住我功能,cookie 默认保存2周
//        http.rememberMe();
        //开启自动配置的注销的功能
        // .logoutSuccessUrl("/"); 注销成功来到首页
//        http.logout().logoutSuccessUrl("/toLogin");
    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //在内存中定义，也可以在jdbc中去拿....
        //Spring security 5.0中新增了多种加密方式，也改变了密码的格式。
        //要想我们的项目还能够正常登陆，需要修改一下configure中的代码。我们要将前端传过来的密码进行某种方式加密
        //spring security 官方推荐的是使用bcrypt加密方式。
        auth.userDetailsService(adminUserService);
    }

}
package internet.shop.config;


import internet.shop.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Данный класс содержит в себе настройки безопасности приложения
 *
 * @author Прохоров Дмитрий
 */
@Configuration
@EnableGlobalMethodSecurity(
        jsr250Enabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService myUserDetailsService;

    @Autowired
    public WebSecurityConfig(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    /**
     * В данном методе перечислены основные настройки безопасности доступа по протоколу http
     * проверка csrf токена отключена
     * доступ по относительным путям "/", "/home", "/registration", "/camp/{id}", "/filter/**", "/photo/**", "/api/**"
     * доступен всем без авторизации
     * по относительному пути "/login" происходит авторизация
     * в случае успешной авторизации пользователь будет перенаправлен на "/home"
     * в случае отсутствия прав на доступ к странице пользователь будет перенаправлен на "/accessDenied"
     *
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/home", "/registration", "/camp/{id}", "/filter/**", "/photo/**", "/api/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/home", true)
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied")

        ;

    }

    /**
     * В данном методе указаны пути к статическим ресурсам, доступ к которым открыт всем пользователям
     * (проверка доступа отключена)
     *
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/js/**", "/webjars/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }
}

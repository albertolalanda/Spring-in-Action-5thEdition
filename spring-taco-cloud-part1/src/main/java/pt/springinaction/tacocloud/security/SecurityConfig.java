package pt.springinaction.tacocloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*@Autowired
    DataSource dataSource;*/

    @Qualifier("userRepositoryUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/design", "/orders").access("hasRole('ROLE_USER')")
            .antMatchers("/", "/**").access("permitAll")
            .and().formLogin().loginPage("/login")
            .and().logout().logoutSuccessUrl("/");

        //enable for h2-console
        //!!! REMOVE FOR PRODUCTION !!!
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //Custom user auth
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());



        //In-memory user store
        /*auth.inMemoryAuthentication()
                .withUser("buzz").password("infinity").authorities("ROLE_USER")
                .and()
                .withUser("woody").password("bullseye").authorities("ROLE_USER");*/

        //JDBC-based user store
        //auth.jdbcAuthentication().dataSource(dataSource);

        //JDBC-based user store with queries
        /*  auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from Users " +
                                "where username=?")
                .authoritiesByUsernameQuery(
                        "select username, authority from UserAuthorities " +
                                "where username=?");*/

        //JDBC-based user store with passwordEncoder
        /*auth
            .jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery(
                    "select username, password, enabled from Users " +
                            "where username=?")
            .authoritiesByUsernameQuery(
                    "select username, authority from UserAuthorities " +
                            "where username=?")
            .passwordEncoder(new BCryptPasswordEncoder());*/

        //LDAP backed user store
        /*auth
            .ldapAuthentication()
            .userSearchFilter("(uid={0})")
            .groupSearchFilter("member={0}")
            .passwordCompare()
            .passwordEncoder(new BCryptPasswordEncoder());*/
    }



}

package se.video_shop.rental_system.rental_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/css/**", "/js/**", "/images/**").permitAll();

//        http
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable()
//                .formLogin()
//                .loginPage("/user/login")
//                .defaultSuccessUrl("/film/search")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();

        http
                .formLogin()
                .loginPage("/user/login")
                .failureUrl("/user/login?error")
                .defaultSuccessUrl("/film/search")
                .permitAll()
                .and()
                .csrf().disable()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();
    }

    @Bean
    protected UserDetailsService userDetails() {
        User.UserBuilder builder =
                User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager =
                new InMemoryUserDetailsManager();
        manager.createUser(
                builder
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build());
        manager.createUser(
                builder
                        .username("admin")
                        .password("password")
                        .roles("USER", "ADMIN")
                        .build());
        return manager;
    }
}

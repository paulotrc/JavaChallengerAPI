//package com.example.javachallengerapi;
//
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class WebConfiguration {
//
////    @Bean
////    ServletRegistrationBean h2servletRegistration(){
////        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
////        registrationBean.addUrlMappings("/console/*");
////        return registrationBean;
////    }
//
////    @Bean
////    public WebSecurityConfigurerAdapter webSecurityConfig(DataSource dataSource) {
////        return new WebSecurityConfigurerAdapter() {
////            @Override
////            protected void configure(HttpSecurity http) throws Exception {
////                http.authorizeRequests()
////                        .antMatchers("/h2/**").hasRole("ADMIN")//allow h2 console access to admins only
////                        .anyRequest().authenticated()//all other urls can be access by any authenticated role
////                        .and().formLogin()//enable form login instead of basic login
////                        .and().csrf().ignoringAntMatchers("/h2/**")//don't apply CSRF protection to /h2-console
////                        .and().headers().frameOptions().sameOrigin();//allow use of frame to same origin urls
////            }
////
////            @Override
////            protected void configure(AuthenticationManagerBuilder builder) throws Exception {
////                builder.jdbcAuthentication()
////                        .dataSource(dataSource);
////            }
////        };
////    }
//}
//

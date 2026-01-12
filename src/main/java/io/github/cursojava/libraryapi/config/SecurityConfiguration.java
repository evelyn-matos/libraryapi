package io.github.cursojava.libraryapi.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import io.github.cursojava.libraryapi.security.CustomUserDetailsService;
import io.github.cursojava.libraryapi.security.LoginSocialSucessHandler;
import io.github.cursojava.libraryapi.service.UsuarioService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, LoginSocialSucessHandler sucessHandler) throws Exception {
          return http
                  .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .formLogin(configurer -> {
                    configurer.loginPage("/login").permitAll();
                })
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/login/**").permitAll();
                    authorize.requestMatchers(HttpMethod.POST, "/usuarios/**").permitAll();
                    authorize.anyRequest().authenticated();
                     })
                .oauth2Login(oauth2 -> {
                    
                    oauth2
                    .loginPage("/login")
                    .successHandler(sucessHandler);
                })
                .build();
    }

    //Implementação de segurança , criptografia de senha
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //@Bean (usando o customAuthentication)
    public UserDetailsService userDetailsService(UsuarioService usuarioService){

        // UserDetails user1 = User.builder()
        //     .username("usuario")
        //     .password(encoder.encode("12334"))
        //     .roles("USER")
        //     .build();

        // UserDetails user2 = User.builder()
        //     .username("admin")
        //     .password(encoder.encode("532544"))
        //     .roles("ADMIN")
        //     .build();


        return new CustomUserDetailsService(usuarioService);
    } 

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults(){
        return new GrantedAuthorityDefaults("");
    }
}





// Desativa a proteção CSRF (Cross-Site Request Forgery).
            // Normalmente usada em aplicações stateless (ex: APIs REST).
            //.csrf(AbstractHttpConfigurer::disable) 

            // Habilita autenticação via formulário padrão do Spring Security.
            // Quando o usuário acessa uma rota protegida, é redirecionado para a página de login padrão.
            //.formLogin(Customizer.withDefaults()) pagina de login padrão do security

            //  .formLogin(configurer -> {
            //         configurer.loginPage("/login").permitAll(); //Pagina criada customizada
            //  })

            // Habilita autenticação HTTP Basic.
            // O usuário envia usuário e senha no header Authorization.
            // Muito usada para testes ou APIs simples.
                //.httpBasic(Customizer.withDefaults())

            // Configura as regras de autorização das requisições HTTP
            // .authorizeHttpRequests(authorize -> {
            //     // Exige que qualquer requisição esteja autenticada
            //     // (não permite acesso anônimo a nenhuma rota)
            //     authorize.anyRequest().authenticated();
            // })
             // Constrói e retorna a cadeia de filtros de segurança
            //.build();
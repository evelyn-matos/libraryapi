package io.github.cursojava.libraryapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
          return http
                  .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .formLogin(configurer -> {
                    configurer.loginPage("/login").permitAll();
                })
                .authorizeHttpRequests(authorize -> {
                    authorize.anyRequest().authenticated();
                     })
                .build();
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
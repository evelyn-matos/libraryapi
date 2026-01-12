package io.github.cursojava.libraryapi.security;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import io.github.cursojava.libraryapi.model.Usuario;
import io.github.cursojava.libraryapi.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginSocialSucessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

    private final UsuarioService usuarioService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
       
            OAuth2AuthenticationToken auth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

            OAuth2User oAuth2User = auth2AuthenticationToken.getPrincipal();

            String email = oAuth2User.getAttribute("email");

            Usuario usuario = usuarioService.obterPorEmail(email);

            if(usuario == null){    
                getNovoUser(email);   // Aqui é apenas uma demostração, precisa ser criado um novo usuario. 
                // precisa ser estudado qual melhor forma de ser feito, principalmente em relaçao a senha.
                //Inicialmente, acrediro que seria melhor deixar a senha null e dentro do app(frontend), solicitar o cadastro da senha.
            }

            authentication = new CustomAuthentication(usuario);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            super.onAuthenticationSuccess(request, response, authentication);

    }


    private void getNovoUser(String email) {
        Usuario novoUser = new Usuario();
        novoUser.setEmail(email);
        novoUser.setLogin(obterLogin(email));
        novoUser.setSenha("123");
        novoUser.setRoles(List.of("OPERADOR"));
        usuarioService.salvar(novoUser);
    }


    private String obterLogin(String email) {
        return email.substring(0, email.indexOf("@"));
    }


    
}

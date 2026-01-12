package io.github.cursojava.libraryapi.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import io.github.cursojava.libraryapi.model.Usuario;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityService {

    

    public Usuario obterUsuarioLogado(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if( authentication instanceof CustomAuthentication customAuthentication){
            return customAuthentication.getUsuario();
        }

        return null;
    }
}
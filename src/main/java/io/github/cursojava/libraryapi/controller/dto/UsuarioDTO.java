package io.github.cursojava.libraryapi.controller.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
    @NotBlank(message = "Campo obrigatório")
    String login, 
    
    @NotBlank(message = "Campo obrigatório")
    String senha, 

    @NotBlank(message = "Campo obrigatório")
    @Email(message = "email inválido")
    String email,
    
    List<String> roles
) {
}

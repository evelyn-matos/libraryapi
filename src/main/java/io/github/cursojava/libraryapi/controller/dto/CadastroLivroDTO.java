package io.github.cursojava.libraryapi.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.validator.constraints.ISBN;

import io.github.cursojava.libraryapi.enums.GeneroLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record CadastroLivroDTO(
    
    @ISBN
    @NotBlank(message = "campo obrigatorio")
    String isbn,

    @NotBlank(message = "campo obrigatorio")
    String titulo,

    @NotNull(message = "campo obrigatorio")
    @Past(message = "nao pode ser uma data futura")
    LocalDate dataPublicacao,

    GeneroLivro genero,

    BigDecimal preco,

    @NotNull(message = "campo obrigatorio")
    UUID idAutor
) {
}
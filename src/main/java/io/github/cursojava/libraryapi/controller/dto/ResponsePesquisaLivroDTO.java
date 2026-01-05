package io.github.cursojava.libraryapi.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import io.github.cursojava.libraryapi.enums.GeneroLivro;

public record ResponsePesquisaLivroDTO(

   String isbn,
        
   String titulo,
        
   LocalDate dataPublicacao,
        
   GeneroLivro genero,
        
   BigDecimal preco,
        
   UUID idAutor
) {
}
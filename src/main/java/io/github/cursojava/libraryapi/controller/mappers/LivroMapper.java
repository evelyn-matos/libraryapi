package io.github.cursojava.libraryapi.controller.mappers;

import io.github.cursojava.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.cursojava.libraryapi.controller.dto.ResponsePesquisaLivroDTO;
import io.github.cursojava.libraryapi.model.Livro;
import io.github.cursojava.libraryapi.repository.AutorRepository;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = AutorMapper.class)
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java( autorRepository.findById(dto.idAutor()).orElse(null) )")
    public abstract Livro toEntity(CadastroLivroDTO dto);

    @Mapping(target = "idAutor", source = "autor.id")
    public abstract ResponsePesquisaLivroDTO toDTO(Livro livro);
}

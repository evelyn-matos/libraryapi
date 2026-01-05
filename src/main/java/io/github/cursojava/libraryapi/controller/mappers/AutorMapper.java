package io.github.cursojava.libraryapi.controller.mappers;

import io.github.cursojava.libraryapi.controller.dto.AutorDTO;
import io.github.cursojava.libraryapi.model.Autor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    // @Mapping(source = "nome", target = "nome")
    // @Mapping(source = "dataNascimento", target = "dataNascimento")
    // @Mapping(source = "nacionalidade", target = "nacionalidade")
    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);
}

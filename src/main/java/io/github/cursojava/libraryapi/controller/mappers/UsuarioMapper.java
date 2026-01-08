package io.github.cursojava.libraryapi.controller.mappers;

import org.mapstruct.Mapper;

import io.github.cursojava.libraryapi.controller.dto.UsuarioDTO;
import io.github.cursojava.libraryapi.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);
}

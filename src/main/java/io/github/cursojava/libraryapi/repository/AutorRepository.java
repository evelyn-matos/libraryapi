package io.github.cursojava.libraryapi.repository;

import io.github.cursojava.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface AutorRepository extends JpaRepository<Autor, UUID> {
}

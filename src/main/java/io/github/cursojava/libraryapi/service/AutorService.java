package io.github.cursojava.libraryapi.service;


import org.springframework.stereotype.Service;

import io.github.cursojava.libraryapi.model.Autor;
import io.github.cursojava.libraryapi.repository.AutorRepository;

@Service
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository repository){
        this.repository = repository;
    }

    public Autor salvar(Autor autor){
        return repository.save(autor);
    }
}
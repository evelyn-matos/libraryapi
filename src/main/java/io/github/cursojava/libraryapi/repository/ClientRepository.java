package io.github.cursojava.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.cursojava.libraryapi.model.Client;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client findByClientId(String clientId);
}
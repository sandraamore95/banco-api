package com.banco.bancoapi.repository;


import com.banco.bancoapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public Optional<Cliente> findByMailAndPassword(String mail, String password);

    //FUNCIONA COGIENDO EL NOMBRE DEL CAMPO.. FIND BY .. NOMBRECAMPO (getid) -nombre COLUMNA EN BD
    public List<Cliente> findBygestor_id(Long id);

    public Optional<Cliente> findByMail(String mail);

}

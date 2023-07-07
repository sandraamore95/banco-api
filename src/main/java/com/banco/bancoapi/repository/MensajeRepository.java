package com.banco.bancoapi.repository;

import java.util.ArrayList;


import com.banco.bancoapi.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

	public ArrayList<Mensaje> findByClienteOrigenId(Long id);
	public ArrayList<Mensaje> findByGestorDestinoId(Long id);


}

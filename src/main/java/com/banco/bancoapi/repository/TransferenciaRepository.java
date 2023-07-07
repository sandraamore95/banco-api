package com.banco.bancoapi.repository;


import java.util.ArrayList;


import com.banco.bancoapi.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
	
	public ArrayList<Transferencia> findByClienteOrdenanteId(Long id);
	public ArrayList<Transferencia> findByClienteDestinoId(Long id);

}

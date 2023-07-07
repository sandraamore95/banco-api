
package com.banco.bancoapi.service;
import java.util.ArrayList;

import java.util.Optional;

import com.banco.bancoapi.model.Cliente;
import com.banco.bancoapi.model.Transferencia;

import com.banco.bancoapi.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransferenciaServicio {


	@Autowired
	TransferenciaRepository transferenciaRepository;
	@Autowired
	ClienteService clienteService;

	public ArrayList<Transferencia> getAllTransferencias(){
		return (ArrayList<Transferencia>) this.transferenciaRepository.findAll()
;	}

	public Optional<Transferencia> obtenerTransferencia(Long id){

		return this.transferenciaRepository.findById(id);

	}

	public Transferencia guardarTransferencia(Transferencia transferencia) {

		this.transferenciaRepository.save(transferencia);

		Double importe = transferencia.getImporte();

		Cliente ordenante = transferencia.getClienteOrdenante();
		ordenante = clienteService.getClienteById(ordenante.getId()).orElse(null);
		Double saldoOrdenante = ordenante.getSaldo();

		ordenante.setSaldo(saldoOrdenante - importe);

		Cliente beneficiario = transferencia.getClienteDestino();
		beneficiario = clienteService.getClienteById(beneficiario.getId()).orElse(null);
		Double saldoBenenficiario = beneficiario.getSaldo();

		beneficiario.setSaldo(saldoBenenficiario + importe);

		clienteService.guardarClienteSinActualizarPassword(ordenante);
		clienteService.guardarClienteSinActualizarPassword(beneficiario);

		return transferencia;

	}

	public boolean borrarTransferencia(Long id) {

		try {

			this.transferenciaRepository.deleteById(id);

			return true;

		}catch(Exception e) {

			e.printStackTrace();

			return false;

		}

	}

	public ArrayList<Transferencia> obtenerTransferenciaDestinoPorCliente(Long id){

		return this.transferenciaRepository.findByClienteDestinoId(id);

	}

	public ArrayList<Transferencia> obtenerTransferenciaOrigenPorCliente(Long id){

		return this.transferenciaRepository.findByClienteOrdenanteId(id);

	}



}

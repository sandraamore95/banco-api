package com.banco.bancoapi.service;

import java.util.ArrayList;
import java.util.Optional;


import com.banco.bancoapi.model.Mensaje;
import com.banco.bancoapi.repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class MensajeServicio {
	@Autowired
	MensajeRepository mensajeRepository;

	public ArrayList<Mensaje> obtenerMensajes(){

		return (ArrayList<Mensaje>) this.mensajeRepository.findAll();


	}

	public Optional<Mensaje> obtenerMensaje(Long id){

		return this.mensajeRepository.findById(id);

	}

	public Mensaje guardarMensaje(Mensaje mensaje) {

		return this.mensajeRepository.save(mensaje);

	}

	public boolean borrarMensaje(Long id) {

		try {

			this.mensajeRepository.deleteById(id);

			return true;

		}catch(Exception e) {

			e.printStackTrace();

			return false;
		}


	}

	public ArrayList<Mensaje> obtenerMensajeDestinoPorGestor(Long id){

		return this.mensajeRepository.findByGestorDestinoId(id);

	}

	public ArrayList<Mensaje> obtenerMensajeOrigenPorCliente(Long id){

		return this.mensajeRepository.findByClienteOrigenId(id);

	}


}

package com.banco.bancoapi.service;
import java.util.List;
import java.util.Optional;

import com.banco.bancoapi.model.Cliente;
import com.banco.bancoapi.model.Gestor;
import com.banco.bancoapi.repository.GestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class GestorService {
	
	@Autowired
	GestorRepository gestorRepository;

	//tambien sirve para update gestor
	public Gestor createGestor(Gestor gestor) {
		return this.gestorRepository.save(gestor);

	}

	public Optional<Gestor> getGestorById(Long gestor_id) {
		return this.gestorRepository.findById(gestor_id);
	}
	public Optional<Gestor> getGestorByMail(String mail){
		return this.gestorRepository.findByMail(mail);
	}


	public Optional<Gestor> getGestorbyPhone(String phone){
		return this.gestorRepository.findByphone(phone);
	}
	public List<Gestor> getAllgestores(){

		return this.gestorRepository.findAll();
	}

	
	public Optional<Gestor> obtenerLogin(String mail, String password){
		Optional <Gestor> gestor=getGestorByMail(mail);
		if(gestor.isPresent()){
			//comparar contraseña
			Gestor gestorExist=gestor.get();
			BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
			if(encoder.matches(password,gestorExist.getPassword())){
				return gestor;
			}
		}
		return null;
	}
/*
	public Gestor updateGestor(Gestor gestor) {
			Gestor existingGestor = this.gestorRepository.findById(gestor.getId()).get();
			existingGestor.setMail(gestor.getMail());
			existingGestor.setUsername(gestor.getUsername());
			existingGestor.setPassword(gestor.getPassword());
			Gestor updatedGestor = this.gestorRepository.save(existingGestor);
			return updatedGestor;
		}

 */

	public void deleteGestor(Gestor gestor) {

	 this.gestorRepository.delete(gestor);

	}

}

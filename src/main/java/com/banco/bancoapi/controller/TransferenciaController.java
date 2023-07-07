package com.banco.bancoapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.banco.bancoapi.model.Transferencia;
import com.banco.bancoapi.service.TransferenciaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/transferencia")
public class TransferenciaController {
	
	@Autowired
	TransferenciaServicio transferenciaServicio;
	
	@GetMapping()
	public ArrayList<Transferencia> obtenerTransferencias(){
		
		return this.transferenciaServicio.getAllTransferencias();
		
	}
	
	@GetMapping(path = "/{id}")
	public Optional<Transferencia> obtenerTransferencia(@PathVariable("id") Long id){
		
		return this.transferenciaServicio.obtenerTransferencia(id);
		
	}
	
	@PostMapping()
	public Transferencia guardarTransferencia(@RequestBody Transferencia transferencia) {
		
		return this.transferenciaServicio.guardarTransferencia(transferencia);
		
	}
	
	@DeleteMapping(path = "/{id}")
	public boolean borrarTransferencia(@PathVariable("id") Long id) {
		
		return this.transferenciaServicio.borrarTransferencia(id);
		
	}
	
	@GetMapping(path = "/transferencias-recibidas/{idCliente}")
	public ArrayList<Transferencia> obtenerTransferenciaDestinoPorCliente(@PathVariable("idCliente") Long id){
		
		return this.transferenciaServicio.obtenerTransferenciaDestinoPorCliente(id);
		
	}
	
	@GetMapping(path = "/transferencias-enviadas/{idCliente}")
	public ArrayList<Transferencia> obtenerTransferenciaOrigenPorCliente(@PathVariable("idCliente") Long id){
		
		return this.transferenciaServicio.obtenerTransferenciaOrigenPorCliente(id);
		
	}

}

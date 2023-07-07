package com.banco.bancoapi.controller;

import java.util.*;

import com.banco.bancoapi.ResourceNotFoundException;
import com.banco.bancoapi.model.Gestor;
import com.banco.bancoapi.service.GestorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/gestor")
public class GestorController {
	
	@Autowired
	GestorService gestorService;

	@GetMapping
	public List<Gestor> getAll(){

		return this.gestorService.getAllgestores();
	}
	/*
	 @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
        try {
            //check if employee exist in database
            Employee empObj = getEmpRec(id);

            if (empObj != null) {
                return new ResponseEntity<>(empObj, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
	 */
	  @GetMapping(path = "/{id}")
    public ResponseEntity<Gestor> getGestorById(@PathVariable("id") Long gestor_id)
            throws ResourceNotFoundException {
        Gestor gestor = this.gestorService.getGestorById(gestor_id)
                .orElseThrow(() -> new ResourceNotFoundException("Gestor no se encuentra para el id : " + gestor_id));
        return ResponseEntity.ok().body(gestor);
    }

	@GetMapping(path = "mail/{mail}")
	public ResponseEntity<Gestor> getGestorByMail(@PathVariable("mail") String mail)
		    throws ResourceNotFoundException {
			Gestor gestor = this.gestorService.getGestorByMail(mail)
					.orElseThrow(() -> new ResourceNotFoundException("Gestor no se encuentra para el  mail : " + mail ));
			return ResponseEntity.ok().body(gestor);
	}
	@GetMapping(path = "phone/{phone}")
	public ResponseEntity<Gestor> getGestorByPhone(@PathVariable("phone") String phone)
			throws ResourceNotFoundException {
		Gestor gestor = this.gestorService.getGestorbyPhone(phone)
				.orElseThrow(() -> new ResourceNotFoundException("Gestor no se encuentra para el  telefono : " + phone ));
		return ResponseEntity.ok().body(gestor);
	}





	//ME LO DEJO PARA LUEGO !!!
	@GetMapping(path = "/login")
	public ResponseEntity<Optional<Gestor>>loginGestor(@RequestParam("mail") String mail, @RequestParam("password") String password){
		Optional<Gestor> gestor =this.gestorService.obtenerLogin(mail,password);
		return new ResponseEntity<>(gestor, HttpStatus.OK);
		//devuelve el objeto gestor si se encuentra el login!! si no devuelve null porque no hay gestor registrado!

	}

	  //ADD EMPLOYEE - no se puede añadir un gestor con el mismo mail que otro gestor

	@PostMapping()
	public ResponseEntity<Gestor> addGestor(@Valid @RequestBody Gestor gestor) throws ResourceNotFoundException {
		boolean gestor_exist = this.gestorService.getGestorByMail(gestor.getMail()).isEmpty();
		//si el gestor con el mail ya existe en la BD - error mail no disponible
		if (!gestor_exist){
			throw new ResourceNotFoundException("El mail introducido ya existe");
		}
		//ahora ya podemos añadirlo a la BD
		Gestor gestor_add =this.gestorService.createGestor(gestor);
		return new ResponseEntity<>(gestor_add, HttpStatus.OK);

	}
	@PutMapping(path = "/{id}")
	public ResponseEntity<Gestor> updateGestor(@PathVariable(value = "id") Long gestor_id,
												   @Valid @RequestBody Gestor gestorDetails) throws ResourceNotFoundException {
		Gestor gestor = this.gestorService.getGestorById(gestor_id)
				.orElseThrow(() -> new ResourceNotFoundException("El id del gestor no existe:: " + gestor_id));


		//tenemos que ver que no exista este email en la BD
		boolean gestor_exist = this.gestorService.getGestorByMail(gestorDetails.getMail()).isEmpty();
		//si el gestor con el mail ya existe en la BD - error mail no disponible
		if (!gestor_exist){
			throw new ResourceNotFoundException("Email no disponible");
		}
		gestor.setMail(gestorDetails.getMail());
		gestor.setUsername(gestorDetails.getUsername());
		gestor.setPassword(gestorDetails.getPassword());
		final Gestor updatedGestor = this.gestorService.createGestor(gestor);
		return ResponseEntity.ok(updatedGestor);
	}
	@DeleteMapping(path = "/{id}")
    public Map<String, Boolean> deleteGestor(@PathVariable(value = "id") Long gestor_id)
            throws ResourceNotFoundException {
        Gestor gestor = this.gestorService.getGestorById(gestor_id)
                .orElseThrow(() -> new ResourceNotFoundException("Gestor no encontrado para esta id :: " + gestor_id));

        this.gestorService.deleteGestor(gestor);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Gestor eliminado correctamente!", Boolean.TRUE);
        return response;
    }

	}






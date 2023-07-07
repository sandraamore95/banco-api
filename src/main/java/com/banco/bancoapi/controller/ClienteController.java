package com.banco.bancoapi.controller;

import java.util.*;


import com.banco.bancoapi.ResourceNotFoundException;
import com.banco.bancoapi.model.Cliente;
import com.banco.bancoapi.model.Gestor;
import com.banco.bancoapi.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/cliente")
public class ClienteController {


    @Autowired
    ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAll(){
        return this.clienteService.getAllClients();
    }

    //ME LO DEJO PARA LUEGO !!!
    @GetMapping(path = "/login")
    public ResponseEntity<Optional<Cliente>>loginCliente(@RequestParam("mail") String mail, @RequestParam("password") String password){
        Optional<Cliente> cliente =this.clienteService.obtenerLogin(mail,password);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
        //devuelve el objeto cliente si se encuentra el login!! si no devuelve null porque no hay cliente registrado!

    }

    // ENDPOINT CAMBIAR CONTRASEÑA!!
    @PutMapping(path = "password/{id}")
    public ResponseEntity<Cliente> changePassw(@PathVariable("id") Long cliente_id, @RequestBody Cliente clienteDetails)
            throws ResourceNotFoundException {
        Cliente cliente = this.clienteService.getClienteById(cliente_id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no se encuentra para el id : " + cliente_id));
        //si el cliente se encuentra ya podemos cambiar la contraseña del cliente
        System.out.println(cliente.getPassword());
        //recogemos el cliente y le metemos la nueva password
        cliente.setPassword(clienteDetails.getPassword());
        //ahora llamamos a crear nuevo cliente con sus atributos
        final Cliente updatedCliente = this.clienteService.createClient(cliente);
        return ResponseEntity.ok(updatedCliente);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long cliente_id)
            throws ResourceNotFoundException {
        Cliente cliente = this.clienteService.getClienteById(cliente_id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no se encuentra para el id : " + cliente_id));
        return ResponseEntity.ok().body(cliente);
    }
    @GetMapping(path = "mail/{mail}")
    public ResponseEntity<Cliente> getClienteByMail(@PathVariable("mail") String mail)
            throws ResourceNotFoundException {
        Cliente cliente = this.clienteService.getClienteByMail(mail)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no se encuentra para el  mail : " + mail ));
        return ResponseEntity.ok().body(cliente);
    }


    @GetMapping(path = "/gestor/{idGestor}")
    public List<Cliente> obtenerClientePorGestor(@PathVariable("idGestor") Long id){
        return this.clienteService.obtenerClientePorGestor(id);
    }

    @DeleteMapping(path = "/{id}")
    public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long cliente_id)
            throws ResourceNotFoundException {
        Cliente cliente = this.clienteService.getClienteById(cliente_id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado para esta id :: " + cliente_id));

        this.clienteService.deleteCliente(cliente);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Cliente eliminado correctamente!", Boolean.TRUE);
        return response;
    }

    //ADD CLIENTE
    @PostMapping()
    public ResponseEntity<Cliente> addClient(@Valid @RequestBody Cliente cliente) throws ResourceNotFoundException {
        boolean client_exist = this.clienteService.getClienteByMail(cliente.getMail()).isEmpty();
        //si el cliente con el mail ya existe en la BD - error mail no disponible
        if (!client_exist){
            throw new ResourceNotFoundException("El mail introducido ya existe");
        }
        //ahora ya podemos añadirlo a la BD
        Cliente client_add =this.clienteService.createClient(cliente);
        return new ResponseEntity<>(client_add, HttpStatus.OK);

    }



    //UPDATE CLIENTE
    @PutMapping(path = "/mail/{id}")
    public ResponseEntity<Cliente> updateClient(@PathVariable(value = "id") Long client_id,
                                                @Valid @RequestBody Cliente clientDetails) throws ResourceNotFoundException {
        Cliente cliente = this.clienteService.getClienteById(client_id)
                .orElseThrow(() -> new ResourceNotFoundException("El id del cliente no existe:: " + client_id));


        //tenemos que ver que no exista este email en la BD
        boolean client_exist = this.clienteService.getClienteByMail(clientDetails.getMail()).isEmpty();

        //si el gestor con el mail ya existe en la BD - error mail no disponible

        if (!client_exist){
            throw new ResourceNotFoundException("Email no disponible");
        }
        cliente.setMail(clientDetails.getMail());
        cliente.setUsername(clientDetails.getUsername());
        cliente.setSaldo(clientDetails.getSaldo());
        cliente.setPassword(clientDetails.getPassword());
        cliente.setGestor(clientDetails.getGestor());
        final Cliente updatedCliente = this.clienteService.createClient(cliente);
        return ResponseEntity.ok(updatedCliente);
    }

    //update cliente username por mail del cliente
    @PutMapping(path = "/username/{mail}")
    public ResponseEntity<Cliente> updateUsername(@PathVariable(value = "mail") String mail,
                                                  @Valid @RequestBody Cliente clientDetails) throws ResourceNotFoundException {
        Cliente cliente = this.clienteService.getClienteByMail(mail)
                .orElseThrow(() -> new ResourceNotFoundException("El mail del cliente no existe:: " + mail));
//si el cliente se encuentra , modificamos su username
        cliente.setMail(cliente.getMail());
        cliente.setUsername(clientDetails.getUsername()); // el nuevo username
        cliente.setSaldo(cliente.getSaldo());
        cliente.setPassword(cliente.getPassword());
        cliente.setGestor(cliente.getGestor());
        final Cliente updatedCliente = this.clienteService.createClient(cliente);
        //devuelve el cliente con el username actualizado!
        return ResponseEntity.ok(updatedCliente);
    }





}

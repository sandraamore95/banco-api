package com.banco.bancoapi.service;

import com.banco.bancoapi.model.Cliente;
import com.banco.bancoapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    public List<Cliente> getAllClients(){
        return this.clienteRepository.findAll();
    }


    public Optional<Cliente> obtenerLogin(String mail, String password){
        Optional <Cliente> cliente=getClienteByMail(mail);
        if(cliente.isPresent()){
            //comprar contraseñla
            Cliente clienteExist=cliente.get();
            BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
            if(encoder.matches(password,clienteExist.getPassword())){
                return cliente;
            }
        }
        return null;
    }

    public Optional<Cliente> getClienteById(Long cliente_id) {
        return this.clienteRepository.findById(cliente_id);
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

    public void deleteCliente(Cliente cliente) {

        this.clienteRepository.delete(cliente);

    }

    public List<Cliente> obtenerClientePorGestor(Long id){

        return this.clienteRepository.findBygestor_id(id);

    }
    public Optional<Cliente> getClienteByMail(String mail){
        return this.clienteRepository.findByMail(mail);
    }

    public Cliente createClient(Cliente cliente) {
       String passw= cliente.getPassword();
       if(passw!=null){
           //ciframos la contraseña
           cliente.encriptarPassw();
       }else{
           //sacar la contaseña y poner la de antes

           cliente.setPassword(obtenerPasswActual(cliente));
       }
        return this.clienteRepository.save(cliente);

    }

    private String obtenerPasswActual(Cliente cliente)
    {
        Cliente cliente_add=getClienteById(cliente.getId()).orElse(null);
        if(cliente_add!=null){
            return cliente_add.getPassword();
        }else{
            return null;
        }
    }
    public Cliente guardarClienteSinActualizarPassword(Cliente cliente) {
        String passGuardada= obtenerPasswActual(cliente);
        cliente.setPassword(passGuardada);
        return this.clienteRepository.save(cliente);
    }

}



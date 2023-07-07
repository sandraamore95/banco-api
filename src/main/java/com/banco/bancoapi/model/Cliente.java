package com.banco.bancoapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "mail", nullable = false)
    private String mail;
    @Column(name = "password", nullable = false)
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY )
    private String password;
    @Column(name = "saldo", nullable = false)
    private Double saldo;
    @Column(name = "username", nullable = false)
    private String username;

    //campo con relacion cliente-gestor
    @ManyToOne
    private Gestor gestor;


    public void encriptarPassw(){
       BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        this.password=encoder.encode(this.password);
    }

}

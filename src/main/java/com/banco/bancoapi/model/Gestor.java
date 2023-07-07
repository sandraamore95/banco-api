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
@Table(name = "gestor")
public class Gestor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "username", nullable = false)
	private String username;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY ) // para que no aparezca en el SELECT
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "mail", nullable = false)
	private String mail;
	@Column(name = "telefono", nullable = false)
	private String phone;


	public void encriptarPassw(){
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		this.password=encoder.encode(this.password);
	}

}

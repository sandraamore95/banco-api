
package com.banco.bancoapi.model;
import java.util.Date;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Setter
@Getter

@AllArgsConstructor
@Table(name = "mensaje")
public class Mensaje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String texto;
	
	@CreationTimestamp
	private Date fecha;


	@ManyToOne
	@JoinColumn(name = "id_origen", nullable = false)
	private Gestor gestorDestino  ;

	@ManyToOne
	@JoinColumn(name = "id_destino", nullable = false)
	private Cliente clienteOrigen ;

	public Mensaje() {

	}


	
	

}


package com.banco.bancoapi.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Table(name = "transferencia")
public class Transferencia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String concepto;
	private double importe;
	
	@CreationTimestamp
	private Date fecha;

	@ManyToOne
	@JoinColumn(name = "id_ordenante", nullable = false)
	private Cliente clienteOrdenante;  //ordenante

	@ManyToOne
	@JoinColumn(name = "id_destino", nullable = false)
	private Cliente clienteDestino; //beneficiario
	public Transferencia() {
	}

}

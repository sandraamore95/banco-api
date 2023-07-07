package com.banco.bancoapi.repository;
import java.util.List;
import java.util.Optional;
import com.banco.bancoapi.model.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;


//extendemos de JpaRepository, Gestor y Long. Gestor es la entidad y Long es la Primary key
public interface GestorRepository extends JpaRepository<Gestor, Long> {
	public Optional<Gestor> findByMail(String mail);

	public Optional<Gestor> findByMailAndPassword(String mail, String password);

	/*

	@Column(name = "telefono", nullable = false)
	private String phone; SE TIENE Q LLAMAR IGUAL!!
	 */
	public Optional <Gestor> findByphone(String phone);



}

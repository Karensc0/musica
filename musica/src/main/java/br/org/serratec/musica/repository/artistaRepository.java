package br.org.serratec.musica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.org.serratec.musica.model.artista;
import jakarta.transaction.Transactional;

public interface artistaRepository extends JpaRepository<artista, Long> {

	@Modifying
	@Transactional
	@Query("Delete from artista l where l.id =:idartista")
	void deletarArtista(@Param("idartista") Long idartista);
}

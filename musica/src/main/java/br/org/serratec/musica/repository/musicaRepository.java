package br.org.serratec.musica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.org.serratec.musica.model.artista;
import br.org.serratec.musica.model.musica;
import jakarta.transaction.Transactional;


public interface musicaRepository extends JpaRepository<musica, Long> {

	@Query("Select l from musica l where :artista member of l.artistas")
	List<musica> buscaPorAutor(@Param("artista") artista artista);
	
	
	@Modifying
	@Transactional
	@Query("Delete from musica l where l.id =:idmusica")
	void excluirMusica(@Param("idmusica") Long idmusica);
}



package br.org.serratec.musica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.org.serratec.musica.model.album;
import jakarta.transaction.Transactional;

public interface albumRepository extends JpaRepository<album, Long> {
	
	@Modifying
	@Transactional
	@Query("Delete from album l where l.id =:idalbum")
	void excluirAlbum(@Param("idalbum") Long idalbum);
}

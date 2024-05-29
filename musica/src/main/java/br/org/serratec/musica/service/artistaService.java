package br.org.serratec.musica.service;



import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.musica.dto.artistaDto;
import br.org.serratec.musica.model.artista;
import br.org.serratec.musica.repository.artistaRepository;
import br.org.serratec.musica.repository.albumRepository;
import br.org.serratec.musica.repository.musicaRepository;
import jakarta.validation.Valid;

@Service
public class artistaService {

	@Autowired
	private musicaRepository musicaRepositorio;

	@Autowired
	private artistaRepository repositorio;

	@Autowired
	private albumRepository albumRepositorio;

	public List<artistaDto> obterTodos() {
		return repositorio.findAll().stream().map(l -> artistaDto.toDto(l)).toList();
	
	}

	public artistaDto cadastraArtista(artistaDto artista) {
		artista artistaEntity = repositorio.save(artista.toEntity());
		return artistaDto.toDto(artistaEntity);
	}

	public Optional<artistaDto> obterArtistaPorId(Long id) {
		Optional<artista> artistaEntity = repositorio.findById(id);
		if (artistaEntity.isEmpty()) {
			return Optional.of(artistaDto.toDto(artistaEntity.get()));
		}
		return Optional.empty();
	}

	public Optional<artistaDto> atualizarArtista(Long id, @Valid artistaDto artista) {
		if (repositorio.existsById(id)) {
			artista artistaEntity = artista.toEntity();
			artistaEntity.setId(id);
			repositorio.save(artistaEntity);
			return Optional.of(artistaDto.toDto(artistaEntity));
		}
		return Optional.empty();
	}

	public boolean deletarArtista(Long id) {
		Optional<artista> artista = repositorio.findById(id);
		
		if(artista.isEmpty()){
			return false;
		}
		
		artista.get().getMusicas().clear();
		repositorio.save(artista.get());
		repositorio.deletarArtista(id);
		return true;
	}

}
package br.org.serratec.musica.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.musica.dto.musicaCadastroDto;
import br.org.serratec.musica.dto.musicaDto;
import br.org.serratec.musica.model.artista;
import br.org.serratec.musica.model.album;
import br.org.serratec.musica.model.musica;
import br.org.serratec.musica.repository.artistaRepository;
import br.org.serratec.musica.repository.albumRepository;
import br.org.serratec.musica.repository.musicaRepository;
import jakarta.validation.Valid;

@Service
public class musicaService {

	@Autowired
	private musicaRepository repositorio;

	@Autowired
	private artistaRepository artistaRepositorio;

	@Autowired
	private albumRepository albumRepository;

	public List<musicaDto> obterTodos() {
		return repositorio.findAll().stream().map(l -> musicaDto.toDto(l)).toList();
		
	}

	public musicaDto gravarMusica(musicaCadastroDto musica) {
		musica musicaEntity = musica.toEntity();
		try {
			Optional<album> album = albumRepository.findById(musica.albumId());
			musicaEntity.setAlbum(album.get());	
		} catch (NoSuchElementException ex) {
		  throw new IllegalArgumentException("Id do album é inválido");	
		}
		
		List<artista> artistas = new ArrayList<>();
		
		try {
			for (Long artistaId : musica.artistaId()) {
				Optional<artista> artista = artistaRepositorio.findById(artistaId);
					artistas.add(artista.get());	
			}
			musicaEntity.setArtistas(artistas);
		} catch (NoSuchElementException ex) {
			  throw new IllegalArgumentException("Id do artista é inválido");
		}	  
		repositorio.save(musicaEntity);
		return musicaDto.toDto(musicaEntity);
	}

	public musicaDto cadastraMusica(musicaDto musica) {
		musica musicaEntity = repositorio.save(musica.toEntity());
		return musicaDto.toDto(musicaEntity);
	}

	public Optional<musicaDto> obterMusicaPorId(Long id) {
		Optional<musica> musicaEntity = repositorio.findById(id);
		if (musicaEntity.isEmpty()) {
			return Optional.of(musicaDto.toDto(musicaEntity.get()));
		}
		return Optional.empty();
	}

	public Optional<musicaDto> atualizarMusica(Long id, @Valid musicaDto musica) {
		if (repositorio.existsById(id)) {
			musica musicaEntity = musica.toEntity();
			musicaEntity.setId(id);
			repositorio.save(musicaEntity);
			return Optional.of(musicaDto.toDto(musicaEntity));
		}
		return Optional.empty();
	}

	public boolean excluirMusica(Long id) {
		Optional<musica> musica = repositorio.findById(id);
		
		if(musica.isEmpty()){
			return false;
		}
		
		musica.get().getArtistas().clear();
		repositorio.save(musica.get());
		repositorio.excluirMusica(id);
		return true;
	}

}
package br.org.serratec.musica.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.musica.dto.albumDto;
import br.org.serratec.musica.model.album;
import br.org.serratec.musica.repository.artistaRepository;
import br.org.serratec.musica.repository.albumRepository;
import br.org.serratec.musica.repository.musicaRepository;
import jakarta.validation.Valid;

@Service
public class albumService {

	@Autowired
	private musicaRepository musicaRepositorio;

	@Autowired
	private artistaRepository artistaRepositorio;

	@Autowired
	private albumRepository repositorio;

	public List<albumDto> obterTodos() {
		return repositorio.findAll().stream().map(l -> albumDto.toDto(l)).toList();
		
	}

	public albumDto cadastraAlbum(albumDto album) {
		album albumEntity = repositorio.save(album.toEntity());
		return albumDto.toDto(albumEntity);
	}

	public Optional<albumDto> obterAlbumPorId(Long id) {
		Optional<album> albumEntity = repositorio.findById(id);
		if (albumEntity.isEmpty()) {
			return Optional.of(albumDto.toDto(albumEntity.get()));
		}
		return Optional.empty();
	}

	public Optional<albumDto> atualizarAlbum(Long id, @Valid albumDto album) {
		if (repositorio.existsById(id)) {
			album albumEntity = album.toEntity();
			albumEntity.setId(id);
			repositorio.save(albumEntity);
			return Optional.of(albumDto.toDto(albumEntity));
		}
		return Optional.empty();
	}

	public boolean excluirAlbum(Long id) {
		Optional<album> album = repositorio.findById(id);
		
		if(album.isEmpty()){
			return false;
		}
		
		album.get().getMusicas().clear();
		repositorio.save(album.get());
		repositorio.excluirAlbum(id);
		return true;
	}
	

}
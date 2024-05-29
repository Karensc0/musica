package br.org.serratec.musica.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.musica.dto.albumDto;
import br.org.serratec.musica.service.albumService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/albuns")
public class albumController {

	@Autowired 
	private albumService servico;
	
	@GetMapping
	public ResponseEntity<List<albumDto>> obterTodos() {
		return new ResponseEntity<>(servico.obterTodos(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<albumDto> obterAlbumPorId(@PathVariable Long id) {
		Optional<albumDto> dto = servico.obterAlbumPorId(id);
		if (dto.isPresent()) {
			return new ResponseEntity<>(dto.get(), HttpStatus.FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping
	public ResponseEntity<albumDto> cadastrarAlbum(@RequestBody @Valid albumDto album) {
		return new ResponseEntity<>(servico.cadastraAlbum(album), HttpStatus.CREATED);
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<albumDto> atualizar(@PathVariable Long id, @RequestBody @Valid albumDto album) {
		Optional<albumDto> dto = servico.atualizarAlbum(id, album);
		
		if (dto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}		
		return ResponseEntity.ok(dto.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if(!servico.excluirAlbum(id)){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
}
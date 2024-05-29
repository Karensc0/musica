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



import br.org.serratec.musica.dto.artistaDto;
import br.org.serratec.musica.service.artistaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/artistas")
public class artistaController {
	
	@Autowired
	artistaService servico;
	
	@GetMapping
	public ResponseEntity<List<artistaDto>> obterTodos() {
		return new ResponseEntity<>(servico.obterTodos(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<artistaDto> obterPorId(@PathVariable Long id) {
		Optional<artistaDto> dto = servico.obterArtistaPorId(id);
		if (dto.isPresent()) {
			return new ResponseEntity<>(dto.get(), HttpStatus.FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping
	public ResponseEntity<artistaDto> cadastrarArtista(@RequestBody @Valid artistaDto artista){
		return new ResponseEntity<artistaDto>(servico.cadastraArtista(artista), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<artistaDto> atualizarArtista(@PathVariable Long id, @RequestBody @Valid artistaDto artista){
		Optional<artistaDto> artistaDto = servico.atualizarArtista(id, artista);
		if(artistaDto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(artistaDto.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarArtista(@PathVariable Long id){
		if(!servico.deletarArtista(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}

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

import br.org.serratec.musica.dto.musicaDto;
import br.org.serratec.musica.service.musicaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/musicas")
public class musicaController {

	@Autowired 
	private musicaService servico;
	
	@GetMapping
	public ResponseEntity<List<musicaDto>> obterTodos() {
		return new ResponseEntity<>(servico.obterTodos(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<musicaDto> obterPorId(@PathVariable Long id) {
		Optional<musicaDto> dto = servico.obterMusicaPorId(id);
		if (dto.isPresent()) {
			return new ResponseEntity<>(dto.get(), HttpStatus.FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping
	public ResponseEntity<musicaDto> cadastrarMusica(@RequestBody @Valid musicaDto musica) {
		return new ResponseEntity<>(servico.cadastraMusica(musica), HttpStatus.CREATED);
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<musicaDto> atualizar(@PathVariable Long id, @RequestBody @Valid musicaDto musica) {
		Optional<musicaDto> dto = servico.atualizarMusica(id, musica);
		
		if (dto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}		
		return ResponseEntity.ok(dto.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if(!servico.excluirMusica(id)){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
}
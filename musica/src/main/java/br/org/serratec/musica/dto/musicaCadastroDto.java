package br.org.serratec.musica.dto;

import java.util.List;

import br.org.serratec.musica.config.Mapper;
import br.org.serratec.musica.model.musica;

public record musicaCadastroDto(
		String titulo,
		String isbn,
		int anoLancamento,
		Long albumId,
		List<Long> artistaId) {
	
	public musica toEntity() {
		return Mapper.getMapper().convertValue(this, musica.class);
	}
	
}


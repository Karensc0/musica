package br.org.serratec.musica.dto;

import java.util.List;

import br.org.serratec.musica.config.Mapper;
import br.org.serratec.musica.model.musica;

public record musicaDto(
		Long id,
		String titulo,
		int anoLancamento,
		albumDto album,
		List<artistaDto> artistas)  {
	
	public musica toEntity() {
		return Mapper.getMapper().convertValue(this, musica.class);
		
	}

	public static musicaDto toDto(musica musicaEntity) {
		return Mapper.getMapper().convertValue(musicaEntity, musicaDto.class);
	}

}

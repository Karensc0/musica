package br.org.serratec.musica.dto;

import br.org.serratec.musica.config.Mapper;
import br.org.serratec.musica.model.album;

public record albumDto(
		 Long id,
		 String nomeAlbum,
		 int anoLancamento,
		 gravadoraDto gravadora)  {
	
	public album toEntity() {
		return Mapper.getMapper().convertValue(this, album.class);
	}
	
	public static albumDto toDto(album albumEntity) {
		return Mapper.getMapper().convertValue(albumEntity, albumDto.class);
	}

}

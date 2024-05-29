package br.org.serratec.musica.dto;


import br.org.serratec.musica.config.Mapper;
import br.org.serratec.musica.model.artista;



public record artistaDto(
		Long id,
		String nome,
		String genero) {
	
	public artista toEntity() {
		return Mapper.getMapper().convertValue(this, artista.class);
	}

	public static artistaDto toDto(artista artistaEntity) {
		return Mapper.getMapper().convertValue(artistaEntity, artistaDto.class);
	}
}




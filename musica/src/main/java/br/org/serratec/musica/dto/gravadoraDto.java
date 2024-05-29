package br.org.serratec.musica.dto;


import br.org.serratec.musica.config.Mapper;
import br.org.serratec.musica.model.gravadora;

public record gravadoraDto(
		Long id,
		String cnpj,
		String razaoSocial) {
	
	public gravadora toEntity() {
		return Mapper.getMapper().convertValue(this, gravadora.class);
	}

}
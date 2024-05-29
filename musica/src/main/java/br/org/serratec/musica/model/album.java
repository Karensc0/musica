package br.org.serratec.musica.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "albuns")
public class album {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeAlbum;
	private int anoLancamento;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gravadora_id")
	private gravadora gravadora;
	@OneToMany(mappedBy = "album")
	private List<musica> musicas;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeAlbum() {
		return nomeAlbum;
	}
	public void setNomeAlbum(String nomeAlbum) {
		this.nomeAlbum = nomeAlbum;
	}
	public int getAnoLancamento() {
		return anoLancamento;
	}
	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
	public gravadora getGravadora() {
		return gravadora;
	}
	public void setGravadora(gravadora gravadora) {
		this.gravadora = gravadora;
	}
	public List<musica> getMusicas() {
		return musicas;
	}
	public void setMusicas(List<musica> musicas) {
		this.musicas = musicas;
	}
	
	
		
}	
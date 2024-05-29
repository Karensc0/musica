package br.org.serratec.musica.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "musicas")
public class musica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private int anoLancamento;
	@ManyToOne(cascade = CascadeType.ALL)
	private album album;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "musica_artista",
	  joinColumns = @JoinColumn(name = "musica_id"),
	  inverseJoinColumns = @JoinColumn(name = "artista_id"))
	private List<artista> artistas;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getAnoLancamento() {
		return anoLancamento;
	}
	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
	public album getAlbum() {
		return album;
	}
	public void setAlbum(album album) {
		this.album = album;
	}
	public List<artista> getArtistas() {
		return artistas;
	}
	public void setArtistas(List<artista> artistas) {
		this.artistas = artistas;
	}
	
	
	
}
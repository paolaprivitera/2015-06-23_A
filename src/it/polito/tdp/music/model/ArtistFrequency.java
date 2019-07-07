package it.polito.tdp.music.model;

public class ArtistFrequency {
	
	private int id;
	private String nome;
	private int frequenza;
	
	public ArtistFrequency(int id, String nome, int frequenza) {
		this.id = id;
		this.nome = nome;
		this.frequenza = frequenza;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getFrequenza() {
		return frequenza;
	}
	public void setFrequenza(int frequenza) {
		this.frequenza = frequenza;
	}
	
	

}

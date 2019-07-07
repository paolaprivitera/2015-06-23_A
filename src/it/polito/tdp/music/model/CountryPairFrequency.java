package it.polito.tdp.music.model;

public class CountryPairFrequency {

	private int paese1;
	private int paese2;
	private int numeroArtisti;
	
	public CountryPairFrequency(int paese1, int paese2, int numeroArtisti) {
		this.paese1 = paese1;
		this.paese2 = paese2;
		this.numeroArtisti = numeroArtisti;
	}

	public int getPaese1() {
		return paese1;
	}

	public void setPaese1(int paese1) {
		this.paese1 = paese1;
	}

	public int getPaese2() {
		return paese2;
	}

	public void setPaese2(int paese2) {
		this.paese2 = paese2;
	}

	public int getNumeroArtisti() {
		return numeroArtisti;
	}

	public void setNumeroArtisti(int numeroArtisti) {
		this.numeroArtisti = numeroArtisti;
	}	
	
	
}

package it.polito.tdp.music.model;

import java.time.Month;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.music.db.MusicDAO;

public class Model {
	
	private MusicDAO dao;
	private List<Month> mesi;
	private List<ArtistFrequency> topArtisti;
	private List<Country> topCountries;
	private SimpleWeightedGraph<Country, DefaultWeightedEdge> grafo;
	
	public Model() {
		topCountries = new LinkedList<Country>();
		dao = new MusicDAO();
	}

	public List<Month> getMonths() {
		mesi = new LinkedList<Month>();
		
		for(Integer m : dao.getMonths()) {
			mesi.add(Month.of(m));
		}
		
		return mesi;
	}

	public List<ArtistFrequency> doElenco(Month mese) {
		
		topArtisti = new LinkedList<ArtistFrequency>();
		
		topArtisti = dao.getTopArtisti(mese);
		
		return topArtisti;
	}
	
	public List<Country> getTopCountries(Month mese) {
		
		List<Country> countries = new LinkedList<Country>();
		
		for(ArtistFrequency af : doElenco(mese)) {
			
			List<Country> parziale = dao.getCountriesForArtist(af.getId(), mese);
			
			for(Country c : parziale) {
				if(!countries.contains(c))
					countries.add(c);
			}
		}
		
		this.topCountries = countries;
		
		return topCountries;
	}

	
	public void creaGrafo(Month mese) {
		
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(grafo, this.topCountries);
		
		List<CountryPairFrequency> freq = dao.getArtistsForCountryPairs(mese);
		
		for(Country c1 : grafo.vertexSet()) { //topCountries
			for(Country c2 : grafo.vertexSet()) {
				if(c1.getId() < c2.getId()) { // c1.getId()!=c2.getId()
					int peso = calcolaPeso(c1, c2, freq);
					
					if(peso!=0) {
						// creo l'arco e assegno il peso
						Graphs.addEdge(grafo, c1, c2, peso);
					}
					
				}
			}
		}
		
	}
	
	private int calcolaPeso(Country c1, Country c2, List<CountryPairFrequency> freq) {
		
		for(CountryPairFrequency cf : freq) {
			if(c1.getId()==cf.getPaese1() && c2.getId()==cf.getPaese2())
				return cf.getNumeroArtisti();
		}
		
		return 0;
	}
	
	public int maxDistanzaCountry() { // cioe' chi ha il peso maggiore
		int max = 0;
		
		for(DefaultWeightedEdge e : grafo.edgeSet()) {
			if(grafo.getEdgeWeight(e) > max)
				max = (int) grafo.getEdgeWeight(e);
		}
		
		return max;
	}
}

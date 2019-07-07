package it.polito.tdp.music.db;

import it.polito.tdp.music.model.Artist;
import it.polito.tdp.music.model.ArtistFrequency;
import it.polito.tdp.music.model.City;
import it.polito.tdp.music.model.Country;
import it.polito.tdp.music.model.CountryPairFrequency;
import it.polito.tdp.music.model.Listening;
import it.polito.tdp.music.model.Track;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class MusicDAO {

	public List<Country> getAllCountries() {
		final String sql = "SELECT id, country FROM country" ;

		List<Country> countries = new LinkedList<Country>() ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;

			ResultSet res = st.executeQuery() ;

			while( res.next() ) {
				countries.add( new Country(res.getInt("id"), res.getString("country"))) ;
			}

			conn.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}

		return countries ;

	}

	public List<City> getAllCities() {
		final String sql = "SELECT id, city FROM city" ;

		List<City> cities = new LinkedList<City>() ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;

			ResultSet res = st.executeQuery() ;

			while( res.next() ) {
				cities.add( new City(res.getInt("id"), res.getString("city"))) ;
			}

			conn.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}

		return cities ;

	}


	public List<Artist> getAllArtists() {
		final String sql = "SELECT id, artist FROM artist" ;

		List<Artist> artists = new LinkedList<Artist>() ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;

			ResultSet res = st.executeQuery() ;

			while( res.next() ) {
				artists.add( new Artist(res.getInt("id"), res.getString("artist"))) ;
			}

			conn.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}

		return artists ;

	}

	public List<Track> getAllTracks() {
		final String sql = "SELECT id, track FROM track" ;

		List<Track> tracks = new LinkedList<Track>() ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;

			ResultSet res = st.executeQuery() ;

			while( res.next() ) {
				tracks.add( new Track(res.getInt("id"), res.getString("track"))) ;
			}

			conn.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}

		return tracks ;

	}

	public List<Listening> getAllListenings() {
		final String sql = "SELECT id, userid, month, weekday, longitude, latitude, countryid, cityid, artistid, trackid FROM listening" ;

		List<Listening> listenings = new LinkedList<Listening>() ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;

			ResultSet res = st.executeQuery() ;

			while( res.next() ) {
				listenings.add( new Listening(res.getLong("id"), res.getLong("userid"), res.getInt("month"), res.getInt("weekday"),
						res.getDouble("longitude"), res.getDouble("latitude"), res.getInt("countryid"), res.getInt("cityid"),
						res.getInt("artistid"), res.getInt("trackid"))) ;
			}

			conn.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}

		return listenings ;

	}




	public static void main(String[] args) {
		MusicDAO dao = new MusicDAO() ;

		List<Country> countries = dao.getAllCountries() ;
		//System.out.println(countries) ;

		List<City> cities = dao.getAllCities() ;
		//System.out.println(cities) ;

		List<Artist> artists = dao.getAllArtists() ;

		List<Track> tracks = dao.getAllTracks() ;

		List<Listening> listenings = dao.getAllListenings() ;



		System.out.format("Loaded %d countries, %d cities, %d artists, %d tracks, %d listenings\n", 
				countries.size(), cities.size(), artists.size(), tracks.size(), listenings.size()) ;
	}

	public List<Integer> getMonths() {

		String sql = "SELECT DISTINCT month " + 
				"FROM listening " + 
				"GROUP BY month";

		List<Integer> mesi = new LinkedList<Integer>();

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;

			ResultSet res = st.executeQuery() ;

			while(res.next() ) {
				mesi.add(res.getInt("month"));
			}

			conn.close() ;
		} catch (SQLException e) {

			e.printStackTrace();
			return null ;
		}

		return mesi;
	}

	public List<ArtistFrequency> getTopArtisti(Month mese) {
		String sql = "SELECT l.artistid AS id1, a2.artist AS a3, COUNT(*) AS cnt " + 
				"FROM listening AS l, artist AS a2 " + 
				"WHERE MONTH = ? AND l.artistid = a2.id " + 
				"GROUP BY id1, a3 " + 
				"ORDER BY cnt DESC " + 
				"LIMIT 0,20";

		List<ArtistFrequency> topArtisti = new LinkedList<ArtistFrequency>();

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, mese.getValue());

			ResultSet res = st.executeQuery() ;

			while(res.next()) {
				topArtisti.add(new ArtistFrequency(res.getInt("id1"), res.getString("a3"), res.getInt("cnt")));
			}

			conn.close() ;
		} catch (SQLException e) {

			e.printStackTrace();
			return null ;
		}

		return topArtisti;
	}

	public List<Country> getCountriesForArtist(int id, Month mese) {
		
		String sql = "SELECT DISTINCT c.id, c.country "+
				"FROM listening AS l, country as c "+
				"WHERE MONTH = ? AND l.artistid = ? AND l.countryid = c.id";
		
		List<Country> topCountries = new LinkedList<Country>();

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, mese.getValue());
			st.setInt(2, id);

			ResultSet res = st.executeQuery() ;

			while(res.next()) {
				topCountries.add(new Country(res.getInt("c.id"), res.getString("c.country")));
			}

			conn.close() ;
		} catch (SQLException e) {

			e.printStackTrace();
			return null ;
		}

		return topCountries;
	}

	public List<CountryPairFrequency> getArtistsForCountryPairs(Month mese) {
		
		String sql = "SELECT COUNT(DISTINCT l1.artistid) AS cnt, c1.id AS country1, c2.id AS country2 " + 
				"FROM listening l1, listening l2, country c1, country c2 " + 
				"WHERE l1.MONTH = ? and l2.MONTH = ? AND l1.countryid = c1.id AND l2.countryid = c2.id " + 
				"AND l1.artistid = l2.artistid " + 
				"GROUP BY country1, country2";
		
		List<CountryPairFrequency> frequenze = new LinkedList<CountryPairFrequency>();
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, mese.getValue());
			st.setInt(2, mese.getValue());

			ResultSet res = st.executeQuery() ;

			while(res.next()) {
				frequenze.add(new CountryPairFrequency(res.getInt("country1"), res.getInt("country2"), res.getInt("cnt")));
			}

			conn.close() ;
		} catch (SQLException e) {

			e.printStackTrace();
			return null ;
		}
		
		return frequenze;
	}

}

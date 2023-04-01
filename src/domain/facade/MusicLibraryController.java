package domain.facade;

import java.util.Optional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

import domain.core.MusicLibrary;
import domain.core.Song;
import domain.core.SongMetaInfo;

public class MusicLibraryController {
	
	MusicLibrary library;
	
	public MusicLibraryController(MusicLibrary library) {
		this.library = library;
	}
	
	/**
	 * gets the number of songs in controlled library
	 * 
	 * @return number of songs in controlled library
	 */
	public int numberOfSongs() {
		return library.size();
	}
	
	/**
	 * add music to library controlled by obtaining meta-information
	 *  about it from the file
	 * 
	 * @requires filename is an mp3 file name 
	 * @param filename mp3 file name
	 */
	public void addSong(String filename) {
		try {
			Mp3File mp3 = new Mp3File(filename);
			if (mp3.hasId3v1Tag()) {
				ID3v1 tag = mp3.getId3v1Tag();
		        String title = tag.getTitle() == null ? "unkown" : tag.getTitle();
		        String artist = tag.getArtist()  == null ? "unkown" : tag.getArtist();
		        String album = tag.getAlbum()  == null ? "unkown" : tag.getAlbum();
		        String genre = tag.getGenreDescription()  == null ? "unkown" : tag.getGenreDescription();
		        List<String> artists = new ArrayList<String>();
		        artists.add(artist);
		        SongMetaInfo songInfo = new SongMetaInfo(title, genre, artists, album);
		        Song song = new Song(songInfo, filename);
		        library.add(song);
			} else if (mp3.hasId3v2Tag()) {
				ID3v2 tag = mp3.getId3v2Tag();
		        String title = tag.getTitle() == null ? "unkown" : tag.getTitle();
		        String artist = tag.getArtist()  == null ? "unkown" : tag.getArtist();
		        String album = tag.getAlbum()  == null ? "unkown" : tag.getAlbum();
		        String genre = tag.getGenreDescription()  == null ? "unkown" : tag.getGenreDescription();
		        List<String> artists = new ArrayList<String>();
		        artists.add(artist);
		        SongMetaInfo songInfo = new SongMetaInfo(title, genre, artists, album);
		        Song song = new Song(songInfo, filename);
		        library.add(song);
			}
		} catch (IOException e) {
			System.err.println("Error reading MP3 file: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Error reading MP3 file: " + e.getMessage());
		}
	}
	
	/**
	 * sends the selection request to the controlled library if possible
	 * 
	 * @requires 0 <= i < numberOfSongs()
	 * @param i
	 */
	public void selectSong(int i) {
		if(i < numberOfSongs() && i >= 0) {
			library.select(i);
		}
	}

	/**
	 * returns the selected song in the library (if any)
	 * 
	 * @return selected song in the library (if any)
	 */
	public Optional<ISong> getSelectedSong() {
		
		if (library.someSelected()) {
			return Optional.of(library.getSelected());
		}
		
		return Optional.empty();
	}
	
	/**
	 * deletes the selected song in the library (if it exists)
	 */
	public void removeSelectedSong() {
		library.remove();
	}
	
	/**
	 * if any music is selected, determines the interruption of the music that is 
	 * playing (if that's the case) and starts playing the currently selected song in the library 
	 * controlled; a song's counter is incremented each time the song is played to the end; if 
	 * no music is selected does nothing
	 */
	public void play() {
		if (library.someSelected()) {
			library.play();
		}
	}
	
	/**
	 * stops the music that was being played
	 */
	public void stop() {
		if (library.isPlaying()) {
			library.stop();
		}
	}
	
	/**
	 * passes the rating of the selected song in the controlled library (if it exists)
	 * to the value immediately above what it currently has (or stays the same if 
	 * this value is already the maximum)
	 */
	public void incRateSelected() {
		library.incRateSelected();
	}
	
	/**
	 * passes the rating of the selected song in the controlled library (if it exists)
	 * to the value immediately bellow what it currently has (or stays the same if 
	 * this value is already the minimum)
	 */
	public void decRateSelected() {
		library.decRateSelected();
	}
	
	/**
	 * returns an iterable structure with the songs from the controlled
	 * library that match the given regular expression
	 * 
	 * @param reexp regular expression
	 * @return iterable structure with the songs from the controlled library that match the given regular expression
	 */
	public Iterable<ISong> getMatches(String reexp) {
		return library.getMatches(reexp);
	}
	
	/**
	 * returns an iterable structure with the songs in the library
	 * 
	 * @return iterable structure with the songs in the library
	 */
	public Iterable<ISong> getSongs() {
		return library.getSongs();
	}
}

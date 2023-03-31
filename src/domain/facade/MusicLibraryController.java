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
	
	public int numberOfSongs() {
		return library.size();
	}
	
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
	
	public void selectSong(int i) {
		if(i < numberOfSongs() && i >= 0) {
			library.select(i);
		}
	}

	public Optional<ISong> getSelectedSong() {
		
		if (library.someSelected()) {
			return Optional.of(library.getSelected());
		}
		
		return Optional.empty();
	}
	
	public void removeSelectedSong() {
		library.remove();
	}
	
	public void play() {
		if (library.someSelected()) {
			library.play();
		}
	}
	
	public void stop() {
		if (library.isPlaying()) {
			library.stop();
		}
	}
	
	public void incRateSelected() {
		library.incRateSelected();
	}
	
	public void decRateSelected() {
		library.decRateSelected();
	}
	
	public Iterable<ISong> getMatches(String reexp) {
		return library.getMatches(reexp);
	}
	
	public Iterable<ISong> getSongs() {
		return library.getSongs();
	}
}

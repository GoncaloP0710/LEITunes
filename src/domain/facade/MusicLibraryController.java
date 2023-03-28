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
		        String title = tag.getTitle();
		        String artist = tag.getArtist();
		        String album = tag.getAlbum();
		        String genre = tag.getGenreDescription();
		        List<String> artists = new ArrayList<String>();
		        artists.add(artist);
		        SongMetaInfo songInfo = new SongMetaInfo(title, genre, artists, album);
		        Song song = new Song(songInfo, filename);
		        library.add(song);
			} else if (mp3.hasId3v2Tag()) {
				ID3v2 tag = mp3.getId3v2Tag();
				String title = tag.getTitle();
		        String artist = tag.getArtist();
		        String album = tag.getAlbum();
		        String genre = tag.getGenreDescription();
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
		return Optional.ofNullable(library.getSelected());
	}
	
	public void removeSelectedSong() {
		library.remove();
	}
	
	//------------------------------------------------
	public void play() {
		if(library.someSelected()) {
			if(library.isPlaying()) {
				library.stop();
			}
			library.play();
			//o contador de uma música é incrementado sempre que a música é tocada até ao fim
			//Como é q eu sei se foi tocada ate ao fim?
			library.getSelected().incTimesPlayed();
		}
	}
	
	public void stop() {
		library.stop();
		//se essa música foi posta a tocar através da 
		//Como é q eu sei isso?
	}
	//------------------------------------------------
	
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

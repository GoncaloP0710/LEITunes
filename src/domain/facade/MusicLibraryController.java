package domain.facade;

import java.util.Optional;
import java.io.File;
import java.util.List;

import domain.core.MusicLibrary;

public class MusicLibraryController {
	
	MusicLibrary library;
	
	public MusicLibraryController(MusicLibrary library) {
		this.library = library;
	}
	
	public int numberOfSongs() {
		return library.size();
	}
	
	//------------------------------------------------
	public void addSong(String filename) {

		
		Song song = new Song(, filename);
		library.add(song);
	}
	//------------------------------------------------
	
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
			//o contador de uma m�sica � incrementado sempre que a m�sica � tocada at� ao fim
			//Como � q eu sei se foi tocada ate ao fim?
			library.getSelected().incTimesPlayed();
		}
	}
	
	public void stop() {
		library.stop();
		//se essa m�sica foi posta a tocar atrav�s da 
		//Como � q eu sei isso?
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

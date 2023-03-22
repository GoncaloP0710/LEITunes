package domain.core;

import domain.facade.ISong;
import util.observer.Event;

public abstract class SongLibraryEvent implements Event {
	private ISong song;
	private MusicLibrary library;
	
	public SongLibraryEvent(ISong song1, MusicLibrary library1) {
		song = song1;
		library = library1;
	}
	
	public ISong getSong() {
		return song;
	}
	
	public MusicLibrary getLibrary () {
		return library;
	}
}

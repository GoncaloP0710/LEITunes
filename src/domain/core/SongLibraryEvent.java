package domain.core;

import domain.facade.ISong;
import util.observer.Event;

/**
 * represents a generic event about a song in a library
 */
public abstract class SongLibraryEvent implements Event {
	private ISong song;
	private MusicLibrary library;

	/**
	 * Builds an object that represents a generic event about a song in a library
	 * 
	 * @param song1    music affected by the event
	 * @param library1 library affected by the event
	 */
	public SongLibraryEvent(ISong song1, MusicLibrary library1) {
		song = song1;
		library = library1;
	}

	/**
	 * load information about the song
	 * 
	 * @return music affected by the event
	 */
	public ISong getSong() {
		return song;
	}

	/**
	 * load information about the library
	 * 
	 * @return library affected by the event
	 */
	public MusicLibrary getLibrary() {
		return library;
	}
}

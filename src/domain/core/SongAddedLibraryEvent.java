package domain.core;

import domain.facade.ISong;

/**
 * represents a event of a song being added
 */
public class SongAddedLibraryEvent extends SongLibraryEvent {

	/**
	 * Builds an object that represents a song added event about a song in a library
	 * 
	 * @param song1    music affected by the event
	 * @param library1 library affected by the event
	 */
	public SongAddedLibraryEvent(ISong song1, MusicLibrary library1) {
		super(song1, library1);
	}
}

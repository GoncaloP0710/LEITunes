package domain.core;

import domain.facade.ISong;

public class SongRemovedLibraryEvent extends SongLibraryEvent {

	/**
	 * Builds an object that represents a song removed event about a song in a library
	 * 
	 * @param song1 music affected by the event
	 * @param library1 library affected by the event
	 */
	public SongRemovedLibraryEvent(ISong song1, MusicLibrary library1) {
		super(song1, library1);
	}

}

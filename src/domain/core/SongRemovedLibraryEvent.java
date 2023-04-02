package domain.core;

import domain.facade.ISong;

/**
 * represents a event about a song being removed
 */
public class SongRemovedLibraryEvent extends SongLibraryEvent {

	/**
	 * Builds an object that represents a event about a song being removed
	 * 
	 * @param song1    music affected by the event
	 * @param library1 library affected by the event
	 */
	public SongRemovedLibraryEvent(ISong song1, MusicLibrary library1) {
		super(song1, library1);
	}

}

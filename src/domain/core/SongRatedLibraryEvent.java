package domain.core;

import domain.facade.ISong;

public class SongRatedLibraryEvent extends SongLibraryEvent {

	public SongRatedLibraryEvent(ISong song1, MusicLibrary library1) {
		super(song1, library1);
	}

}

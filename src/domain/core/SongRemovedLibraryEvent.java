package domain.core;

import domain.facade.ISong;

public class SongRemovedLibraryEvent extends SongLibraryEvent {

	public SongRemovedLibraryEvent(ISong song1, MusicLibrary library1) {
		super(song1, library1);
	}

}

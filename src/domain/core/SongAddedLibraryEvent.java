package domain.core;

import domain.facade.ISong;

public class SongAddedLibraryEvent extends SongLibraryEvent{
	
	public SongAddedLibraryEvent (ISong song1, MusicLibrary library1) {
		super(song1, library1);
	}
}

package domain.core;

import domain.facade.ISong;
import util.observer.Event;

public abstract class SongLibraryEvent implements Event {
	private ISong song;
	private MusicLibrary library;
}

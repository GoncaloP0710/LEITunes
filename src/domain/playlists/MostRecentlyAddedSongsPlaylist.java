package domain.playlists;

import java.util.Iterator;

import domain.core.MusicLibrary;
import domain.core.SongLibraryEvent;
import domain.facade.ISong;

public class MostRecentlyAddedSongsPlaylist extends SmartPlaylist{

	private SmartPlaylist playlistSmart;
	protected final int maxNumSongs;
	
	public MostRecentlyAddedSongsPlaylist(MusicLibrary library) {
		super("MostRecentlyAddedSongsPlaylist", library);
		this.maxNumSongs = 10;
	}
	
	/**
	 * Reaction to events, namely those emitted by the music library that 
	 * backs up this playlist (can affect the content of the playlist)
	 */
	@Override
	public void processEvent(SongLibraryEvent event) {
		
	}

}

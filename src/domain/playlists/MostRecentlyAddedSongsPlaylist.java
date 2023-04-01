package domain.playlists;


import java.util.ArrayList;
import java.util.List;

import domain.core.MusicLibrary;
import domain.core.SongAddedLibraryEvent;
import domain.core.SongLibraryEvent;
import domain.core.SongRatedLibraryEvent;
import domain.core.SongRemovedLibraryEvent;
import domain.facade.ISong;

public class MostRecentlyAddedSongsPlaylist extends SmartPlaylist{

	protected final int maxNumSongs;
	private int numSongs;
	
	private List<ISong> listSongs;
	
	/**
	 * represents a play list where the 4 songs are 
	 * most recently added to a given library
	 * 
	 * @param library library of the play list
	 */
	public MostRecentlyAddedSongsPlaylist(MusicLibrary library) {
		super("MostRecentlyAddedSongsPlaylist", library);
		
		this.maxNumSongs = 4;
		numSongs = 0;
		
		listSongs = new ArrayList<>();
	}
	
	/**
	 * Reaction to events, namely those emitted by the music library that 
	 * backs up this play list (can affect the content of the play list)
	 */
	@Override
	public void processEvent(SongLibraryEvent event) {
		if(event instanceof SongAddedLibraryEvent) {
			if (numSongs != maxNumSongs) {
				this.add(event.getSong());
				numSongs++;
			}else {
				this.select(0);
				this.remove();
				this.add(event.getSong());
			}
			listSongs.add(event.getSong());
		} else if (event instanceof SongRemovedLibraryEvent) {
			for (int i = 0; i < listSongs.size(); i++) {
				if (event.getSong() == listSongs.get(i)) {
					listSongs.remove(i);
					break;
				}
			}
			boolean songRemovedWasOnList = false;
			this.select(0);
			for (int i = 0; i < this.size(); i++) {
				if (this.getSelected() == (event.getSong())) {
					this.remove();
					songRemovedWasOnList = true;
				}
				this.next();
			}
			if (songRemovedWasOnList) {
				if (listSongs.size() <= this.size()) {
					return;
				}else {
					this.add(listSongs.get(listSongs.size()-4));
				}
			}
		} 
	}
}

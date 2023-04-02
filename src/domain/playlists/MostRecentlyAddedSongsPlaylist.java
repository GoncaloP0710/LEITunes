package domain.playlists;

import java.util.ArrayList;
import java.util.List;

import domain.core.MusicLibrary;
import domain.core.SongAddedLibraryEvent;
import domain.core.SongLibraryEvent;
import domain.core.SongRemovedLibraryEvent;
import domain.facade.ISong;

/**
 * playlist of the most recently added
 */
public class MostRecentlyAddedSongsPlaylist extends SmartPlaylist {

	protected final int maxNumSongs;
	private int numSongs;

	private List<ISong> listSongs;

	/**
	 * represents a play list where the 5 songs are
	 * most recently added to a given library
	 * 
	 * @param library library of the play list
	 */
	public MostRecentlyAddedSongsPlaylist(MusicLibrary library) {
		super("Most Recently Added", library);

		this.maxNumSongs = 5;
		numSongs = 0;

		listSongs = new ArrayList<>();
	}

	/**
	 * Reaction to events, namely those emitted by the music library that
	 * backs up this play list (can affect the content of the play list)
	 */
	@Override
	public void processEvent(SongLibraryEvent event) {
		if (event instanceof SongAddedLibraryEvent) {
			if (numSongs != maxNumSongs) {
				super.add(event.getSong());
				numSongs++;
			} else {
				super.select(0);
				super.remove();
				super.add(event.getSong());
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
			super.select(0);
			for (int i = 0; i < super.size(); i++) {
				if (super.getSelected() == (event.getSong())) {
					super.remove();
					songRemovedWasOnList = true;
				}
				super.next();
			}
			if (songRemovedWasOnList) {
				if (listSongs.size() <= super.size()) {
					return;
				} else {
					super.add(listSongs.get(listSongs.size() - 5));
				}
			}
		}
	}

	/**
	 * creates a string that represents a playlist of the most recently added songs
	 * 
	 * @return string that represents a playlist of the most recently added songs
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(super.toString());
		return str.toString();
	}
}

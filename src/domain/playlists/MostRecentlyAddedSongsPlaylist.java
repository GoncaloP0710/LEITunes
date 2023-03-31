package domain.playlists;


import domain.core.MusicLibrary;
import domain.core.SongAddedLibraryEvent;
import domain.core.SongLibraryEvent;
import domain.core.SongRemovedLibraryEvent;

public class MostRecentlyAddedSongsPlaylist extends SmartPlaylist{

	protected final int maxNumSongs;
	private int numSongs;
	
	public MostRecentlyAddedSongsPlaylist(MusicLibrary library) {
		super("MostRecentlyAddedSongsPlaylist", library);
		this.maxNumSongs = 4;
		numSongs = 0;
	}
	
	/**
	 * Reaction to events, namely those emitted by the music library that 
	 * backs up this playlist (can affect the content of the playlist)
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
		} else if (event instanceof SongRemovedLibraryEvent) {
			this.select(0);
			for (int i = 0; i < this.size(); i++) {
				if (this.getSelected() == (event.getSong())) {
					this.remove();
				}
				this.next();
			}
		}
	}
}

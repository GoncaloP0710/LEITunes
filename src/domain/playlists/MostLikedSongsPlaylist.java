package domain.playlists;

import java.util.ArrayList;

import domain.core.MusicLibrary;
import domain.core.Rate;
import domain.core.SongAddedLibraryEvent;
import domain.core.SongLibraryEvent;
import domain.core.SongRatedLibraryEvent;
import domain.core.SongRemovedLibraryEvent;
import domain.facade.ISong;

public class MostLikedSongsPlaylist extends SmartPlaylist{
	
	protected final int maxNumSongs;
	private int numSongs;
	
	private Rate lowestRate;
	private int lowestRateIndex;

	public MostLikedSongsPlaylist(MusicLibrary library) {
		super("MostLikedSongsPlaylist", library);
		
		this.maxNumSongs = 4;
		numSongs = 0;
		
		lowestRate = Rate.LOW;
		lowestRateIndex = 0;
	}
	
	/**
	 * Reaction to events, namely those emitted by the music library that 
	 * backs up this playlist (can affect the content of the playlist)
	 */
	@Override
	public void processEvent(SongLibraryEvent event) {
		Rate rating = event.getSong().getRating();
		if(event instanceof SongRatedLibraryEvent) {
			if (numSongs < maxNumSongs) {
				this.add(event.getSong());
				numSongs++;
				return;
			}else {
				this.select(0);
				for (int i = 0; i < 10; i++) {
					if (!this.getSelected().getRating().isHigher(lowestRate)) {
						lowestRate = this.getSelected().getRating();
						lowestRateIndex = i;
					}
					this.next();
				}
				this.select(lowestRateIndex);
				//Remover o lowestRate e adicionar a nova musica
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

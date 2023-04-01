package domain.playlists;

import java.util.ArrayList;
import java.util.List;

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
	
	private List<ISong> listSongs;

	/**
	 * represents a smart play list where the 4 songs are 
	 * the highest rated for a given library
	 * 
	 * @param library library of the play list
	 */
	public MostLikedSongsPlaylist(MusicLibrary library) {
		super("MostLikedSongsPlaylist", library);
		
		this.maxNumSongs = 4;
		numSongs = 0;
		
		lowestRate = Rate.LOW;
		lowestRateIndex = 0;
		
		listSongs = new ArrayList<>();
	}
	
	/**
	 * Reaction to events, namely those emitted by the music library that 
	 * backs up this play list (can affect the content of the play list)
	 * 
	 * @param event event that occurred
	 */
	@Override
	public void processEvent(SongLibraryEvent event) {
		Rate eventSongRating = event.getSong().getRating();
		if (event instanceof SongAddedLibraryEvent) {
			if (numSongs < maxNumSongs) {
				this.add(event.getSong());
				numSongs++;
				return;
			}else {
				this.select(0);
				//Scrolls the list looking for the lowest rate 
				for (int i = 0; i < this.size(); i++) {
					if (!this.getSelected().getRating().isHigherOrSame(lowestRate)) {
						lowestRate = this.getSelected().getRating();
						lowestRateIndex = i;
					}
					this.next();
				}
				if (eventSongRating.isHigherOrSame(lowestRate)) {
					this.select(lowestRateIndex);
					//Remove the lowestRate and add the new song
					this.remove();
					this.add(event.getSong());
				}
			}
			listSongs.add(event.getSong());
		} else if (event instanceof SongRemovedLibraryEvent) {
			this.select(0);
			for (int i = 0; i < this.size(); i++) {
				if (this.getSelected() == (event.getSong())) {
					this.remove();
					listSongs.remove(i);
					return;
				}
				this.next();
			}
			for (int i = 0; i < listSongs.size(); i++) {
				if (listSongs.get(i) == (event.getSong())) {
					listSongs.remove(i);
					return;
				}
			}
			return;
		} else if (event instanceof SongRatedLibraryEvent) {
			boolean songChangedIsOnPlaylist = false;
			this.select(0);
			//Scrolls the list looking for the lowest rate 
			for (int i = 0; i < this.size(); i++) {
				if (!this.getSelected().getRating().isHigherOrSame(lowestRate)) {
					lowestRate = this.getSelected().getRating();
					lowestRateIndex = i;
				}
				if (this.getSelected() == event.getSong()) {
					songChangedIsOnPlaylist = true;
				}
				this.next();
			}
			if (!songChangedIsOnPlaylist) {
				if (event.getSong().getRating().isHigherOrSame(lowestRate)) {
					this.select(lowestRateIndex);
					this.remove();
					this.add(event.getSong());
				}
			} else {
				if (!event.getSong().getRating().isHigher(lowestRate)) {
					//get highest rated song that is not on the top 4 
					ISong highestSong = null;
					for (int i = 0; i < listSongs.size(); i++) {
						for (int j = 0; j < this.size(); j++) {
							
						}
					}

				}
			}
		}
	}	
}

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

/**
 * playlist of the most liked songs
 */
public class MostLikedSongsPlaylist extends SmartPlaylist {

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
		super("Most Liked", library);

		this.maxNumSongs = 5;
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
		if (event instanceof SongAddedLibraryEvent) {
			listSongs.add(event.getSong());
		} else if (event instanceof SongRemovedLibraryEvent) {
			if (this.size() > 0) {
				this.select(0);
				for (int i = 0; i < this.size(); i++) {
					if (this.getSelected() == (event.getSong())) {
						this.remove();
					}
					this.next();
				}
			}
			for (int i = 0; i < listSongs.size(); i++) {
				if (listSongs.get(i) == (event.getSong())) {
					listSongs.remove(i);
				}
			}
		} else if (event instanceof SongRatedLibraryEvent) {
			if (numSongs < maxNumSongs) {
				this.add(event.getSong());
				numSongs++;
			} else {
				boolean songChangedIsOnPlaylist = false;
				int indexOfSongChanged = 0;
				this.select(0);
				// Scrolls the play list looking for the lowest rate
				// If it finds the changed song songChangedIsOnPlaylist = true && saves its
				// index
				for (int i = 0; i < this.size(); i++) {
					if (!this.getSelected().getRating().isHigherOrSame(lowestRate)) {
						lowestRate = this.getSelected().getRating();
						lowestRateIndex = i;
					}
					if (this.getSelected() == event.getSong()) {
						songChangedIsOnPlaylist = true;
						indexOfSongChanged = i;
					}
					this.next();
				}
				// if the song that was changed is not on the play list then checks if its
				// rating is
				// higher than the lowest of the play list and if it is swaps both
				if (!songChangedIsOnPlaylist) {
					if (event.getSong().getRating().isHigherOrSame(lowestRate)) {
						this.select(lowestRateIndex);
						this.remove();
						this.add(event.getSong());
					}
				} else {
					// checks if the rate of the song changed is higher than the lowest of the play
					// list
					// because if it is, there is no need to do anything
					if (!event.getSong().getRating().isHigher(lowestRate)) {
						// get highest rated song that is not on the top 4
						ISong highestSong = null;
						Rate highestRate = Rate.LOW;
						boolean belongsToTop4 = false;
						for (int i = 0; i < listSongs.size(); i++) {
							belongsToTop4 = false;
							this.select(0);
							for (int j = 0; j < this.size(); j++) {
								if (listSongs.get(i) == this.getSelected()) {
									belongsToTop4 = true;
									break;
								}
								this.next();
							}
							if (!belongsToTop4) {
								if (listSongs.get(i).getRating().isHigherOrSame(highestRate)) {
									highestRate = listSongs.get(i).getRating();
									highestSong = listSongs.get(i);
								}
							}
						}
						// if the highest rated song that is not on the top 4 is higher rating than the
						// one that got changed, swaps both
						if (!event.getSong().getRating().isHigherOrSame(highestRate)) {
							this.select(indexOfSongChanged);
							this.remove();
							this.add(highestSong);
						}
					}
				}
			}
		}
	}

	/**
	 * creates a string that represents a playlist of the most liked songs
	 * 
	 * @return string that represents a playlist of the most liked songs
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(super.toString());
		return str.toString();
	}
}

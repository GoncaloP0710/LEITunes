package domain.playlists;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import domain.core.MusicLibrary;
import domain.core.Rate;
import domain.core.Song;
import domain.core.SongAddedLibraryEvent;
import domain.core.SongLibraryEvent;
import domain.core.SongRatedLibraryEvent;
import domain.core.SongRemovedLibraryEvent;
import domain.facade.ISong;
import domain.player.Player;
import domain.player.PlayerFactory;
import util.adts.AbsQListWithSelection;
import util.adts.ArrayQListWithSelection;

/**
 * Represents a playlist
 */
public abstract class AbsPlaylist implements Playlist {

	private MusicLibrary library;
	private ArrayQListWithSelection<ISong> playlist;
	private String playlistName;
	private Player player = PlayerFactory.INSTANCE.getPlayer();
	private ISong songPlaying;

	public AbsPlaylist(String name, MusicLibrary library1) {
		library = library1;
		playlistName = name;
		playlist = new ArrayQListWithSelection<ISong>();
		this.player.addListener(this);
		songPlaying = null;
	}

	/**
	 * Returns the number of songs in the play list
	 * 
	 * @return the number of songs in the play list
	 * @ensures \return >= 0
	 */
	@Override
	public int size() {
		return playlist.size();
	}

	/**
	 * Returns the selected song
	 * 
	 * @return the selected song
	 * @requires someSelected()
	 * @ensures \return != null
	 */
	@Override
	public ISong getSelected() {
		// Require of getSelected() is not necessary because it is the same as require
		// of the function
		return playlist.getSelected();
	}

	/**
	 * Returns true if some element is selected
	 * 
	 * @return true if some element is selected, false otherwise
	 */
	@Override
	public boolean someSelected() {
		return playlist.someSelected();
	}

	/**
	 * Adds a song to the end of the play list, if it
	 * does not exist yet and selects it,
	 * if addition is possible
	 *
	 * @param song the element to be added
	 * @requires song != null
	 * @return true if the song was added to the play list, false otherwise
	 * @ensures \result ==> size() == \old(size()) + 1 &&
	 *          someSelected() &&
	 *          getIndexSelected() == size() - 1
	 */
	@Override
	public boolean add(ISong song) {
		boolean exist = false;
		for (int i = 0; i < size(); i++) {
			if (playlist.get(i) == song) {
				exist = true;
				break;
			}
		}
		if (!exist) {
			playlist.add(song);
			return true;
		}
		return false;
	}

	/**
	 * Removes the selected element from the playlist, if possible
	 *
	 * @return true if the song was removed, false otherwise
	 * @ensures \return && \old someSelected() ==>
	 *          !someSelected() && size() == \old(size()) - 1
	 * @ensures !\return ==> \old someSelected() == someSelected()
	 *          && size() == \old(size())
	 */
	@Override
	public boolean remove() {
		if (playlist.someSelected()) {
			playlist.remove();
			return true;
		}
		return false;
	}

	/**
	 * Selects song at position i
	 * 
	 * @param i the position denoting the element to be selected
	 * @requires 0 <= i < size()
	 * @ensures someSelected() && getIndexSelected() == i &&
	 *          size() == \old(size())
	 */
	@Override
	public void select(int i) {
		playlist.select(i);
	}

	/**
	 * Moves the current selected song up to position i,
	 * shifting down all elements in the playlist from
	 * positions i+1 to \old getIndexSelected()-1,
	 * if movement in the playlist is possible
	 * 
	 * @param i the index where this element is going to be moved
	 * @requires someSelected() && 0 <= i < getIndexSelected()
	 * @ensures \return ==> someSelected() &&
	 *          getIndexSelected() == i &&
	 *          size() == \old(size())
	 */
	@Override
	public boolean moveUpSelected(int i) {
		return playlist.moveUpSelected(i);
	}

	/**
	 * Returns the index of the selected element, if any
	 * 
	 * @return the index of the selected element, if any
	 * @requires someSelected()
	 * @ensures 0 <= \return < size()
	 */
	@Override
	public int getIndexSelected() {
		// Require of getSelected() is not necessary because it is the same as require
		// of the function
		return playlist.getIndexSelected();

	}

	/**
	 * Selects the next element, if any. Otherwise, no element is selected.
	 *
	 * @requires someSelected()
	 * @ensures if \old getIndexSelected() < size() - 1
	 *          then getIndexSelected() = \old getIndexSelected() + 1
	 *          else !someSelected()
	 * @ensures size() == \old(size())
	 */
	@Override
	public void next() {
		// Require of getSelected() is not necessary because it is the same as require
		// of the function
		if (playlist.getIndexSelected() < playlist.size() - 1) {
			playlist.select(getIndexSelected() + 1);
		} else {
			playlist.deSelect();
		}
	}

	/**
	 * Selects the previous element, if any. Otherwise, no element is selected.
	 *
	 * @requires someSelected()
	 * @ensures if \old getIndexSelected() > 0
	 *          then getIndexSelected() = \old getIndexSelected() - 1
	 *          else !someSelected()
	 * @ensures size() == \old(size())
	 */
	@Override
	public void previous() {
		if (playlist.getIndexSelected() > 0) {
			playlist.select(getIndexSelected() - 1);
		} else {
			playlist.deSelect();
		}
	}

	/**
	 * Returns the name of the playlist
	 * 
	 * @return the name of the playlist
	 * @ensures \result != null
	 */
	@Override
	public String getName() {
		return this.playlistName;
	}

	@Override
	public Iterator<ISong> iterator() {
		return playlist.iterator();
	}

	/**
	 * Returns if a song is playing and the play action has been performed via the
	 * playlist
	 * 
	 * @return true if a song is playing and the play action was done through the
	 *         playlist,
	 *         false otherwise
	 */
	@Override
	public boolean isPlaying() {
		return songPlaying != null;
	}

	/**
	 * Plays the selected song
	 * 
	 * @requires someSelected()
	 * @ensures isPlaying()
	 */
	@Override
	public void play() {
		if (isPlaying()) {
			stop();
		}
		player.load(playlist.getSelected().getFilename());
		player.play();
		songPlaying = getSelected();
	}

	/**
	 * Stops the playing song
	 * 
	 * @requires isPlaying()
	 */
	@Override
	public void stop() {
		songPlaying = null;
		player.stop();
	}

	/**
	 * creates a string that represents a playlist
	 * 
	 * @return string that represents a playlist
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("*-- Playlist ").append(playlistName).append("--*");
		str.append("\n");
		str.append(playlist.toString());
		return str.toString();
	}

	/**
	 * Reaction to property change events, namely those emitted by the player
	 * (can affect the selected song and song being played)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getNewValue() == Player.PlayingState.ENDED && isPlaying()) {
			songPlaying.incTimesPlayed();
			if (someSelected()) {
				next();
				if (someSelected()) {
					play();
				} else {
					songPlaying = null;
				}
			}
		} else if (evt.getNewValue() == Player.PlayingState.STOPED) {
			songPlaying = null;
		}
	}

	/**
	 * Reaction to events, namely those emitted by the music library that
	 * backs up this play list (can affect the content of the play list)
	 */
	@Override
	public void processEvent(SongLibraryEvent e) {
		if (e instanceof SongRemovedLibraryEvent) {
			ISong songSelected = playlist.getSelected();
			playlist.select(0);
			for (int i = 0; i < size(); i++) {
				if (playlist.get(i) == e.getSong()) {
					playlist.remove();
					break;
				}
				playlist.next();
			}
			playlist.select(0);
			for (int i = 0; i < size(); i++) {
				if (playlist.get(i) == songSelected) {
					playlist.select(i);
					break;
				}
				playlist.next();
			}
		}
	}
}
package domain.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import domain.facade.ISong;
import domain.player.Player;
import domain.player.PlayerFactory;
import util.adts.AbsQListWithSelection;
import util.observer.Listener;
import util.observer.Subject;

/**
 * Represents a music library
 */
public class MusicLibrary extends AbsQListWithSelection<Song>
		implements Subject<SongLibraryEvent>, PropertyChangeListener {

	private Song songPlaying;
	private Player player = PlayerFactory.INSTANCE.getPlayer();
	private List<Listener<SongLibraryEvent>> listeners = new ArrayList<>();

	/**
	 * Creates a music Library
	 */
	public MusicLibrary() {
		super();
		songPlaying = null;
		this.player.addListener(this);
	}

	/**
	 * determines to stop the currently playing song (if that is the case)
	 * and starts playing the selected song
	 * 
	 * @requires someSelected()
	 */
	public void play() {
		if (isPlaying()) {
			stop();
		}
		// Require of getSelected() is not necessary because it is the same as require
		// of the function
		player.load(getSelected().getFilename());
		songPlaying = getSelected();
		player.play();
	}

	/**
	 * indicates whether a song that was played via the library is playing
	 * 
	 * @return True if a song is playing and False otherwise
	 */
	public boolean isPlaying() {
		return songPlaying != null;
	}

	/**
	 * stops the music that was being played
	 * 
	 * @requires isPlaying()
	 */
	public void stop() {
		songPlaying = null;
		player.stop();
	}

	/**
	 * moves the rating of the selected song to the value immediately above the one
	 * it
	 * currently has (or stays the same if this value is already the maximum)
	 */
	public void incRateSelected() {
		if (someSelected()) {
			getSelected().incRating();
			SongRatedLibraryEvent evt = new SongRatedLibraryEvent(super.getSelected(), this);
			emitEvent(evt);
		}
	}

	/**
	 * moves the rating of the selected song to the value immediately
	 * below the one it currently has (or stays the same if this value is already
	 * the minimum)
	 */
	public void decRateSelected() {
		if (someSelected()) {
			super.getSelected().decRating();
			SongRatedLibraryEvent evt = new SongRatedLibraryEvent(super.getSelected(), this);
			emitEvent(evt);
		}

	}

	/**
	 * returns an iterable structure with the songs in
	 * the library that match the given regular expression
	 * 
	 * @param reexp regular expression
	 * @return iterable structure with the library songs that match the given
	 *         regular expression
	 */
	public Iterable<ISong> getMatches(String reexp) {
		ArrayList<ISong> list = new ArrayList<ISong>();
		for (int i = 0; i < super.objList.size(); i++) {
			if (super.objList.get(i).matches(reexp)) {
				list.add(super.objList.get(i));
			}
		}
		return list;
	}

	/**
	 * returns an iterable structure with the songs in the library in proper order
	 * 
	 * @return iterable structure with the songs in the library in proper order
	 */
	public Iterable<ISong> getSongs() {
		ArrayList<ISong> list = new ArrayList<ISong>();
		for (int i = 0; i < super.objList.size(); i++) {
			list.add(super.objList.get(i));
		}
		return list;

	}

	/**
	 * adds a song to the library
	 * 
	 * @param song the song to be added
	 */
	@Override
	public void add(Song song) {
		SongAddedLibraryEvent evt = new SongAddedLibraryEvent(song, this);
		emitEvent(evt);
		super.add(song);
	}

	/**
	 * removes a song of the library
	 * 
	 * @param song the song to be removed
	 */
	@Override
	public void remove() {
		if (someSelected()) {
			SongRemovedLibraryEvent evt = new SongRemovedLibraryEvent(super.getSelected(), this);
			emitEvent(evt);
			super.remove();
		}
	}

	/**
	 * makes certein changes based on the event that hapened
	 * 
	 * @param evt event that hapened
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
	 * Emits a given event to the listeners
	 * 
	 * @param e event that occurred
	 */
	@Override
	public void emitEvent(SongLibraryEvent e) {
		for (Listener<SongLibraryEvent> o : listeners) {
			o.processEvent(e);
		}
	}

	/**
	 * Registers a new listener
	 * 
	 * @param obs listener to be added
	 */
	@Override
	public void registerListener(Listener<SongLibraryEvent> obs) {
		listeners.add(obs);

	}

	/**
	 * Removes the registry of the given listener
	 * 
	 * @param obs listener to be removed
	 */
	@Override
	public void unregisterListener(Listener<SongLibraryEvent> obs) {
		listeners.remove(obs);

	}

	/**
	 * list creation
	 * 
	 * @return list
	 */
	@Override
	public List<Song> createList() {
		return new ArrayList<>();
	}

	/**
	 * gives a string based on the library
	 * 
	 * @return string based on the library
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("*****MUSIC LIBRARY****");
		str.append("\n");
		str.append(super.toString());
		return str.toString();
	}

}

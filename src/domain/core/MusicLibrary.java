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

public class MusicLibrary extends AbsQListWithSelection<Song> implements Subject<SongLibraryEvent>, PropertyChangeListener {

    private Song songPlaying;
    private Player player = PlayerFactory.INSTANCE.getPlayer();
    private List<Listener<SongLibraryEvent>> listeners = new ArrayList<>();
    
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
        // Require of getSelected() is not necessary because it is the same as require of the function
        player.load(getSelected().getFilename());
        player.play();
    }

    /**
     * indicates whether a song that was played via the library is playing
     * 
     * @return True if a song is playing and False otherwise
     */
    public boolean isPlaying() {
    	return songPlaying!=null;
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
     * moves the rating of the selected song to the value immediately above the one it 
     * currently has (or stays the same if this value is already the maximum)
     */
    public void incRateSelected() {
    	if (someSelected()) {
    		getSelected().incRating();
    	}
    }
    
    /**
     * moves the rating of the selected song to the value immediately 
     * below the one it currently has (or stays the same if this value is already the minimum)
     */
    public void decRateSelected() {
    	if (someSelected()) {
    		super.getSelected().decRating();
    	}
    }

    /**
     * returns an iterable structure with the songs in 
     * the library that match the given regular expression
     * 
     * @param reexp regular expression
     * @return iterable structure with the library songs that match the given regular expression
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
    
 //--------------------------------------------------------------------------------------

	@Override
	public void add(ISong song) {

	}
	
	@Override
	public void remove() {

	}
    
    
	@Override
	public void rate() {

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
//--------------------------------------------------------------------------------------

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
}

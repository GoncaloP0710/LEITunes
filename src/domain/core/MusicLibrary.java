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

    public void play() {
        if (isPlaying()) {
        	stop();
        }
        player.load(getSelected().getFilename());
        player.play();
    }

    public boolean isPlaying() {
    	return songPlaying!=null;
    }

    public void stop() {
    	songPlaying = null;
    	player.stop();    	
    }

    public void incRateSelected() {
    	if (someSelected()) {
    		getSelected().incRating();
    
    	}
    }
    
    public void decRateSelected() {
    	if (someSelected()) {
    		super.getSelected().decRating();
    	}
    }

    public Iterable<ISong> getMatches(String reexp) {
    	ArrayList<ISong> list = new ArrayList<ISong>();
    	for (int i = 0; i < super.objList.size(); i++) {
    		if (super.objList.get(i).matches(reexp)) {
    			list.add(super.objList.get(i));
    		}
    	}
    	return list;
    }

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

	@Override
	public void emitEvent(SongLibraryEvent e) {
		for (Listener<SongLibraryEvent> o : listeners) {
			o.processEvent(e);
		}
	}

	@Override
	public void registerListener(Listener<SongLibraryEvent> obs) {
		listeners.add(obs);
		
	}

	@Override
	public void unregisterListener(Listener<SongLibraryEvent> obs) {
		listeners.remove(obs);
		
	}


	@Override
	public List<Song> createList() {
		return new ArrayList<>();
	}

}

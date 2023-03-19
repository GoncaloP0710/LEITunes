package domain.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import domain.facade.ISong;
import util.adts.AbsQListWithSelection;
import util.adts.ArrayQListWithSelection;
import util.adts.QListWithSelection;
import util.observer.Listener;
import util.observer.Subject;

public class MusicLibrary extends AbsQListWithSelection<Song> implements QListWithSelection<Song>, Subject<SongLibraryEvent>, PropertyChangeListener {

    private Song songPlaying;

    public MusicLibrary(ArrayList<Song> lista) {
    	super(lista);
        songPlaying = null;
    }

    public void play() {
    	if(isPlaying()) {
    		stop();
    	}
    	songPlaying = super.getSelected();
    	super.getSelected().incTimesPlayed();
    }

    public boolean isPlaying() {
    	return songPlaying!=null;
    }

    public void stop() {
    	songPlaying = null;
    }

    public void incRateSelected() {
    	super.getSelected().incRating();
    }

    public void decRateSelected() {
    	super.getSelected().decRating();
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
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void emitEvent(SongLibraryEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerListener(Listener<SongLibraryEvent> obs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterListener(Listener<SongLibraryEvent> obs) {
		// TODO Auto-generated method stub
		
	}
//--------------------------------------------------------------------------------------

}

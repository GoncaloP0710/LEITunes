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
    	
    }

    public Iterable<ISong> getSongs() {
    	
    }
    
}

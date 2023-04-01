package domain.playlists;

import domain.core.MusicLibrary;
import domain.facade.ISong;

public abstract class SmartPlaylist extends AbsPlaylist{

	/**
	 * skeleton implementation of play lists that do not allow addition
	 * and manually removing songs or changing position
	 * 
	 * @param name name of the play list
	 * @param library library of the play list
	 */
	public SmartPlaylist(String name, MusicLibrary library) {
		super(name,library);
	}
	
	/**
	 * automatic inserts that are made in a smart play list
	 * 
	 * @param song song to be added
	 */
	protected void addAutomatic(ISong song) {
		this.add(song);
	}
	
	/**
	 *  automatic removals that are made in a smart play list
	 * 
	 * @param index index of the song to be removed
	 */
	protected void removeAutomatic(int index) {
		int indexSelect = this.getIndexSelected() < index ? this.getIndexSelected() : this.getIndexSelected() - 1;
		this.select(index);
		this.remove();
		this.select(indexSelect);
	}
}

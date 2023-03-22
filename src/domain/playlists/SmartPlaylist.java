package domain.playlists;

import domain.core.MusicLibrary;
import domain.facade.ISong;

public abstract class SmartPlaylist extends AbsPlaylist{

	public SmartPlaylist(String name, MusicLibrary library) {
		super(name,library);
	}
	
	protected void addAutomatic(ISong song) {
		
	}
	
	protected void removeAutomatic(int index) {
		this.select(index);
		this.remove();
	}
	
	
}

package domain.playlists;

import domain.core.MusicLibrary;

public abstract class SmartPlaylist extends AbsPlaylist{

	public SmartPlaylist(String name, MusicLibrary library) {
		super(name,library);
	}
	
	public protected void addAutomatic(ISong song) {
		
	}
	
	public protected void removeAutomatic(int index) {
		
	}
}

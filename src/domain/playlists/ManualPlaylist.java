package domain.playlists;

import domain.core.MusicLibrary;

/**
 * Represents a playlist where certain operations must be made manualy
 */
public class ManualPlaylist extends AbsPlaylist {

	/**
	 * creates a playlist
	 * 
	 * @param name    name of the playlist
	 * @param library library of the playlist
	 */
	public ManualPlaylist(String name, MusicLibrary library) {
		super(name, library);
	}
}

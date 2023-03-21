package domain.playlists;

import java.util.Iterator;

import domain.core.MusicLibrary;
import domain.facade.ISong;

public class MostLikedSongsPlaylist extends SmartPlaylist{
	
	private SmartPlaylist playlistSmart;

	public MostLikedSongsPlaylist(MusicLibrary library) {
		super("s", library);
	}

}

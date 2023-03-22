package domain.facade;

import java.util.Iterator;

import domain.core.MusicLibrary;
import domain.playlists.ManualPlaylist;
import domain.playlists.Playlist;
import domain.playlists.PlaylistList;

public class PlaylistListController {

	private PlaylistList playlists;
	private MusicLibrary library;
	
	public PlaylistListController(PlaylistList playlists, MusicLibrary library) {
		this.playlists = playlists;
		this.library = library;
	}
	
	public void createPlaylist(String name) {
		ManualPlaylist playlist = new ManualPlaylist(name, library);
		playlists.add(playlist);
	}
	
	public void selectPlaylist(int i) {
		playlists.select(i);
	}
	
	public boolean somePlaylistSelected() {
		return playlists.someSelected();
	}
	
	public Playlist getSelectedPlaylist() {
		return playlists.getSelected();
	}
	
	public void removePlaylist() {
		playlists.remove();
	}
	
	public Iterator<Playlist> iterator() {
		return playlists.iterator();
	}
	
	public int numberOfSongs() {
		return playlists.getSelected().size();
	}
	
	public void addSong() {
		
	}
}

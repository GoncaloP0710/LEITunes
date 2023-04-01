package domain.facade;

import domain.core.MusicLibrary;
import domain.playlists.PlaylistList;

public class LEITunes {

	private MusicLibrary library;
	private PlaylistList playlist;
	private MusicLibraryController libraryController;
	private PlaylistListController playlistControler;
	
	public LEITunes (MusicLibrary library, PlaylistList playlist, MusicLibraryController libraryController, PlaylistListController playlistControler) {
		this.library = library;
		this.playlist = playlist;
		this.libraryController = libraryController;
		this.playlistControler = playlistControler;
	}
	
	public MusicLibrary getMusicLibrary () {
		return library;
	}
	
	public PlaylistList getPlaylistList () {
		return playlist;
	}
	
	public MusicLibraryController getMusicLibraryController () {
		return libraryController;
	}
	
	public PlaylistListController getPlaylistListController () {
		return playlistControler;
	}
	
}

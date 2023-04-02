package domain.facade;

import domain.core.MusicLibrary;
import domain.playlists.PlaylistList;

/**
 * provides the initial system object containing a MusicLibrary and a
 * PlaylistList.
 * Additionally, it contains a MusicLibraryController and a
 * PlaylistListController
 */
public class LEITunes {

	private MusicLibrary library;
	private PlaylistList playlist;
	private MusicLibraryController libraryController;
	private PlaylistListController playlistControler;

	/**
	 * Builds a LEITunes
	 */
	public LEITunes() {
		this.library = new MusicLibrary();
		this.playlist = new PlaylistList(library);
		this.libraryController = new MusicLibraryController(library);
		this.playlistControler = new PlaylistListController(playlist, library);
	}

	/**
	 * @return library
	 */
	public MusicLibrary getMusicLibrary() {
		return library;
	}

	/**
	 * @return playlist
	 */
	public PlaylistList getPlaylistList() {
		return playlist;
	}

	/**
	 * @return libraryController
	 */
	public MusicLibraryController getMusicLibraryController() {
		return libraryController;
	}

	/**
	 * @return playlistControler
	 */
	public PlaylistListController getPlaylistController() {
		return playlistControler;
	}

}

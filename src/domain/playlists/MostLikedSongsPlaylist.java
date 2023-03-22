package domain.playlists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import domain.core.MusicLibrary;
import domain.core.Rate;
import domain.core.SongLibraryEvent;
import domain.facade.ISong;

public class MostLikedSongsPlaylist extends SmartPlaylist{
	
	protected final int maxNumSongs;
	private int numSongs;
	private Rate lowestRate;
	private int lowestRateIndex;

	public MostLikedSongsPlaylist(MusicLibrary library) {
		super("MostLikedSongsPlaylist", library);
		this.maxNumSongs = 10;
		numSongs = 0;
		lowestRate = null;
		
	}
	
	/**
	 * Reaction to events, namely those emitted by the music library that 
	 * backs up this playlist (can affect the content of the playlist)
	 */
	@Override
	public void processEvent(SongLibraryEvent event) {
		Rate rating = event.getSong().getRating();
		if (rating != null) {
			//Verificar se a musica nao foi apagada da library
			ArrayList<ISong> libraryListSongs = (ArrayList<ISong>) event.getLibrary().getSongs();
			if (libraryListSongs.contains(event.getSong())) {
				//Verificar se a musica pode entrar na lista
				if (numSongs == maxNumSongs) {
					
					this.remove();
					numSongs--;
				}
				this.add(event.getSong());
				numSongs++;
			}
			
		}
	}
		
	
}

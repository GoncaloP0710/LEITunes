package domain.playlists;

import domain.core.MusicLibrary;
import domain.facade.ISong;

public abstract class SmartPlaylist extends AbsPlaylist{

	public SmartPlaylist(String name, MusicLibrary library) {
		super(name,library);
	}
	
	protected void addAutomatic(ISong song) {
		this.add(song);
	}
	
	protected void removeAutomatic(int index) {
		//Tenho de voltar a selecionar a musica que estava selecionada
		this.select(index);
		this.remove();
	}
}

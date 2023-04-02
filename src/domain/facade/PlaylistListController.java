package domain.facade;

import java.util.Iterator;

import domain.core.MusicLibrary;
import domain.playlists.ManualPlaylist;
import domain.playlists.Playlist;
import domain.playlists.PlaylistList;

public class PlaylistListController {

    private PlaylistList pl;
    private MusicLibrary lib;

    /**
     * Cria um objeto PlaylistListController
     * 
     * @param playlists lista de playlists
     * @param library   library com que as playlists vao ser criadas
     */
    public PlaylistListController(PlaylistList playlists, MusicLibrary library) {
        this.pl = playlists;
        this.lib = library;
    }

    /**
     * Cria uma nova playlist e adiciona a mesma a lista
     * 
     * @param name nome da playlist nova
     */
    public void createPlaylist(String name) {
        ManualPlaylist playlist = new ManualPlaylist(name, lib);
        pl.add(playlist);
    }

    /**
     * Seleciona uma playlist da lista
     * 
     * @param i indice da playlist a selecionar
     */
    public void selectPlaylist(int i) {
        if (i >= 0 && i < pl.size()) {
            pl.select(i);
        }
    }

    /**
     * Verifica se ha uma playlist selecionada
     * 
     * @return true caso haja uma playlist selecionada, false caso contrario
     */
    public boolean somePlaylistSelected() {
        return pl.someSelected();
    }

    /**
     * Devolve a playlist selecionada
     * 
     * @requires somePlaylistSelected()
     * @return playlist selecionada
     */
    public Playlist getSelectedPlaylist() {
        return pl.getSelected();
    }

    /**
     * Remove a playlist selecionada se esta existir, nao faz nada caso contrario
     */
    public void removePlaylist() {
        if (somePlaylistSelected()) {
            pl.remove();
        }
    }

    /**
     * Devolve um iterador com as playlists da lista
     * 
     * @return iterador com as playlists da lista
     */
    public Iterator<Playlist> iterator() {
        return pl.iterator();
    }

    /**
     * Devolve o numero de musicas da playlist selecionada
     * 
     * @return numero de musicas da playlist selecionada
     * @requires somePlaylistSelected()
     */
    public int numberOfSongs() {
        return pl.getSelected().size();
    }

    /**
     * Adiciona a musica selecionada a library da playlist selecionada,se nao
     * houver uma musica selecionada nao faz nada
     * 
     * @requires somePlaylistSelected()
     */
    public void addSong() {
        if (lib.someSelected()) {
            this.pl.getSelected().add(lib.getSelected());
        }
    }

    /**
     * Seleciona uma musica na playlist selecionada
     * 
     * @param i
     * @requires somePlaylistSelected()
     */
    public void selectSong(int i) {
        if (i >= 0 && i < numberOfSongs()) {
            pl.getSelected().select(i);

        }
    }

    /**
     * Verifica se ha alguma playlist selecionada e se houver, verifica tambem se
     * ha alguma musica selecionada nessa playlist
     * 
     * @return true caso passe ambas as verificacoes, false caso contrario
     */
    public boolean someSongSelected() {
        if (somePlaylistSelected()) {
            return getSelectedPlaylist().someSelected();
        }
        return false;
    }

    /**
     * Remove a musica selecionada da playlist selecionada
     * 
     * @requires someSongSelected()
     */
    public void removeSelectedSong() {
        pl.getSelected().remove();
    }

    /**
     * Manda um pedido de selecao da proxima musica para a playlist selecionada
     * 
     * @requires somePlaylistSelected()
     */
    public void nextSong() {
        this.pl.getSelected().next();
    }

    /**
     * Manda um pedido de selecao da musica anterior para a playlist selecionada
     * 
     * @requires somePlaylistSelected()
     */
    public void previousSong() {
        this.pl.getSelected().previous();
    }

    /**
     * Determina a interrupcao de uma musica se for o caso de que estava uma a
     * tocar e comeca a tocar, por ordem, todas as musicas da playlist selecionada
     * a partir da musica selecionada
     */
    public void play() {
        if (someSongSelected()) {
            if (pl.getSelected().isPlaying()) {
                pl.getSelected().stop();
            }
            pl.getSelected().play();
        }
    }

    /**
     * Para a musica que estava a ser tocada se esta foi posta a tocar por uma
     * playlist, caso contrario nao faz nada
     */
    public void stop() {
        if (pl.isPlaying()) {
            pl.stop();
        }
    }

    /**
     * retorna a string que representa as playlists
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("***** PLAYLISTS *****").append("\n");
        for (int i = 0; i < pl.size(); i++) {
            str.append(pl.get(i).toString()).append("\n");
        }
        return str.toString();
    }
}

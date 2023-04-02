package domain.playlists;

import java.util.ArrayList;
import java.util.List;

import domain.core.MusicLibrary;
import util.adts.AbsQListWithSelection;
import util.adts.QListWithSelection;

/**
 * represents a list of playlists
 */
public class PlaylistList extends AbsQListWithSelection<Playlist> implements QListWithSelection<Playlist> {

    private MusicLibrary lib;

    /**
     * Builds a list of playlists
     * 
     * @param library of the playlists
     */
    public PlaylistList(MusicLibrary library) {
        super();
        this.lib = library;
        this.add(new MostLikedSongsPlaylist(library));
        this.add(new MostRecentlyAddedSongsPlaylist(library));
    }

    /**
     * adds a playlist to the list and selects it
     */
    @Override
    public void add(Playlist playlist) {
        super.add(playlist);
        this.lib.registerListener(playlist);
    }

    /**
     * removes the selected playlist if any is selected
     */
    @Override
    public void remove() {
        this.lib.unregisterListener(getSelected());
        super.remove();
    }

    /**
     * Determines the interruption of the currently playing song, if that's the
     * case, and starts playing all the songs in the list from the selected song in
     * order
     * 
     * @requires this.someSelected()
     */
    public void play() {
        if (getSelected().someSelected()) {
            if (isPlaying()) {
                stop();
            }
            getSelected().play();
        }
    }

    /**
     * Indicates whether a song is being played in any of the playlists in the list
     * 
     * @return true if a song is being played and false otherwise
     */
    public boolean isPlaying() {
        int indexSelect = getIndexSelected();
        for (int i = 0; i < size(); i++) {
            this.select(i);
            if (this.getSelected().isPlaying()) {
                select(indexSelect);
                return true;
            }
        }
        select(indexSelect);
        return false;
    }

    /**
     * Stops the music that was playing
     * 
     * @requires isPlaying()
     */
    public void stop() {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).someSelected()) {
                this.get(i).stop();
            }
        }
    }

    /**
     * Returns the number of playlists in the list
     * 
     * @return number of playlists in the list
     */
    @Override
    public int size() {
        return super.size();
    }

    /**
     * 
     * @return ArrayList<Playlist>
     */
    @Override
    public List<Playlist> createList() {
        return new ArrayList<Playlist>();
    }

    /**
     * builds a string that represents a list of playlists
     * 
     * @return string that represents a list of playlists
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < super.size(); i++) {
            str.append("*-- Playlist ").append(super.get(i).getName()).append("--*");
            super.get(i).toString();
            str.append("\n");
        }
        return str.toString();
    }
}

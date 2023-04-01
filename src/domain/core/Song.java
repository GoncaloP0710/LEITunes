package domain.core;

import java.util.List;
import domain.facade.ISong;
import util.adts.RegExpMatchable;

public class Song implements ISong, RegExpMatchable {

    private String songFileName;
    private SongMetaInfo songInfo;
    private Rate rate;
    private int timesPlayed;

    /**
     * Creates a song
     * 
     * @param info information about the song
     * @param fileName name of the file of the mp3
     */
    public Song(SongMetaInfo info, String fileName) {
        songFileName = fileName;
        songInfo = info;
        timesPlayed = 0;
    }

    /**
	 * Increments the number of times the song was played
	 */
    @Override
    public void incTimesPlayed() {
        timesPlayed++;
    }

    /**
	 * Returns the number of times the song was played
	 * 
	 * @return number of times the song was played
	 */
    @Override
    public int getTimesPlayed() {
        return timesPlayed;
    }

    /**
	 * Returns the rating of the song
	 * 
	 * @return the song's rating
	 * @ensures \result != null
	 */
    @Override
    public Rate getRating() {
        return rate;
    }

    /**
	 * Increments the song's rating
	 * @ensures getRating().equals(\old(getRating().inc())
	 */
    @Override
    public void incRating() {
    	rate = rate.incRating();
    }

    /**
	 * Decrements the song's rating
	 * @ensures getRating().equals(\old(getRating().dec())
	 */
    @Override
    public void decRating() {
    	rate = rate.decRating();
    }

    /**
	 * Returns the title of the song
	 * 
	 * @return the song's title
	 * @ensures \result != null
	 */
    @Override
    public String getSongTitle() {
        return songInfo.songTitle();
    }

    /**
	 * Returns the genre of the song
	 * 
	 * @return the song's genre
	 * @ensures \result != null
	 */
    @Override
    public String getGenre() {
        return songInfo.songGenre();
    }

    /**
	 * Returns the artist list of the song
	 * 
	 * @return the song's artists list
	 * @ensures \result != null
	 */
    @Override
    public List<String> getArtists() {
        return songInfo.songArtists();
    }

    /**
	 * Returns the album name of the song
	 * 
	 * @return the song's album name
	 * @ensures \result != null
	 */
    @Override
    public String getAlbum() {
        return songInfo.songAlbumTitle();
    }

    /**
	 * Return the filename  of the song
	 * 
	 * @return the song's filename
	 * @ensures \result != null 
	 */
    @Override
    public String getFilename() {
        return songFileName;
    }

    /**
	 * Checks if any song data matches the given regular expression
	 *  
	 * @param regexp the regular expression to be used
	 * @requires regexp != null
	 * @return whether some data of the song matches with the given regexp
	 */
    @Override
    public boolean matches(String regexp) {
        return songInfo.matches(regexp);
    }
    
    /**
     * 
     */
    public String toString() {
    	StringBuilder str = new StringBuilder();
    	str.append("[").append(getSongTitle()).append(", ");
    	str.append(getAlbum()).append(", ");
    	str.append(getGenre()).append(", ");
    	str.append(getArtists()).append("] --- ");
    	//O que representam os numeros?
    	return str.toString();
    }
}

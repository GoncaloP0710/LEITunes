package domain.core;

import java.util.List;
import domain.facade.ISong;
import util.adts.RegExpMatchable;

public class Song implements ISong, RegExpMatchable {

    private String songFileName;
    private SongMetaInfo songInfo;
    private Rate rate;
    private int timesPlayed;

    public Song(SongMetaInfo info, String fileName) {
        songFileName = fileName;
        songInfo = info;
        timesPlayed = 0;
    }

    @Override
    public void incTimesPlayed() {
        timesPlayed++;
    }

    @Override
    public int getTimesPlayed() {
        return timesPlayed;
    }

    @Override
    public Rate getRating() {
        return rate;
    }

    @Override
    public void incRating() {
    	rate = rate.incRating(rate);
    }

    @Override
    public void decRating() {
    	rate = rate.decRating(rate);
    }

    @Override
    public String getSongTitle() {
        return songInfo.songTitle();
    }

    @Override
    public String getGenre() {
        return songInfo.songGenre();
    }

    @Override
    public List<String> getArtists() {
        return songInfo.songArtists();
    }

    @Override
    public String getAlbum() {
        return songInfo.songAlbumTitle();
    }

    @Override
    public String getFilename() {
        return songFileName;
    }

    @Override
    public boolean matches(String regexp) {
        return songInfo.matches(regexp);
    }
}

package domain.core;

import util.adts.RegExpMatchable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * represent some meta information of the songs like the title of the
 * song, genre, a list with the name of one or more artists and the name of the
 * album. Implements
 * RegExpMatchable considering that some information available (the name, some
 * of the artists' names,
 * the genre or album name) over the object adheres to the given regexp
 *
 */
public record SongMetaInfo(String songTitle, String songGenre, List<String> songArtists, String songAlbumTitle)
        implements RegExpMatchable {

    /**
     * @param regexp a regular expression used to check the matches
     * @requires regexp != null
     * @return true if this object matches regexp, false otherwise.
     */
    @Override
    public boolean matches(String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matchersongTitle = pattern.matcher(songTitle);
        Matcher matchersongGenre = pattern.matcher(songGenre);
        Matcher matchersongAlbumTitle = pattern.matcher(songAlbumTitle);
        boolean artist = false;
        for (String s : songArtists) {
            artist = artist || pattern.matcher(s).matches();
        }
        return matchersongTitle.matches() || matchersongGenre.matches() || matchersongAlbumTitle.matches() || artist;
    }
}

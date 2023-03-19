package domain.core;

import util.adts.RegExpMatchable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record SongMetaInfo(String songTitle, String songGenre, List<String> songArtists, String songAlbumTitle)
        implements RegExpMatchable {

    @Override
    public boolean matches(String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matchersongTitle = pattern.matcher(songTitle);
        Matcher matchersongGenre = pattern.matcher(songGenre);
        Matcher matchersongAlbumTitle = pattern.matcher(songAlbumTitle);
        boolean artist = false;
        for (String s: songArtists) {
        	artist = artist || pattern.matcher(s).matches();
        }
        return matchersongTitle.matches() || matchersongGenre.matches() || matchersongAlbumTitle.matches() || artist;
    }
}

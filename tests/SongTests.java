import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import domain.core.Rate;
import domain.core.Song;
import domain.core.SongMetaInfo;

public class SongTests {

    String name = "musicadosinformaticos.mp3";
    String title = "Musica dos Informaticos";
    String genre = "Fado";
    List<String> artists = List.of("Rodrigo Leao");
    String album = "LEI";
    String rate = "MID";
    String timesPlayed = "0";

    SongMetaInfo meta;

    Song musica;

    @BeforeEach
    void setUp() {
        meta = new SongMetaInfo(title, genre, artists, album);
        musica = new Song(meta, name);
    }

    @Test
    public void timesPlayedTest() {
        assertTrue(musica.getTimesPlayed() == 0);
        musica.incTimesPlayed();
        assertTrue(musica.getTimesPlayed() == 1);
    }

    @Test
    public void rateTest() {
        assertTrue(musica.getRating().equals(Rate.MID));
        musica.incRating();
        assertTrue(musica.getRating().equals(Rate.HIGH));
        musica.decRating();
        assertTrue(musica.getRating().equals(Rate.MID));
    }

    @Test
    public void getTitleTest() {
        assertEquals(title, musica.getSongTitle());
    }

    @Test
    public void getGenreTest() {
        assertEquals(genre, musica.getGenre());
    }

    @Test
    public void getArtistsTest() {
        assertEquals(artists, musica.getArtists());
    }

    @Test
    public void getAlbumTest() {
        assertEquals(album, musica.getAlbum());
    }

    @Test
    public void nameTest() {
        assertEquals(musica.getFilename(), name);
    }

    @Test
    public void toStringTest() {
        String compare = "[" + title + ", " + album + ", " + genre + ", " + artists.toString() + "] --- " + rate
                + " -- " + timesPlayed;
        assertEquals(musica.toString(), compare);
    }

    @Test
    public void equalTest1() {
        Song compare = new Song(meta, name);
        assertEquals(compare, musica);
    }

    @Test
    public void equalTest2() {
        Song compare = new Song(new SongMetaInfo("outro", genre, artists, album), name);
        assertFalse(musica.equals(compare));
    }

    @Test
    public void equalTest3() {
        Song compare = new Song(new SongMetaInfo(title, "outro", artists, album), name);
        assertFalse(musica.equals(compare));
    }

    @Test
    public void equalTest4() {
        Song compare = new Song(new SongMetaInfo(title, genre, null, album), name);
        assertFalse(musica.equals(compare));
    }

    @Test
    public void equalTest5() {
        Song compare = new Song(new SongMetaInfo(title, genre, artists, "outro"), name);
        assertFalse(musica.equals(compare));
    }

    @Test
    public void equalTest6() {
        Song compare = new Song(new SongMetaInfo(title, genre, artists, album), "outro.mp3");
        assertFalse(musica.equals(compare));
    }

    @Test
    public void equalTest7() {
        Song compare = new Song(meta, name);
        compare.incTimesPlayed();
        assertFalse(musica.equals(compare));
    }

    @Test
    public void equalTest8() {
        Song compare = new Song(meta, name);
        compare.incRating();
        assertFalse(musica.equals(compare));
    }
}

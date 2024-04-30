package chapter4.ex1;

import chapter1.common.Artist;

import java.util.List;
import java.util.Optional;

public class Artists {
    private List<Artist> artists;

    public Artists(List<Artist> artists) {
        this.artists = artists;
    }

    public Optional<Artist> getArtist(int index) {
        if (index < 0 || index >= artists.size()) {
            Optional.empty();
        }
        return Optional.ofNullable(artists.get(index));
    }

    private void indexException(int index) {
        throw new IllegalArgumentException(index + " does not correspond to an Artist");
    }

    public String getArtistName(int index) {
        return getArtist(index).map(Artist::getName).orElse("unknown");
    }

}

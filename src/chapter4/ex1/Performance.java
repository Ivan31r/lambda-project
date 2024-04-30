package chapter4.ex1;

import chapter1.common.Artist;

import java.util.stream.Stream;

import static java.util.stream.Stream.*;
import static java.util.stream.Stream.concat;

public interface Performance {
    public String getName();

    public Stream<Artist> getMusicians();

    default Stream<Artist> getAllMusicians(){
        return getMusicians().flatMap(artist -> concat(of(artist),artist.getMembers()));
    }
}

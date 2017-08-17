package me.gladwell.cloudscore;

import java.util.ArrayList;
import static java.util.Collections.unmodifiableList;

import java.time.LocalDate;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 * Implementation of the Gallery interface using immutability to ensure thread-safety.
 * @author ricardo
 */
public class ThreadSafeGallery implements Gallery {

    private final List<Art> gallery;

    public ThreadSafeGallery() {
        this(new ArrayList<>());
    }

    private ThreadSafeGallery(List<Art> gallery) {
        this.gallery = unmodifiableList(gallery);
    }

    @Override
    public Gallery addArt(Art art) {
        List<Art> newGallery = new ArrayList<Art>(gallery);
        newGallery.add(art);
        return new ThreadSafeGallery(newGallery);
    }

    @Override
    public Gallery deleteArt(Art art) {
        List<Art> newGallery = new ArrayList<Art>(gallery);
        newGallery.remove(art);
        return new ThreadSafeGallery(newGallery);
    }

    @Override
    public List<Art> getAllArt() {
        return gallery;
    }

    @Override
    public List<String> getArtists() {
        return gallery
                 .stream()
                 .map(art -> art.getArtist())
                 .sorted()
                 .collect(toList());
    }

    @Override
    public List<Art> getArtByArtist(String artist) {
        return gallery
                .stream()
                .filter(art -> art.getArtist().equals(artist))
                .collect(toList());
    }

    @Override
    public List<Art> getRecentArt() {
        LocalDate aYearAgo = LocalDate.now().minusYears(1);

        return gallery
                .stream()
                .filter(art -> art.getDateCreated().isAfter(aYearAgo))
                .collect(toList());
    }

    @Override
    public List<Art> getArtByPrice(Integer price) {

        return gallery
                .stream()
                .filter(art -> art.getPrice().map(p -> p.equals(price)).orElse(false))
                .collect(toList());
    }

}

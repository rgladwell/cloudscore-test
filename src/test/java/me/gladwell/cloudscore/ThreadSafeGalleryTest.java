package me.gladwell.cloudscore;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Lists;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Optional;

public class ThreadSafeGalleryTest {

    private Gallery gallery = new ThreadSafeGallery();

    @Test
    public void testAddArt() {
        Art art = new Art("name", ArtType.PAINTING, Optional.of(1), "John Smith", LocalDate.now());
        assertTrue(gallery.addArt(art).getAllArt().contains(art));
    }

    @Test
    public void testDeleteArt() {
        Art art = new Art("name", ArtType.PAINTING, Optional.of(1), "John Smith", LocalDate.now());
        assertTrue(gallery.addArt(art).deleteArt(art).getAllArt().isEmpty());
    }

    @Test
    public void testDeleteArtDoesNotDeleteUnknownArtist() {
        Art art1 = new Art("name1", ArtType.PAINTING, Optional.of(1), "John Smith", LocalDate.now());
        Art art2 = new Art("name2", ArtType.PAINTING, Optional.of(1), "Adam Smith", LocalDate.now());

        assertTrue(gallery.addArt(art1).deleteArt(art2).getAllArt().contains(art1));
    }

    @Test
    public void testGetArtists() {
        Art art = new Art("name", ArtType.PAINTING, Optional.of(1), "John Smith", LocalDate.now());

        assertTrue(gallery.addArt(art).getArtists().contains("John Smith"));
    }

    @Test
    public void testGetArtistsReturnsAlphabeticalOrder() {
        Art art1 = new Art("name1", ArtType.PAINTING, Optional.of(1), "John Smith", LocalDate.now());
        Art art2 = new Art("name2", ArtType.VASE, Optional.of(2), "Adam Smith", LocalDate.now());

        assertIterableEquals(gallery.addArt(art1).addArt(art2).getArtists(), Lists.newArrayList("Adam Smith", "John Smith"));
    }

    @Test
    public void testGetArtByArtist() {
        Art art = new Art("name1", ArtType.PAINTING, Optional.of(1), "John Smith", LocalDate.now());

        assertTrue(gallery.addArt(art).getArtByArtist("John Smith").contains(art));
    }

    @Test
    public void testGetArtByArtistDoesNotReturnArtByOtherArtists() {
        Art art1 = new Art("name1", ArtType.PAINTING, Optional.of(1), "John Smith", LocalDate.now());
        Art art2 = new Art("name2", ArtType.VASE, Optional.of(2), "Adam Smith", LocalDate.now());

        assertFalse(gallery.addArt(art1).addArt(art2).getArtByArtist("John Smith").contains(art2));
    }

    @Test
    public void testGetRecentArt() {
        Art art = new Art("name1", ArtType.PAINTING, Optional.of(1), "John Smith", LocalDate.now());

        assertTrue(gallery.addArt(art).getRecentArt().contains(art));
    }

    @Test
    public void testGetRecentArtDoesNotReturnOldArt() {
        Art art = new Art("name2", ArtType.VASE, Optional.of(2), "Adam Smith", LocalDate.now().minusYears(1));

        assertTrue(gallery.addArt(art).getRecentArt().isEmpty());
    }

    @Test
    public void testGetArtByPrice() {
        Art art = new Art("name1", ArtType.PAINTING, Optional.of(1), "John Smith", LocalDate.now());

        assertTrue(gallery.addArt(art).getArtByPrice(1).contains(art));
    }

    @Test
    public void testGetArtByPriceExcludesOtherPrices() {
        Art art = new Art("name1", ArtType.PAINTING, Optional.of(1), "John Smith", LocalDate.now());

        assertTrue(gallery.addArt(art).getArtByPrice(2).isEmpty());
    }

    @Test
    public void testGetArtByPriceExcludesEmptyPrices() {
        Art art = new Art("name1", ArtType.PAINTING, Optional.empty(), "John Smith", LocalDate.now());

        assertTrue(gallery.addArt(art).getArtByPrice(2).isEmpty());
    }

}

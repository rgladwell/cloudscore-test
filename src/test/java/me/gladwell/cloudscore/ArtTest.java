package me.gladwell.cloudscore;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Optional;

public class ArtTest {

    @Test
    public void testEqualityOnNameTypeAndArtistOnly() {
        Art art1 = new Art("name", ArtType.PAINTING, Optional.of(1), "John Smith", LocalDate.now());
        Art art2 = new Art("name", ArtType.PAINTING, Optional.of(2), "John Smith", LocalDate.now());
        assertEquals( art1, art2 );
    }

    @Test
    public void testNameMandatory() {
        assertThrows(NullPointerException.class, () -> {
            new Art(null, ArtType.PAINTING, Optional.of(1), "John Smith", LocalDate.now());
        }); 
    }

    @Test
    public void testTypeMandatory() {
        assertThrows(NullPointerException.class, () -> {
            new Art("name", null, Optional.of(1), "John Smith", LocalDate.now());
        }); 
    }

    @Test
    public void testArtistMandatory() {
        assertThrows(NullPointerException.class, () -> {
            new Art("name", ArtType.PAINTING, Optional.of(1), null, LocalDate.now());
        }); 
    }

}

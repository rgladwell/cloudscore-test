package me.gladwell.cloudscore;

import java.time.LocalDate;
import java.util.Optional;
import static com.google.common.base.Preconditions.checkNotNull;

public final class Art {

    private final String name;
    private final ArtType type;
    private final Optional<Integer> price;
    private final String artist;
    private final LocalDate dateCreated;

    public Art(String name, ArtType type, Optional<Integer> price, String artist, LocalDate dateCreated) {
        super();

        checkNotNull(name);
        checkNotNull(type);
        checkNotNull(artist);

        this.name = name;
        this.type = type;
        this.price = price;
        this.artist = artist;
        this.dateCreated = dateCreated;
    }

    public String getName() {
        return name;
    }

    public ArtType getType() {
        return type;
    }

    public Optional<Integer> getPrice() {
        return price;
    }

    public String getArtist() {
        return artist;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    @Override
    public String toString() {
        return "Art(" + name + ", " + type + ", " + price + ", " + artist + ", " + dateCreated + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((artist == null) ? 0 : artist.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Art other = (Art) obj;
        if (artist == null) {
            if (other.artist != null)
                return false;
        } else if (!artist.equals(other.artist))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

}

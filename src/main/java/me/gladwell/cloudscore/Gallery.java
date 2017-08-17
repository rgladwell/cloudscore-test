package me.gladwell.cloudscore;

import java.util.List;

public interface Gallery {

    public Gallery addArt(Art art);
    public Gallery deleteArt(Art art);
    public List<Art> getAllArt();
    public List<String> getArtists();
    public List<Art> getArtByArtist(String artist);
    public List<Art> getRecentArt();
    public List<Art> getArtByPrice(Integer price);

}

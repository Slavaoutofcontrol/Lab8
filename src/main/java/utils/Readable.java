package utils;

import collectionClasses.Colour;
import collectionClasses.Country;
import collectionClasses.MovieGenre;
import collectionClasses.MpaaRating;

public interface Readable {
    String readName();
    Integer readCoordinateX();
    Float readCoordinateY();
    Integer readOscarCount();
    MovieGenre readMovieGenre();
    MpaaRating readMpaaRating();
    String readDirectorsName();
    Double readDirectorsWeight();
    Colour readHairColour();
    Country readNationality();
    String readLogin();
    String readPassword();
}

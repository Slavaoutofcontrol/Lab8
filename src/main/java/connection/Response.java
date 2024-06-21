package connection;

import collectionClasses.Colour;
import collectionClasses.Movie;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

public class Response implements Serializable {
    @Serial
    private static final long serialVersionUID = 537321791155276796L;
    private ResponseStatus responseStatus;
    private String response = "";
    private Collection<Movie> collection;
    public Response(){
    }
    public Response(ResponseStatus responseStatus, String response){
        this.responseStatus = responseStatus;
        this.response = response.trim() + "\n";
    }

    public Response(ResponseStatus responseStatus, HashSet<Movie> movies){
        this.responseStatus = responseStatus;
        this.response = response.trim() + "\n";
        this.collection = movies.stream()
                .sorted(Comparator.comparing(Movie::getName)).toList();
    }


    public Response(ResponseStatus status, String response, HashSet<Movie> collection) {
        this.responseStatus = status;
        this.response = response.trim();
        this.collection = collection.stream()
                .sorted(Comparator.comparing(Movie::getName))
                .collect(Collectors.toCollection(HashSet::new));
    }

    public Response(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public ResponseStatus getStatus() {
        return responseStatus;
    }
    public String getResponse() {
        return response;
    }
    public Collection<Movie> getCollection() {
        return collection;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Response response1)) return false;
        return responseStatus == response1.responseStatus && Objects.equals(response, response1.response) && Objects.equals(collection, response1.collection);
    }
    @Override
    public int hashCode() {
        return Objects.hash(responseStatus, response, collection);
    }
    @Override
    public String toString(){
        return "Response[" + responseStatus +
                (response.isEmpty()
                        ? ""
                        :',' + response) +
                (collection == null
                        ? ']'
                        : ',' + collection.toString() + ']');
    }
}

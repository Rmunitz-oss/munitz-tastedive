package munitz.tastedive;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Notes from API Documentation:
 *     q: the search query; consists of a series (at least one) of bands, movies, TV shows, podcasts, books, authors and/or games,
 *     separated by commas. Sometimes it is useful to specify the type of a certain resource in the query
 *     (e.g. if a movie and a book share the same title).
 *     You can do this by using the "band:", "movie:", "show:", "podcast:", "book:", "author:" or "game:" operators,
 *     for example: "band:underworld, movie:harry potter, book:trainspotting". Don't forget to encode this parameter.
 *
 *     type: query type, specifies the desired type of results.
 *     It can be one of the following: music, movies, shows, podcasts, books, authors, games.
 *     If not specified, the results can have mixed types.
 */
public interface TasteDiveService {
    @GET ("/api/similar?type=music&k=413205-SchoolPr-51ODUWYG")
    Single <SimilarMusicFeed> getSimilarMusic(
            @Query("q") String bandName
    );

}


package munitz.tastedive;
import java.util.List;

//variables are capitalized to match JSON response
public class SimilarMusicFeed {
    Similar Similar;
    static class Similar {
        List<Results> Results;

        static class Results {
            String Name;
        }
   }
}

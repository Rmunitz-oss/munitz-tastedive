package munitz.tastedive;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class TasteDiveServiceTest {
    TasteDiveServiceFactory factory = new TasteDiveServiceFactory();
    @Test
    public void getSimilarMusic(){
        //given
        TasteDiveService service = factory.newInstance();

        //when
        SimilarMusicFeed feed = service.getSimilarMusic("One Republic",
                "music",
                "413205-SchoolPr-51ODUWYG")
                .blockingGet();

        //then
        assertNotNull(feed);
        assertNotNull(feed.Similar);
        assertNotNull(feed.Similar.Results);
        assertFalse(feed.Similar.Results.isEmpty());
        assertNotNull(feed.Similar.Results.get(0).Name);
    }
}

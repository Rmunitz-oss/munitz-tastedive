package munitz.tastedive;

import io.reactivex.rxjava3.core.Single;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TasteDiveControllerTest {
    private TasteDiveController controller;
    private TasteDiveServiceFactory factory;
    private TasteDiveService service;
    private Label resultsLabel;
    private TextField bandNameTextField;
    private List<Label> labelsArray;
    private List<Text>similarArtistTextsArray;
    private SimilarMusicFeed feed;

    final String apiKey = "413205-SchoolPr-51ODUWYG";
    final String type = "music";

    @BeforeClass
    public static void beforeClass() {
        com.sun.javafx.application.PlatformImpl.startup(() -> {
        });

    }

    @Test
    public void initialize(){
        //given
        givenTasteDiveController();
        doReturn(service).when(factory).newInstance();

        ///when
        controller.initialize();

        //verify
        verify(factory).newInstance();
        assertEquals(service,factory.newInstance());
    }

    @Test
    public void clearResults(){
        //given
        givenTasteDiveController();

        //when
        controller.clearResults();

        //then
        verify(labelsArray.get(0)).setText("");
        verify(similarArtistTextsArray.get(0)).setText("");
        verify(resultsLabel).setText("");
    }

    @Test
    public void getSimilarMusic(){
        //given
        givenTasteDiveController();
        doReturn("Avicii").when(bandNameTextField).getText();
        doReturn(Single.never()).when(service).getSimilarMusic("Avicii",type,apiKey);

        //when
        controller.getSimilarMusic();

        //then
        verify(service).getSimilarMusic("Avicii",type,apiKey);
    }

    @Test
    public void onSimilarMusicFeedRunEmptyResults(){
        //given
        givenTasteDiveController();
        feed.Similar.Results = Arrays.asList();

        //when
        controller.onSimilarMusicFeedRun(feed);

        //verify
        verify(resultsLabel).setText("Sorry, there are no similar artists for this singer.");
    }

    @Test
    public void onSimilarMusicFeedRunNonEmptyResults(){
        //given
        givenTasteDiveController();
        feed.Similar.Results = Arrays.asList(mock(SimilarMusicFeed.Similar.Results.class));
        feed.Similar.Results.get(0).Name = "Imagine Dragons";

        //when
        controller.onSimilarMusicFeedRun(feed);

        //verify
        verify(labelsArray.get(0)).setText("1.  ");
        verify(similarArtistTextsArray.get(0)).setText("Imagine Dragons");
        verify(resultsLabel).setText("You may enjoy these similar artists.");
    }
    @Test
    public void onError(){
        //given
        givenTasteDiveController();

        //when
        Throwable throwable = mock(Throwable.class);
        controller.onError(throwable);

        //verify
        verify(resultsLabel).setText("Sorry,there was an error. Please try again.");
    }

    @Test
    public void givenTasteDiveController(){
        service = mock(TasteDiveService.class);
        controller = new TasteDiveController(service);
        factory = mock(TasteDiveServiceFactory.class);
        controller.factory = factory;
        resultsLabel = mock(Label.class);
        controller.resultsLabel = resultsLabel;
        bandNameTextField = mock(TextField.class);
        controller.bandNameTextField = bandNameTextField;
        labelsArray = Arrays.asList(mock(Label.class));
        controller.labelsArray = labelsArray;
        similarArtistTextsArray  = Arrays.asList(mock(Text.class));
        controller.similarArtistTextsArray = similarArtistTextsArray;
        feed = mock(SimilarMusicFeed.class);
        feed.Similar = mock(SimilarMusicFeed.Similar.class);
    }
}

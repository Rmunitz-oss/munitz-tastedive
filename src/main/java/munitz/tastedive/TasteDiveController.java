package munitz.tastedive;

import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.List;

public class TasteDiveController {
    @FXML
    Label resultsLabel;
    @FXML
    TextField bandNameTextField;
    @FXML
    List<Label>labelsArray;
    @FXML
    List<Text>similarArtistTextsArray;

    TasteDiveServiceFactory factory = new TasteDiveServiceFactory();
    TasteDiveService service;
    final String apiKey = "413205-SchoolPr-51ODUWYG";
    final String type = "music";
    String bandName;

    public TasteDiveController(TasteDiveService service){
        this.service = service;
    }

    @FXML
    public void initialize(){
        service = factory.newInstance();
    }

    /**
     * calls clearResults method
     * calls getSimilarMusic method
     * with user provided bandName
     */
    public void getSimilarMusic(){
        clearResults();
        bandName = bandNameTextField.getText();
        service.getSimilarMusic(bandName,type,apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.trampoline())
                .subscribe(this :: onSimilarMusicFeed, this :: onError);
    }

    /**
     * clears labels and text from previous search
     */
    public void clearResults() {
        for (Label label: labelsArray){
            label.setText("");
        }
        for (Text text : similarArtistTextsArray){
            text.setText("");
        }
        resultsLabel.setText("");
    }

    public void onSimilarMusicFeed(SimilarMusicFeed feed){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                onSimilarMusicFeedRun(feed);
            }
        }
        );
    }

    /**
     * if no search results, notifies user
     * else gets results from feed
     * and sets labels and text with similar artists found
     * @param feed
     */
    public void onSimilarMusicFeedRun(SimilarMusicFeed feed){
        if(feed.Similar.Results.isEmpty()){
            resultsLabel.setText("Sorry, there are no similar artists for this singer.");
        }
        else{
            for(int ix = 0; ix < labelsArray.size(); ix++ ){
                //labelsArray.get(ix).setText((ix+1) + ". " + feed.Similar.Results.get(ix).Name);
                labelsArray.get(ix).setText((ix+1) + ".  ");
                similarArtistTextsArray.get(ix).setText(feed.Similar.Results.get(ix).Name);
            }
            resultsLabel.setText("You may enjoy these similar artists.");
        }
    }

    /**
     * notifies user upon error
     * @param throwable
     */
    public void onError(Throwable throwable){
        resultsLabel.setText("Sorry,there was an error. Please try again.");
    }

}

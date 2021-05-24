package munitz.tastedive;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TasteDiveServiceFactory {
    public TasteDiveService newInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tastedive.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        TasteDiveService service = retrofit.create(TasteDiveService.class);
        return service;
    }
}

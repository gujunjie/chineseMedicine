package contract;

import bean.TuiJian;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface TuiJianClient {

        @GET("health.json")
        Observable<TuiJian> gethealthJson();

        @GET("medicineDiet.json")
        Observable<TuiJian> getmedicineDietJson();

        @GET("prescription.json")
        Observable<TuiJian> getprescriptionJson();

        @GET("encyclopedia.json")
        Observable<TuiJian> getEncyclopediaJson();
}

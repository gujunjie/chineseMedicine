package contract;

import bean.AccessToken;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface TokenClient {

    @GET("token?grant_type=client_credentials&client_id=xvyMNNP3uCLgiHWyyAkmZWlH&client_secret=WjqSRm740mbxYRChbYSnnYHGKLEuY57z")
    Observable<AccessToken> getAccessToken();
}

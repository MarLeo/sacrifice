package retrofit_basic.api;

import models.Observations;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FredApi {

    @GET("observations?")
    Call<Observations> getAllSource(@Query("series_id") String series_id,
                                    @Query("api_key") String key,
                                    @Query("file_type") String type);

}

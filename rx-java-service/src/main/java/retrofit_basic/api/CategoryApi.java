package retrofit_basic.api;

import models.Categories;
import models.Seriess;
import models.Tags;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface CategoryApi {


    @GET("category?")
    Call<Categories> getCategory(@Query("category_id") int category_id,
                                 @Query("api_key") String key,
                                 @Query("file_type") String type);


    @GET("children?")
    Call<Categories> getChildCategory(@Query("category_id") int category_id,
                                      @Query("realtime_start") String realtime_start,
                                      @Query("realtime_end") String realtime_end,
                                      @Query("api_key") String key,
                                      @Query("file_type") String type);


    @GET("related?")
    Call<Categories> getRelatedCategory(@Query("category_id") int category_id,
                                        @Query("realtime_start") String realtime_start,
                                        @Query("realtime_end") String realtime_end,
                                        @Query("api_key") String key,
                                        @Query("file_type") String type);

    @GET("series?")
    Call<Seriess> getSeriesCategory(@Query("category_id") int category_id,
                                    @Query("api_key") String key,
                                    @Query("file_type") String type,
                                    @QueryMap Map<String, String> options);

    @GET("tags?")
    Call<Tags> getTagsCategory(@Query("category_id") int category_id,
                               @Query("api_key") String key,
                               @Query("file_type") String type,
                               @QueryMap Map<String, String> options);

    @GET("related_tags?")
    Call<Tags> getRelatedTagsCategory(@Query("category_id") int category_id,
                                      @Query("api_key") String key,
                                      @Query("file_type") String type,
                                      @QueryMap Map<String, String> options);


}

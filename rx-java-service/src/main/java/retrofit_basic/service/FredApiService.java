package retrofit_basic.service;

import models.FileType;
import models.Observations;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit_basic.api.FredApi;
import utils.Utils;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class FredApiService {


    Properties properties = Utils.getProperties();
    String fredUrl = properties.getProperty("fredUrl");
    String apiKey = properties.getProperty("apiKey");
    String series_id = properties.getProperty("series_id");
    private FredApi api;

    public FredApiService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(fredUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(FredApi.class);
    }


    public Observations getAllSourceData() throws IOException {

        Optional<Observations> sources = Optional.ofNullable(api
                .getAllSource(series_id, apiKey, FileType.JSON.getType())
                .execute()
                .body());


        Observations response = sources.orElse(new Observations()/*ResponseBody.create(MediaType.parse("application/json"), "{\"key\":[\"empty result\"]}")*/);

        /*Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(response.string());*/

        return response;//gson.toJson(je);
    }


}

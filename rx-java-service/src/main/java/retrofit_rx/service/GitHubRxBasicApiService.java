package retrofit_rx.service;

import models.Contributor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit_rx.api.GitHubRxBasicApi;
import rx.Observable;
import utils.Utils;

import java.util.Properties;

public class GitHubRxBasicApiService {

    private Properties properties = Utils.getProperties();
    private GitHubRxBasicApi api;


    public GitHubRxBasicApiService() {
        String baseUrl = properties.getProperty("baseUrl");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        api = retrofit.create(GitHubRxBasicApi.class);
    }


    public Observable<String> getTopContributors(String userName) {

        return api
                .listRepos(userName)
                .flatMapIterable(x -> x)
                .flatMap(repo -> api.listRepoContributors(userName, repo.getName()))
                .flatMapIterable(x -> x)
                .filter(c -> c.getContributions() > 10.0)
                .sorted((a, b) -> (int) (b.getContributions() - a.getContributions()))
                .map(Contributor::toString)
                .distinct();
    }

}

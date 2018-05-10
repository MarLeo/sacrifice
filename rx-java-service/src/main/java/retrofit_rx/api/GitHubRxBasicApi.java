package retrofit_rx.api;

import models.Contributor;
import models.Repository;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

import java.util.List;

public interface GitHubRxBasicApi {

    @GET("users/{user}/repos")
    Observable<List<Repository>> listRepos(@Path("user") String user);

    @GET("repos/{user}/{repo}/contributors")
    Observable<List<Contributor>> listRepoContributors(@Path("user") String user,
                                                       @Path("repo") String repo);
}

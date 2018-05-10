package retrofit_basic.api;

import models.Contributor;
import models.Repository;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface GitHubBasicApi {

    @GET("users/{user}/repos")
    Call<List<Repository>> listRepos(@Path("user") String user);

    @GET("repos/{user}/{repo}/contributors")
    Call<List<Contributor>> listRepoContributors(@Path("user") String user,
                                                 @Path("repo") String repo);
}

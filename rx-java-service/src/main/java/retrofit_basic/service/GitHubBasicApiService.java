package retrofit_basic.service;

import models.Contributor;
import models.Repository;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit_basic.api.GitHubBasicApi;
import utils.Utils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GitHubBasicApiService {

    private Properties properties = Utils.getProperties();
    private GitHubBasicApi api;

    private Predicate<Contributor> contributorPredicate;

    public GitHubBasicApiService() {
        String baseUrl = properties.getProperty("baseUrl");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(GitHubBasicApi.class);
    }


    public List<String> getTopContributors(String userName) throws IOException {
        List</*LinkedTreeMap*/Repository> repos = api
                .listRepos(userName)
                .execute()
                .body();

        repos = repos != null ? repos : Collections.emptyList();

        return repos.stream()
                // .map(repo -> new Repository(String.valueOf(repo.get("name")), String.valueOf(repo.get("description"))))
                .flatMap(repository -> getContributors(userName, repository))
                .sorted((a, b) -> (int) (b.getContributions() - a.getContributions()))
                .map(Contributor::toString)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }


    private Stream<Contributor> getContributors(String userName,
                                                Repository repository) {
        List</*LinkedTreeMap*/Contributor> contributors = null;

        try {
            contributors = api
                    .listRepoContributors(userName, repository.getName())
                    .execute()
                    .body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        contributors = contributors != null ? contributors : Collections.emptyList();

        contributorPredicate = contributor -> contributor.getContributions() > 10.0;
        return contributors.stream()
                // .map(contributor -> new Contributor(String.valueOf(contributor.get("login")), Double.valueOf(contributor.get("contributions").toString())))
                .filter(contributorPredicate);

    }


}

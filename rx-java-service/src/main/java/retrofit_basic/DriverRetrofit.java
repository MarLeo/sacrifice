package retrofit_basic;

import retrofit_basic.service.GitHubBasicApiService;

import java.io.IOException;
import java.util.List;

public class DriverRetrofit {

    public static void main(String[] args) throws IOException {
        String userName = "eugenp";

        List<String> topContributors = new GitHubBasicApiService().getTopContributors(userName);
        topContributors.forEach(System.out::println);

    }
}

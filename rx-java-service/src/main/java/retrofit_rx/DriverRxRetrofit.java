package retrofit_rx;

import retrofit_rx.service.GitHubRxBasicApiService;
import rx.Observable;

public class DriverRxRetrofit {

    public static void main(String[] args) {
        String userName = "eugenp";

        Observable contributors = new GitHubRxBasicApiService().getTopContributors(userName);
        contributors.subscribe(System.out::println);


    }
}

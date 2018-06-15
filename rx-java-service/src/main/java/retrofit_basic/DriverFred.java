package retrofit_basic;

import models.Categories;
import models.Observations;
import models.Seriess;
import models.Tags;
import retrofit_basic.service.CategoryApiService;
import retrofit_basic.service.FredApiService;

import java.io.IOException;

public class DriverFred {


    public static void main(String[] args) throws IOException {


        Observations sources = new FredApiService().getAllSourceData();

        sources.getObservations().forEach(System.out::println);

        System.out.println("data sources: " + sources);


        Categories categories;
        Seriess seriess;
        Tags tags;

        CategoryApiService categoryApiService = new CategoryApiService();

        categories = categoryApiService.getRelatedCategory();
        categories.getCategories().forEach(System.out::println);
        System.out.println("data sources: " + categories);

        seriess = categoryApiService.getSeriesCategory();
        seriess.getSeriess().forEach(System.out::println);

        tags = categoryApiService.getTagsCategory();
        tags.getTags().forEach(System.out::println);


    }
}

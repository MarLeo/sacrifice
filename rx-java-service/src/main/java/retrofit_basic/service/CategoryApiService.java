package retrofit_basic.service;

import models.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit_basic.api.CategoryApi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.google.common.collect.Maps.newHashMap;

public class CategoryApiService {

    private static final String URI = Uris.CATEGORY_URI.getUri();
    private static final String API_KEY = Uris.API_KEY.getUri();
    private static final String TYPE = FileType.JSON.getType();

    private CategoryApi api;

    private Optional<Categories> categories;

    private Optional<Seriess> seriess;

    private Optional<Tags> tags;

    public CategoryApiService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(CategoryApi.class);
    }


    public Categories getCategory() throws IOException {
        categories = Optional.ofNullable(api
                .getCategory(13, API_KEY, TYPE)
                .execute()
                .body());

        return categories.orElse(new Categories());
    }

    public Categories getChildCategory() throws IOException {
        categories = Optional.ofNullable(api
                .getChildCategory(13, "", "", API_KEY, TYPE)
                .execute()
                .body());

        return categories.orElse(new Categories());
    }

    public Categories getRelatedCategory() throws IOException {
        categories = Optional.ofNullable(api
                .getRelatedCategory(32073, "", "", API_KEY, TYPE)
                .execute()
                .body());

        return categories.orElse(new Categories());
    }


    public Seriess getSeriesCategory() throws IOException {
        Map<String, String> data = new HashMap<>();
        data.put("limit", String.valueOf(1000));
        data.put("offset", String.valueOf(0));
        data.put("order_by", "series_id");
        data.put("sort_order", "asc");
        data.put("filter_variable", "");
        data.put("filter_value", "");
        data.put("tag_names", "");
        data.put("exclude_tag_names", "");

        seriess = Optional.ofNullable(api
                .getSeriesCategory(125, API_KEY, TYPE, data)
                .execute()
                .body());

        return seriess.orElse(new Seriess());
    }

    public Tags getTagsCategory() throws IOException {
        Map<String, String> data = newHashMap();
        data.put("tag_names", "");
        data.put("tag_group_id", "");
        data.put("search_text", "");
        data.put("limit", String.valueOf(1000));
        data.put("offset", String.valueOf(0));
        data.put("order_by", "series_count");
        data.put("sort_order", "asc");

        tags = Optional.ofNullable(api
                .getTagsCategory(125, API_KEY, TYPE, data)
                .execute()
                .body());

        return tags.orElse(new Tags());
    }


    public Tags getRelatedTagsCategory() throws IOException {
        Map<String, String> data = newHashMap();
        data.put("tag_names", "");
        data.put("tag_group_id", "");
        data.put("search_text", "");
        data.put("limit", String.valueOf(1000));
        data.put("offset", String.valueOf(0));
        data.put("order_by", "series_count");
        data.put("sort_order", "asc");

        tags = Optional.ofNullable(api
                .getRelatedTagsCategory(125, API_KEY, TYPE, data)
                .execute()
                .body());

        return tags.orElse(new Tags());

    }

}

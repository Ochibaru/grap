package com.grap.dao;

import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class NetworkDAO {

    private static final String BASE_URL = "https://api.edamam.com/api/nutrition-details?";
    private static final String APP_ID = "d0eb834c";
    private static final String APP_KEY = "c487dada5b43f5ac5d1c90d6ccd92502";

    OkHttpClient client;

    /**
     * Return the data found at the given endpoint
     * @param endpoint a URL or other location where we can find data.
     * @return All of the data returned as one string.
     * @throws Exception
     */
    public String request(String endpoint) throws Exception  {
        String responseBody = "";

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/octet-stream");
        Request request = new Request.Builder()
                .url(endpoint)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()){

            assert response.body() != null;
            responseBody = response.body().string();

        }
        return responseBody;
    }

    /**
     * POST JSON in the Request body
     * Return the data found at the given endpoint
     * @param endpoint a URL or other location where we can find data.
     * @return All of the data returned as one string.
     */
    public String requestJSON(String endpoint) throws IOException{

        String responseBodyJSON;
        final String COMPLETE_URL = BASE_URL + "app_id=" + APP_ID + "&app_key=" + APP_KEY;

        OkHttpClient client = new OkHttpClient();

        final RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), new File("src/test/resources/EdamamNutritionAndRecipeAnalysisSample-REQUEST"));

        final Request request = new Request.Builder()
                .url(COMPLETE_URL)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()){

            assert response.body() != null;
            responseBodyJSON = response.body().string();

        }
        return responseBodyJSON;

    }
}


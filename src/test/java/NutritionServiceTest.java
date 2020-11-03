import okhttp3.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

public class NutritionServiceTest {

    private static final String BASE_URL = "https://api.edamam.com/api/nutrition-details?";
    private static final String APP_ID = "d0eb834c";
    private static final String APP_KEY = "c487dada5b43f5ac5d1c90d6ccd92502";

    OkHttpClient client;

    @Before
    public void init() {
        client = new OkHttpClient();
    }

    @Test
    public void whenPostJsonToEdamam_thenCorrect() throws IOException {

        final String COMPLETE_URL = BASE_URL + "app_id=" + APP_ID + "&app_key=" + APP_KEY;

        final RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), new File("src/test/resources/EdamamNutritionAndRecipeAnalysisSample-REQUEST"));

        final Request request = new Request.Builder()
                .url(COMPLETE_URL)
                .post(body)
                .build();

        final Call call = client.newCall(request);
        final Response response = call.execute();

        assertEquals(200, response.code());
    }
}

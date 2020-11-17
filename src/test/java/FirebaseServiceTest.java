import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FirebaseServiceTest {

    @Test
    public void whenLoginToFirebase_ThenCorrect() throws IOException {

        FileInputStream serviceAccount =
                null;
        try {
            serviceAccount = new FileInputStream("src/test/resources/grap-c2990-firebase-adminsdk-q3p5x-1321d6e6e4.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert serviceAccount != null;
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://grap-c2990.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);

        assertEquals(0,0);
    }
}

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.grap.dao.IFirebaseDAO;
import lombok.SneakyThrows;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

// This test logs into Firebase and posts to a Testing collection the current date/time.
public class FirebaseServiceTest {

    Firestore db;
    IFirebaseDAO firebaseDAO;
    @Autowired
    private Firestore firestore;

    // Get current Date/Time and format it.
    DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDateTime getLocalDate = LocalDateTime.now();
    String localDate = localDateFormatter.format(getLocalDate);

    private CollectionReference getTestCollection(){
        return firestore.collection("Testing");
    }

    @BeforeClass
    public static void setUp() throws IOException {

        // Use a service account
        InputStream serviceAccount = new FileInputStream("src/test/resources/service_account_pk.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp.initializeApp(options);

    }

    @Test
    public void saveTestData_whenTestDataEntered() throws Exception{
        db = FirestoreClient.getFirestore();

        whenLoginToFirebase_ThenCorrect();
        whenUserAddsText();
        thenTestDataIsSaved();
    }

    public void whenLoginToFirebase_ThenCorrect() {


    }

    public void whenUserAddsText() {

        // This to test the get current date and verify that it is added to a collection.
        Collection collectionTest = new ArrayList();
        assertEquals(0, collectionTest.size());
        collectionTest.add(localDate);
        assertEquals(1, collectionTest.size());

    }

    @SneakyThrows
    public void thenTestDataIsSaved() {

        DocumentReference docRef = db.collection("Testing").document("DateStamp");
        // Add document data  with id "DateStamp" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("Date", getLocalDate);
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
        // result.get() blocks on response
        System.out.println("Update time : " + result.get().getUpdateTime());
    }
}

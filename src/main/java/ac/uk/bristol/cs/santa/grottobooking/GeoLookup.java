package ac.uk.bristol.cs.santa.grottobooking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class GeoLookup {

    private GeoApiContext context;

    public GeoLookup(@Value("${external-services.google-maps.api-key}") final String apiKey) {
        context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }

    public String geocode(String address) throws InterruptedException, ApiException, IOException {

        GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(results[0].addressComponents);
    }

}

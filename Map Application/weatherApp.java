
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * This class implements a simple weather application that retrieves and
 * displays the current weather information for a given location using the
 * OpenWeatherMap API. The user can enter a location in a text field and click a
 * "Search" button to retrieve the weather information. The temperature and
 * weather condition are displayed on the GUI using JLabels.
 *
 * @author simonfeng
 */
public class weatherApp extends JFrame implements ActionListener {

    private JLabel tempLabel;
    private JLabel conditionLabel;
    private JTextField locationField;
    private JLabel info;

    public weatherApp() throws JSONException {
        // Set up the GUI
        setTitle("Current Weather");
        setSize(350, 150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the GUI components
        tempLabel = new JLabel();
        conditionLabel = new JLabel();
        info = new JLabel();
        

        // Add the components to the GUI
        add(info, BorderLayout.NORTH);
        add(tempLabel, BorderLayout.CENTER);
        add(conditionLabel, BorderLayout.SOUTH);

        // Retrieve the default weather data and update the GUI
        updateWeather("London, Ontario"); // default set to London, Ontario

        // Show the GUI
        setVisible(true);
    }

    /**
     * Updates the weather information on the GUI for the given location.
     *
     * @param location the location to retrieve the weather information for
     * @throws JSONException if there is an error parsing the JSON response from
     * the API
     */
    private void updateWeather(String location) throws JSONException {
        String apiKey = "a2580fbc97d635375421187db7f57cc8"; // api key generated from website

        // Build the API request URL
        String apiUrl = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", location, apiKey);

        // Use OkHttp to make the API request
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(apiUrl)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            JSONObject json = new JSONObject(responseBody);

            // Extract the relevant data
            double temp = json.getJSONObject("main").getDouble("temp");
            String condition = json.getJSONArray("weather").getJSONObject(0).getString("description");

            // Format the data
            info.setText("Weather in London, Ontario");
            tempLabel.setText(String.format("Temperature: %.1f °C", temp));
            conditionLabel.setText("Condition: " + condition);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update the weather information when the search button is clicked
        String location = locationField.getText();
        try {
            updateWeather(location);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * The main method of the application, which creates a new weatherApp
     * object.
     *
     * @param args the command line arguments (not used)
     * @throws JSONException if there is an error parsing the JSON response from
     * the API
     */
    public static void main(String[] args) throws JSONException {
        new weatherApp();
    }
}

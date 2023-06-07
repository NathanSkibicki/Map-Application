
import java.awt.Point;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class handles the list of POIs of the program
 * @author Yiling, Michael
 */
public class POIHandler {

    private JSONObject poiFile;
    private ArrayList<POI> poiList;

    /**
     * Constructs a new instance of the testPOIHandler class. Initializes an
     * empty ArrayList of testPOI objects and attempts to read in POI data from
     * a JSON file. If the file exists and contains data, the POIs are parsed
     * and added to the ArrayList. If an error occurs during file I/O or
     * parsing, an error message is printed to the console.
     */
    public POIHandler() {
        JSONParser jsonParser = new JSONParser();
        poiList = new ArrayList<POI>();

        try {
            FileReader reader = new FileReader("POIs.json");
            poiFile = (JSONObject) jsonParser.parse(reader);
            JSONArray pois = (JSONArray) poiFile.get("pois");

            for (Object o : pois) {
                JSONObject poi = (JSONObject) o;
                String name = (String) poi.get("name");
                Point coordinate = new Point(((Long) poi.get("xCoord")).intValue(), ((Long) poi.get("yCoord")).intValue());;
                String building = (String) poi.get("building");
                String floor = (String) poi.get("floor");
                String description = (String) poi.get("description");
                String type = (String) poi.get("type");
                boolean custom = (boolean) poi.get("custom");
                boolean favourite = (boolean) poi.get("favourite");

                POI newPOI = new POI(name, coordinate, building, floor, description, type, custom, favourite);
                poiList.add(newPOI);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * Gets the list of POIs
     * @return ArrayList of POIs
     */
    public ArrayList<POI> getPOIList() {
        return poiList;
    }
    
    /**
     * Adds POIs to the list and saves the updated list to the .JSON file
     * @param poi 
     */
    public void addPOI(POI poi) {
        poiList.add(poi);
        saveToJSON();
    }
    /**
     * Replaces a POI in the list 
     * @param poi POI to be replaced
     * @param newPOI POI replacement
     */
    public void editPOI(POI poi, POI newPOI) {
        int i = poiList.indexOf(poi);
        poiList.set(i, newPOI);
        saveToJSON();
    }
    
    /**
     *
     * Removes a point of interest from the list of POIs and saves the updated
     * list to a JSON file.
     * @param poi the point of interest to remove
     */
    public void removePOI(POI poi) {
        poiList.remove(poi);
        saveToJSON();
    }

    /**
     * Saves the list of POIs to a JSON file.
     */
    private void saveToJSON() {
        JSONArray poiArray = new JSONArray();
        for (int i = 0; i < poiList.size(); i++) {
            poiArray.add(poiList.get(i).createJSONObj());
        }
        poiFile.put("pois", poiArray);
        try (FileWriter file = new FileWriter("POIs.json")) {
            if (poiArray.isEmpty()) {
                file.write("");
                file.flush();
            } else {
                file.write(poiFile.toString());
                file.flush();
            }

        } catch (IOException e) {
        }
    }

}

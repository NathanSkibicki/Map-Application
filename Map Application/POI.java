
import java.awt.Point;
import org.json.simple.JSONObject;


/**
 *
 * A class representing a point of interest (POI) on a map. POIs have a name,
 * coordinate (x,y), building name, floor number, description, type, and
 * custom/favourite status.
 * @author Yiling, Nathan
 */
public class POI {

    // Instance variables
    private String name;
    private Point coordinate;
    private String building;
    private String floor;
    private String description;
    private String type;
    private boolean custom;
    private boolean favourite;

    /**
     * Constructor for creating a new POI object.
     *
     * @param name the name of the POI
     * @param coordinate the coordinate of the POI (x,y)
     * @param building the building name of the POI
     * @param floor the floor number of the POI
     * @param description the description of the POI
     * @param type the type of the POI (e.g. Accessibility, Classroom, Lab,
     * Restaurant, Washroom)
     * @param custom the custom status of the POI (true if custom, false if not)
     * @param favourite the favourite status of the POI (true if favourite,
     * false if not)
     */
    public POI(String name, Point coordinate, String building, String floor, String description, String type, boolean custom, boolean favourite) {
        this.name = name;
        this.coordinate = coordinate;
        this.building = building;
        this.floor = floor;
        this.description = description;
        this.type = type;
        this.custom = custom;
        this.favourite = favourite;

    }
    
    /**
     * Constructor that takes another POI as input
     * @param poi POI to model this poi after
     */
    public POI(POI poi) {
        name = poi.getName();
        coordinate = poi.getCoordinate();
        building = poi.getBuilding();
        floor = poi.getFloor();
        description = poi.getDescription();
        type = poi.getType();
        custom = poi.isCustom();
        favourite = poi.isFavourite();
    }
    
    /**
     * Get the name of the POI.
     * @return the name of the POI
     */
    public String getName() {
        return name;
    }

    /**
     * Get the coordinate of the POI.
     * @return the coordinate of the POI
     */
    public Point getCoordinate() {
        return coordinate;
    }

    /**
     * Get the building name of the POI.
     * @return the building name of the POI
     */
    public String getBuilding() {
        return building;
    }

    /**
     * Get the floor number of the POI.
     * @return the floor number of the POI
     */
    public String getFloor() {
        return floor;
    }

    /**
     * Get the description of the POI.
     * @return the description of the POI
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the type of the POI.
     * @return the type of the POI
     */
    public String getType() {
        return type;
    }

    /**
     * Get the custom status of the POI.
     * @return true if the POI is custom, false if not
     */
    public boolean isCustom() {
        return custom;
    }

    /**
     * Get the favourite status of the POI.
     * @return true if the POI is a favourite, false if not
     */
    public boolean isFavourite() {
        return favourite;
    }

    /**
     * Set the name of the POI.
     * @param name the new name of the POI
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the coordinate of the POI.
     * @param coordinate the new coordinate of the POI
     */
    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * Set the description of the POI.
     * @param description the new description of the POI
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the building attribute of the POI.
     * @param building a String representing the building where the POI is located
     */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**
     * Sets the floor of this testPOI object.
     * @param floor the new floor to set
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * Sets the type of this testPOI object.
     * @param type the new type to set
     */
    public void setType(String type) {
        this.type = type;

    }

    /**
     * Sets the custom flag of this testPOI object.
     * @param custom the new value for the custom flag
     */
    public void setCustom(boolean custom) {
        this.custom = custom;
    }

    /**
     * Sets the favourite flag of this testPOI object.
     * @param fav the new value for the favourite flag
     */
    public void setFavourite(boolean fav) {
        favourite = fav;
    }
    
    /**
     * Checks if two POIs are equal
     * @param otherPoi POI to compare to this 
     * @return true if they have the same attributes; false otherwise
     */
    public boolean equals(POI otherPoi) {
        if (otherPoi.getName().equals(name) && otherPoi.getBuilding().equals(building) 
                && otherPoi.getFloor().equals(floor) && otherPoi.getCoordinate().equals(coordinate)
                && otherPoi.getType().equals(type) && otherPoi.isCustom() == custom && otherPoi.isFavourite() == favourite) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Creates a JSONObject containing the attributes of the testPOI object.
     * @return a JSONObject representing the testPOI object's attributes.
     */
    public JSONObject createJSONObj() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("xCoord", coordinate.x);
        json.put("yCoord", coordinate.y);
        json.put("building", building);
        json.put("floor", floor);
        json.put("description", description);
        json.put("type", type);
        json.put("custom", custom);
        json.put("favourite", favourite);
        return json;
    }
}

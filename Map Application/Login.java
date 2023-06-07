import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;

/**

The Login class handles user authentication and user creation by reading and writing to a JSON file.
This class contains two methods: makeUser and confirmUser.
@author Nathan, Simon, Moksh
*/

public class Login {
    
    
 /**
 * This method creates a new user in the JSON file. It takes in a Username, Password, and isAdmin boolean
 * to create the user with the given credentials. If the user already exists in the file, it will not create
 * a new user and will return false. If the user is successfully created, it will return true.
 *
 * @param Username the username of the new user
 * @param Password the password of the new user
 * @param isAdmin a boolean indicating if the new user has admin privileges or not
 * @return true if the user is successfully created, false otherwise
 */
    public boolean makeUser(String Username,String Password,boolean isAdmin) {
        String file = "Users.json";
        
        boolean userSearch = confirmUser(Username,Password)[0];
        boolean check = false;
        if (userSearch) {
            check = true;
            System.out.println("User already exists");
        }
        else if (check == false) {
        try {
           
            JSONParser parse = new JSONParser();
            JSONArray jsonArray = (JSONArray) parse.parse(new FileReader("Users.json"));

            JSONObject UserInfo = new JSONObject();
            UserInfo.put("Username", Username);
            UserInfo.put("Password", Password);
            UserInfo.put("isAdmin", isAdmin);

            jsonArray.add(UserInfo);

            // Write the updated JSONArray to the file
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();
            fileWriter.close();
            System.out.println("New user added to record!");

        } catch (IOException | ParseException e) {
            System.out.println("Error");
        }        
        
        }

        return true;
    }
 /**
 * This method confirms if the provided user credentials exist in the JSON file. It takes in a Username
 * and Password and returns a boolean array containing two elements: a boolean indicating if the user was found
 * and a boolean indicating if the user has admin privileges or not. If the user is not found, it will return
 * a boolean array with the first element set to false.
 *
 * @param Username the username of the user to search for
 * @param Password the password of the user to search for
 * @return a boolean array containing two elements: a boolean indicating if the user was found and a boolean
 *         indicating if the user has admin privileges or not.
 */
    public boolean[] confirmUser(String Username, String Password) {
    
    
        //JSON parser object to parse read file
        JSONParser parse = new JSONParser();
         boolean userSearch[]={false,false};
        try (FileReader reader = new FileReader("Users.json"))
        {
            //Read JSON file
            Object obj = parse.parse(reader);
            JSONArray UserList = (JSONArray) obj;
            //System.out.println(UserList);
            //Iterate over employee array
           //UserList.forEach( emp -> parseUser( (JSONObject) emp,Username,Password ) );
           for (Object userObj : UserList) {
                System.out.println("searching...");
                JSONObject user = (JSONObject) userObj;
                String Usn = (String) user.get("Username");   
                String Pw = (String) user.get("Password");
                Boolean iAd = (Boolean) user.get("isAdmin");
                if (Username.equals(Usn)&&Password.equals(Pw)){
                    System.out.println("User found!");
                    userSearch[0]=true;
                    userSearch[1]=iAd;
                    return userSearch;
                }
           }
                System.out.println("Incorrect password or username.");
           


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
    return userSearch;
    }   
}

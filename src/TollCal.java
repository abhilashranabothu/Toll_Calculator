import java.io.FileReader;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TollCal {
    private static final double TOLL_RATE = 0.25;
    private static HashMap<String, JSONObject> interchanges;
    private static HashMap<String, JSONObject> interchangesId;
    public static void main(String[] args) {
        loadInterchanges();
        costOfTrip("QEW", "Highway 403");
        costOfTrip("Salem Road", "QEW");
        costOfTrip("QEW", "Airport Road");
        costOfTrip("QEW", "Salem Road");
        costOfTrip("QEW", "Highway 27");
        costOfTrip("Airport Road", "Mississauga Road");
        costOfTrip("QEW", "QEW");
    }

    public static void costOfTrip(String from, String to) {

        try {

            JSONObject fromInterchange = interchanges.get(from);
            JSONObject toInterchange = interchanges.get(to);

            JSONArray ja1 = (JSONArray) fromInterchange.get("routes");
            JSONObject jo1 = (JSONObject) ja1.get(0);
            long id1 = ((long) jo1.get("toId"))-1;

            JSONArray ja2 = (JSONArray) toInterchange.get("routes");
            JSONObject jo2 = (JSONObject) ja2.get(0);
            long id2 = ((long) jo2.get("toId"))-1;

            double distance = 0;
            double cost = 0;

            if (id1 < id2){
                for(long i=id1; i<id2; i++) {
                    String s1 = Long.toString(i);
                    JSONObject jo3 = interchangesId.get(s1);
                    if(interchangesId.get(s1) == null) {
                        continue;
                    }
                    else {
                        JSONArray ja3 = (JSONArray) jo3.get("routes");
                        JSONObject jo4 = (JSONObject) ja3.get(0);
                        String distance1 = jo4.get("distance").toString();
                        distance += Double.parseDouble(distance1);
                    }
                }
            }
            else if (id1 > id2){
                for(long i=id1; i>id2; i--) {
                    String s1 = Long.toString(i);
                    JSONObject jo3 = interchangesId.get(s1);
                    if(interchangesId.get(s1) == null) {
                        continue;
                    }
                    else {
                        JSONArray ja3 = (JSONArray) jo3.get("routes");
                        JSONObject jo4 = (JSONObject) ja3.get(1);
                        String distance1 = jo4.get("distance").toString();
                        distance += Double.parseDouble(distance1);
                    }
                }
            }
            else {
                distance = 0;
            }


            cost = distance * TOLL_RATE;
            System.out.println(String.format("The Distance of Trip between %s and %s is %.2f KMs", from, to, distance));
            System.out.println(String.format("The Cost of Trip between %s and %s is %.2f $", from, to, cost));

        } catch(NullPointerException e) {
            System.out.print("NullPointer Exception Caught");
        }

    }

    private static void loadInterchanges() {
        interchanges = new HashMap<>();
        interchangesId = new HashMap<>();
        JSONParser parser = new JSONParser();

        try {
            //reading the interchanges.json file
            Object obj = parser.parse(new FileReader("interchanges.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject locations = (JSONObject) jsonObject.get("locations");

            //Iterating through each location and gathering its name and properties
            for (Object key : locations.keySet()) {
                String locationId = (String) key;
                JSONObject location = (JSONObject) locations.get(locationId);
                //Assigning ID of location as key and properties as value
                interchangesId.put(locationId, location);
                String name = (String) location.get("name");
                //Assigning Name of location as key and properties as value
                interchanges.put(name, location);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
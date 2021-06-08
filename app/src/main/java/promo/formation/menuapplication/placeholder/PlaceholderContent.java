package promo.formation.menuapplication.placeholder;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import promo.formation.menuapplication.MainActivity;


/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PlaceholderContent {

    /**
     * An array of sample (placeholder) items.
     */
    public static final List<PlaceholderItem> ITEMS = new ArrayList<PlaceholderItem>();

    /**
     * A map of sample (placeholder) items, by ID.
     */
    public static final Map<String, PlaceholderItem> ITEM_MAP = new HashMap<String, PlaceholderItem>();

    private static final int COUNT = 25;
/*
    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createPlaceholderItem(i));
        }
        addItem(new PlaceholderItem("26","boujour",makeDetails(26)));
    }*/

    private static void addItem(PlaceholderItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /*private static PlaceholderItem createPlaceholderItem(int position) {
        return new PlaceholderItem(String.valueOf(position), "Phare " + position, makeDetails(position),);
    }*/

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Phare: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A placeholder item representing a piece of content.
     */
    public static class PlaceholderItem {
        public final String id;
        public final String content;
        public final String details;
        public final String region;

        public PlaceholderItem(String id, String content, String details,String region) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.region = region;
        }

        @Override
        public String toString() {
            return content;
        }
    }

    /**
     *  lecture du json pour fabriquer les datas
     */
    public static void loadJson(){
        try {
            BufferedReader br = new BufferedReader(new
                    InputStreamReader(MainActivity.getContext().getAssets().open("phares_all.json")));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            String str = new String(sb.toString()) ;

            //TODO
            //parsing des elements du json (region, hauteur, coord ...
            //ajouter dans items
            JSONObject jObjConnection = new JSONObject(str);
            JSONObject jsonBix = jObjConnection.getJSONObject("phares");
            JSONArray jsonA=jsonBix.getJSONArray("liste");
            for(int i =0 ; i < jsonA.length() ; i++) {
                JSONObject obj = (JSONObject) jsonA.get(i);
                String nom = obj.getString("name");
                String region = obj.getString("region");
                addItem(new PlaceholderItem(String.valueOf(i),nom,makeDetails(i),region));
            }
            Log.d("PlaceHolderContent",str);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
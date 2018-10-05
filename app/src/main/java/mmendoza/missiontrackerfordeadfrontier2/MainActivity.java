package mmendoza.missiontrackerfordeadfrontier2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    // URL for the spreadsheet, Dead Frontier 2 - Mission Guides - https://tinyurl.com/DF2HAVEN
    public static final String URL =
            "https://docs.google.com/spreadsheets/d/1YlZoRmMcscNTR0sqa1tlY37oocdmDQk7tlkOCSYg6FA/htmlview";

    // Databases Helper
    private DBHelper db;

    // Private member variables for the Date.
    private TextView dateTextView;
    private String date;

    // Private member variables for the spinners.
    private Spinner missionCitySpinner;
    private Spinner giverCitySpinner;

    // Private member variables for the listView and adapter
    private ListView missionsListView;
    private MissionListAdapter missionsListAdapter;

    // Array to hold Mission objects for the list view
    private ArrayList<Mission> selectedMissionsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // String of city names used for both spinners.
        String[] cities = getResources().getStringArray(R.array.cities);

        // Create the database.
        // deleteDatabase(DBHelper.DATABASE_NAME); // TODO : Delete this or what
        // TODO: db.deleteOldMissions but where should i put this hm...
        db = new DBHelper(this);

        // TODO: Set crawl delay to 1
        // Start the JSoup process.
        new Missions().execute();

        // Connect the variables.
        dateTextView = findViewById(R.id.dateTextView);
        missionCitySpinner = findViewById(R.id.missionCitySpinner);
        giverCitySpinner = findViewById(R.id.giverCitySpinner);

        // ListView Adapter
        missionsListView = findViewById(R.id.missionsListView);
        missionsListAdapter =
                new MissionListAdapter(this, R.layout.mission_list_item, selectedMissionsList);

        // missionCitySpinner adapter
        final ArrayAdapter<String> missionCitySpinnerAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        missionCitySpinner.setAdapter(missionCitySpinnerAdapter);

        // giverCitySpinner adapter
        final ArrayAdapter<String> giverCitySpinnerAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
                //new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, cities); // TODO: Which is better to use?
        giverCitySpinner.setAdapter(giverCitySpinnerAdapter); // TODO: Try using same adapter for both spinners...

        // missionCitySpinner Listener
        AdapterView.OnItemSelectedListener missionCitySpinnerListener = new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                spinnerItemSelected();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        };

        // giverCitySpinner Listener
        AdapterView.OnItemSelectedListener giverCitySpinnerListener = new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                spinnerItemSelected();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        };

        // Set the spinner listeners.
        // TODO : Try using one listener for both spinners... hm...
        missionCitySpinner.setOnItemSelectedListener(missionCitySpinnerListener);
        giverCitySpinner.setOnItemSelectedListener(giverCitySpinnerListener);

        // TODO: Add left swipe and right swipe functionality to list items.
    }

    /**
     * Fetches the data from the URL.
     */
    private class Missions extends AsyncTask
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] objects)
        {
            Document doc;

            try
            {
                // Connect to the URL
                doc = Jsoup.connect(URL).get();

                // Get the doc body
                Element body = doc.body();

                // Get the latest sheet's id, save only the integer portion of the id.
                Element sheet = body.select("div[id=top-bar] ul[id=sheet-menu] > li").first();
                String sheetId = sheet.attr("id").split("-")[2];

                // Get the text from the sheet, which contains the date in MM_DD_YYYY format.
                date = sheet.text();

                //sheetId = "869625780"; // TODO : Delete this terrible terrible line after testing

                // Get the tbody of the sheet with the matching sheetId
                Elements tbody = body.select("div[id=sheets-viewport] div[id=" + sheetId
                        + "] table[class=waffle] tbody");

                // Get the tr items from the tbody (rows from the spreadsheet)
                Elements trItems = tbody.select("tr");

                // Get each row's columns, skip the first two rows
                for (int i = 2; i < trItems.size(); ++i)
                {
                    if (trItems.get(i).text().split(" ").length > 2) // Ignore blank rows
                    {
                        // ArrayList storing the data for each row.
                        ArrayList<String> missionInfo = new ArrayList<>();

                        //System.out.println(trItems.get(i).text()); // Entry all on one line
                        // Get the td items from the tr items (columns from the spreadsheet)
                        Elements tdItems = trItems.get(i).select("td");
                        for (Element td : tdItems)
                        {
                            if (!td.text().isEmpty())
                            {
                                missionInfo.add(td.text());
                                //System.out.println(td.text()); // Entry pieces on new lines
                            }
                            else
                            {
                                missionInfo.add("");
                                //System.out.println("EMPTY!");
                            }
                        }

                        // Add a mission to the database using the missionInfo ArrayList.
                        Mission mission = new Mission(missionInfo.get(0), missionInfo.get(1),
                                missionInfo.get(2), missionInfo.get(3), missionInfo.get(4),
                                missionInfo.get(5), missionInfo.get(6), missionInfo.get(7),
                                missionInfo.get(8), missionInfo.get(9), missionInfo.get(10),
                                missionInfo.get(11), missionInfo.get(12), date);
                        db.addMission(mission);
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o)
        {
            super.onPostExecute(o);

            // Update the date TextView.
            dateTextView.setText(date);

            // Refresh the spinner once tht sync task has finished.
            spinnerItemSelected();

            // Set the list view adapter.
            missionsListView.setAdapter(missionsListAdapter);
        }
    }

    public void spinnerItemSelected()
    {
        // Get the selected items for both spinners.
        String missionCity = String.valueOf(missionCitySpinner.getSelectedItem());
        String giverCity = String.valueOf(giverCitySpinner.getSelectedItem());

        // Clear the adapter.
        missionsListAdapter.clear();

        // Get the missions from the database matching the selected mission and quest giver cities
        // and add them to the missionsListAdapter.
        missionsListAdapter.addAll(db.getMissions(missionCity, giverCity));
    }
}

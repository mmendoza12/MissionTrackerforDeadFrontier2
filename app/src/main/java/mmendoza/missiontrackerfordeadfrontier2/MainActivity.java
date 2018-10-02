package mmendoza.missiontrackerfordeadfrontier2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
{
    // URL for the spreadsheet, Dead Frontier 2 - Mission Guides - https://tinyurl.com/DF2HAVEN
    public static final String URL =
            "https://docs.google.com/spreadsheets/d/1YlZoRmMcscNTR0sqa1tlY37oocdmDQk7tlkOCSYg6FA/htmlview";

    //

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = getResources().getStringArray(R.array.cities);

        new Missions().execute();
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
                String date = sheet.text();

                sheetId = "771800048"; // TODO : Delete this terrible terrible line after testing

                // Get the tbody of the sheet with the matching sheetId
                Elements tbody = body.select("div[id=sheets-viewport] div[id=" + sheetId
                        + "] table[class=waffle] tbody");

                // Get the tr items from the tbody (rows from the spreadsheet)
                Elements trItems = tbody.select("tr");

                // Get each row's columns, skip the first two rows
                for (int i = 2; i < trItems.size(); ++i)
                {
                    if (trItems.get(i).text().split(" ").length > 1) // Ignore blank entries
                    {
                        System.out.println(trItems.get(i).text()); // Entry all on one line
                        // Get the td items from the tr items (columns from the spreadsheet)
                        Elements tdItems = trItems.get(i).select("td");
                        for (Element td : tdItems)
                        {
                            if (!td.text().isEmpty())
                            {
                                System.out.println(td.text()); // Entry pieces on new lines
                            }
                            else
                            {
                                System.out.println("EMPTY!");
                            }
                        }
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
        }
    }
}

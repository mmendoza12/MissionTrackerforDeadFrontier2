package mmendoza.missiontrackerfordeadfrontier2;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Enable home button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //
        getFragmentManager().
                beginTransaction().
                replace(android.R.id.content, new SettingsActivityFragment())
                .commit();
    }

    public static class SettingsActivityFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}

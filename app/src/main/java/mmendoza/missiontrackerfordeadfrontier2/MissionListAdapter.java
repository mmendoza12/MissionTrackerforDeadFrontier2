package mmendoza.missiontrackerfordeadfrontier2;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * List adapter for the list of missions.
 *
 * @author mmendoza
 */
public class MissionListAdapter extends ArrayAdapter<Mission>
{
    // Activity that uses the adapter (MainActivity)
    private Context mContext;
    // Layout file to inflate (R.layout.mission_list_item)
    private int mResource;
    // List of missions
    private List<Mission> mMissionsList;

    public MissionListAdapter(@NonNull Context context, @LayoutRes int resource,
                              @NonNull List<Mission> missionsList)
    {
        super(context, resource, missionsList);
        mContext = context;
        mResource = resource;
        mMissionsList = missionsList;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup Parent)
    {
        final Mission selectedMission = mMissionsList.get(pos);

        LayoutInflater inflater = (
                LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResource, null);

        LinearLayout missionListItemLinearLayout = view.findViewById(R.id.missionListLinearLayout);

        // Set the mission's title. Make the text grey if the mission is not confirmed
        TextView missionTitleTextView = view.findViewById(R.id.missionTitleTextView);
        missionTitleTextView.setText(selectedMission.getTitle());
        if (selectedMission.getConfirmed().isEmpty())
            missionTitleTextView.setTextColor(ContextCompat.getColor(mContext, R.color.gray));

        // Set the missions's location.
        TextView missionBuildingCityTextView = view.findViewById(R.id.missionBuildingCityTextView);
        missionBuildingCityTextView.setText(selectedMission.getMissionLocation());

        // Set the mission's guide.
        TextView missionGuideTextView = view.findViewById(R.id.missionGuideTextView);
        missionGuideTextView.setText(selectedMission.getMissionGuide());

        // Set the mission's quest giver name
        TextView questGiverNameTextView  = view.findViewById(R.id.questGiverNameTextView);
        questGiverNameTextView.setText(selectedMission.getQuestGiver());

        // Set the mission's quest giver location.
        TextView questGiverBuildingCityTextView  = view.findViewById(R.id.questGiverBuildingCityTextView);
        questGiverBuildingCityTextView.setText(selectedMission.getQuestGiverLocation());

        // Set the mission's quest giver room.
        TextView questGiverRoomTextView  = view.findViewById(R.id.questGiverRoomTextView);
        questGiverRoomTextView.setText(selectedMission.getQuestGiverRoom());

        // Set the mission's money.
        TextView moneyAmountTextView = view.findViewById(R.id.moneyAmountTextView);
        moneyAmountTextView.setText(selectedMission.getMoney());

        // Set the mission's exp.
        TextView expAmountTextView = view.findViewById(R.id.expAmountTextView);
        expAmountTextView.setText(selectedMission.getExp());

        // If the mission is completed, change the list item's text color to green.
        // If the mission is hidden, change it to red.
        if (selectedMission.getStatus() != 0)
        {
            int statusColor;
            if (selectedMission.getStatus() == 1)
                statusColor = R.color.colorAccent;
            else
                statusColor = R.color.red;

            // Mission title.
            missionTitleTextView.setTextColor(ContextCompat.getColor(mContext, statusColor));

            // Mission location.
            TextView missionLocationTextView = view.findViewById(R.id.missionLocationTextView);
            missionLocationTextView.setTextColor(ContextCompat.getColor(mContext, statusColor));

            // Mission walkthrough and guide.
            TextView missionWalkthroughTextView = view.findViewById(R.id.missionWalkthroughTextView);
            missionWalkthroughTextView.setTextColor(ContextCompat.getColor(mContext, statusColor));
            missionGuideTextView.setTextColor(ContextCompat.getColor(mContext, statusColor));

            // Mission quest giver.
            TextView questGiverTextView = view.findViewById(R.id.questGiverTextView);
            questGiverTextView.setTextColor(ContextCompat.getColor(mContext, statusColor));

            // Mission quest giver location.
            TextView questGiverLocationTextView = view.findViewById(R.id.questGiverLocationTextView);
            questGiverLocationTextView.setTextColor(ContextCompat.getColor(mContext, statusColor));

            // Mission quest giver walkthrough and room.
            TextView questGiverWalkthroughTextView = view.findViewById(R.id.questGiverWalkthroughTextView);
            questGiverWalkthroughTextView.setTextColor(ContextCompat.getColor(mContext, statusColor));
            questGiverRoomTextView.setTextColor(ContextCompat.getColor(mContext, statusColor));

            // Mission money.
            TextView moneyTextView = view.findViewById(R.id.moneyTextView);
            moneyTextView.setTextColor(ContextCompat.getColor(mContext, statusColor));
            moneyAmountTextView.setTextColor(ContextCompat.getColor(mContext, statusColor));

            TextView expTextView = view.findViewById(R.id.expTextView);
            expTextView.setTextColor(ContextCompat.getColor(mContext, statusColor));
            expAmountTextView.setTextColor(ContextCompat.getColor(mContext, statusColor));
        }

        // Set the tag as the selected mission.
        missionListItemLinearLayout.setTag(selectedMission);

        return view;
    }
}

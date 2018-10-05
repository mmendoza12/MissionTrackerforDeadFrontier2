package mmendoza.missiontrackerfordeadfrontier2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper
{
    private Context mContext;

    // Database Name and Version
    static final String DATABASE_NAME = "mmendoza.missiontrackerfordeadfrontier2.Mission Tracker for Dead Frontier 2 Database";
    static final int DATABASE_VERSION = 1;

    // Database Table "Missions" and its fields
    private static final String MISSIONS_TABLE = "Missions";
    public static final String FIELD_INDEX = "ind";
    private static final String FIELD_MISSION_BUILDING = "mission_building";
    private static final String FIELD_MISSION_CITY = "mission_city";
    public static final String FIELD_MISSION_MISSION = "mission_mission";
    public static final String FIELD_MISSION_NOTE = "mission_note";
    public static final String FIELD_MISSION_GUIDE = "mission_guide";
    public static final String FIELD_QUEST_GIVER = "quest_giver";
    public static final String FIELD_QUEST_GIVER_BUILDING = "quest_giver_building";
    public static final String FIELD_QUEST_GIVER_CITY = "quest_giver_city";
    public static final String FIELD_QUEST_GIVER_ROOM = "quest_giver_room";
    public static final String FIELD_MONEY = "money";
    public static final String FIELD_EXP = "exp";
    public static final String FIELD_CONFIRMED = "confirmed";
    public static final String FIELD_DATE = "date";
    public static final String FIELD_STATUS = "status";
    public static final String FIELD_HIDDEN = "hidden";

    /**
     * Constructs the database.
     *
     * @param context The Context
     */
    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    /**
     * Creates the table.
     *
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String createQuery = "CREATE TABLE IF NOT EXISTS " + MISSIONS_TABLE + "("
                + FIELD_INDEX + " TEXT, "
                + FIELD_MISSION_BUILDING + " TEXT, "
                + FIELD_MISSION_CITY + " TEXT, "
                + FIELD_MISSION_MISSION + " TEXT, "
                + FIELD_MISSION_NOTE + " TEXT, "
                + FIELD_MISSION_GUIDE + " TEXT, "
                + FIELD_QUEST_GIVER + " TEXT, "
                + FIELD_QUEST_GIVER_BUILDING + " TEXT, "
                + FIELD_QUEST_GIVER_CITY + " TEXT, "
                + FIELD_QUEST_GIVER_ROOM + " TEXT, "
                + FIELD_MONEY + " TEXT, "
                + FIELD_EXP + " TEXT, "
                + FIELD_CONFIRMED + " TEXT, "
                + FIELD_DATE + " TEXT, "
                + FIELD_STATUS + " INTEGER, "
                + FIELD_HIDDEN + " INTEGER" + ")";
        sqLiteDatabase.execSQL(createQuery);
    }

    /**
     *
     *
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        // TODO: onUpgrade
    }

    /**
     * Add a single mission to the db table "Missions" if its index is not found.
     * If the index is found, it will update the mission's record instead.
     *
     * @param mission The mission to be added.
     */
    public void addMission(Mission mission)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        // If no mission exists in the database with the mission's index, add it as a new mission.
        if (updateMission(mission, db) < 1)
        {
            ContentValues values = new ContentValues();

            values.put(FIELD_INDEX, mission.getIndex());
            values.put(FIELD_MISSION_BUILDING, mission.getMissionBuilding());
            values.put(FIELD_MISSION_CITY, mission.getMissionCity());
            values.put(FIELD_MISSION_MISSION, mission.getMissionMission());
            values.put(FIELD_MISSION_NOTE, mission.getMissionNote());
            values.put(FIELD_MISSION_GUIDE, mission.getMissionGuide());
            values.put(FIELD_QUEST_GIVER, mission.getQuestGiver());
            values.put(FIELD_QUEST_GIVER_BUILDING, mission.getQuestGiverBuilding());
            values.put(FIELD_QUEST_GIVER_CITY, mission.getQuestGiverCity());
            values.put(FIELD_QUEST_GIVER_ROOM, mission.getQuestGiverRoom());
            values.put(FIELD_MONEY, mission.getMoney());
            values.put(FIELD_EXP, mission.getExp());
            values.put(FIELD_CONFIRMED, mission.getConfirmed());
            values.put(FIELD_DATE, mission.getDate());
            values.put(FIELD_STATUS, mission.getStatus());
            values.put(FIELD_HIDDEN, mission.getHidden());

            db.insert(MISSIONS_TABLE, null, values);
        }

        db.close();
    }

    /**
     * Update's a mission's info if its index exists in the database.
     *
     * @param mission
     * @param db
     * @return The number of rows updated.
     */
    public int updateMission(Mission mission, SQLiteDatabase db)
    {
        ContentValues values = new ContentValues();

        values.put(FIELD_MISSION_BUILDING, mission.getMissionBuilding());
        values.put(FIELD_MISSION_CITY, mission.getMissionCity());
        values.put(FIELD_MISSION_MISSION, mission.getMissionMission());
        values.put(FIELD_MISSION_NOTE, mission.getMissionNote());
        values.put(FIELD_MISSION_GUIDE, mission.getMissionGuide());
        values.put(FIELD_QUEST_GIVER, mission.getQuestGiver());
        values.put(FIELD_QUEST_GIVER_BUILDING, mission.getQuestGiverBuilding());
        values.put(FIELD_QUEST_GIVER_CITY, mission.getQuestGiverCity());
        values.put(FIELD_QUEST_GIVER_ROOM, mission.getQuestGiverRoom());
        values.put(FIELD_MONEY, mission.getMoney());
        values.put(FIELD_EXP, mission.getExp());
        values.put(FIELD_CONFIRMED, mission.getConfirmed());
        values.put(FIELD_DATE, mission.getDate());

        int updated = db.update(MISSIONS_TABLE, values, FIELD_QUEST_GIVER + " = ?",
                new String[]{mission.getQuestGiver()});
        return updated;
    }

    /**
     * Deletes missions that have an expired date.
     *
     * @param date The current date to compare to.
     */
    public void deleteOldMissions(String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MISSIONS_TABLE, FIELD_DATE + " != ?", new String[]{date});
        db.close();
    }

    public ArrayList<Mission> getMissions(String missionCity, String questGiverCity)
    {
        ArrayList<Mission> missionsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                MISSIONS_TABLE,
                new String[] {FIELD_INDEX, FIELD_MISSION_BUILDING, FIELD_MISSION_CITY,
                        FIELD_MISSION_MISSION, FIELD_MISSION_NOTE, FIELD_MISSION_GUIDE,
                        FIELD_QUEST_GIVER, FIELD_QUEST_GIVER_BUILDING, FIELD_QUEST_GIVER_CITY,
                        FIELD_QUEST_GIVER_ROOM, FIELD_MONEY, FIELD_EXP, FIELD_CONFIRMED, FIELD_DATE,
                        FIELD_STATUS, FIELD_HIDDEN},
                null, null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            do {
                Mission mission = new Mission(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6), cursor.getString(7),
                        cursor.getString(8), cursor.getString(9), cursor.getString(10),
                        cursor.getString(11), cursor.getString(12), cursor.getString(13),
                        cursor.getInt(14), cursor.getInt(15));
                // All cities selected
                if (missionCity.equals("All Cities") && questGiverCity.equals("All Cities"))
                    missionsList.add(mission);
                // Specific mission city, all giver cities
                else if (!missionCity.equals("All Cities") && questGiverCity.equals("All Cities"))
                    if (mission.getMissionCity().equals(missionCity))
                        missionsList.add(mission);
                // All mission cities, specific giver city
                else if (missionCity.equals("All Cities") && !questGiverCity.equals("All Cities"))
                    if (mission.getQuestGiverCity().equals(questGiverCity))
                        missionsList.add(mission);
                // Specific cities for both fields
                else
                    if (mission.getMissionCity().equals(missionCity)
                            && mission.getQuestGiverCity().equals(questGiverCity))
                        missionsList.add(mission);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return missionsList;
    }
}

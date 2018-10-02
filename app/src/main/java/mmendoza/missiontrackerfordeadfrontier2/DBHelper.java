package mmendoza.missiontrackerfordeadfrontier2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
    private Context mContext;

    // Database Name and Version
    static final String DATABASE_NAME = "mmendoza.missiontrackerfordeadfrontier2.Mission Tracker for Dead Frontier 2 Database";
    static final int DATABASE_VERSION = 1;

    // Database Table "Missions" and its fields
    private static final String MISSIONS_TABLE = "Missions";
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
                + FIELD_DATE + " TEXT" + ")";
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
}

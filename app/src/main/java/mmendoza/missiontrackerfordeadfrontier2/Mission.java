package mmendoza.missiontrackerfordeadfrontier2;

/**
 * Mission class to represent a single mission of Dead Frontier 2.
 *
 * @author mmendoza
 */
public class Mission
{
    // The Mission's row in the spreadsheet, acts as a unique identifier
    private int mRow;

    // The Mission's index in the spreadsheet
    private int mIndex;

    // Mission info
    private String mMissionBuilding;
    private String mMissionCity;
    private String mMissionMission;
    private String mMissionNote;
    private String mMissionGuide;

    // Quest Giver info
    private String mQuestGiver;
    private String mQuestGiverBuilding;
    private String mQuestGiverCity;
    private String mQuestGiverRoom;

    // Reward info
    private int mMoney;
    private int mExp;

    // The contributor that confirmed the mission
    private String mConfirmed;

    // The date the mission was available
    private String mDate;

    // Mission status, 0 is uncompleted, 1 is completed, 2 is ignored
    private int mStatus;

    // Mission hidden status. Determines if the mission should be displayed in the list view.
    private int mHidden;

    /**
     * Creates Mission objects to store the data for a single mission.
     *
     * @param row Row of the mission on the spreadsheet. Acts as a unique identifier.
     * @param index Index of the mission on the spreadsheet.
     * @param missionBuilding The building the mission is completed in. (Sunnydale Apartments)
     * @param missionCity The city the mission is completed in. (Dallbow)
     * @param missionMission The mission of the mission. (Find Item, Find Person)
     * @param missionNote The note of the mission. (Wrist Watch, Pendant)
     * @param missionGuide The mission guide, step by step walkthrough.
     * @param questGiver The quest giver. (John Doe)
     * @param questGiverBuilding The building the quest giver is in. (Dallbow Police Department)
     * @param questGiverCity The city the quest giver is in. (Dallbow)
     * @param questGiverRoom The room the quest giver is in. (Lobby)
     * @param money The monetary reward for completion of the mission.
     * @param exp The experience rewarded for completing the mission.
     * @param confirmed The username of who the mission guide was confirmed by.
     * @param date The date the mission was available. (10-1-2018)
     */
    public Mission(int row, int index, String missionBuilding, String missionCity, String missionMission,
                   String missionNote, String missionGuide, String questGiver,
                   String questGiverBuilding, String questGiverCity, String questGiverRoom,
                   int money, int exp, String confirmed, String date)
    {
        mRow = row;
        mIndex = index;
        mMissionBuilding = missionBuilding;
        mMissionCity = missionCity;
        mMissionMission = missionMission;
        mMissionNote = missionNote;
        mMissionGuide = missionGuide;
        mQuestGiver = questGiver;
        mQuestGiverBuilding = questGiverBuilding;
        mQuestGiverCity = questGiverCity;
        mQuestGiverRoom = questGiverRoom;
        mMoney = money;
        mExp = exp;
        mConfirmed = confirmed;
        mDate = date;
        mStatus = 0;
        mHidden = 0;
    }

    /**
     * Creates Mission objects to store the data for a single mission.
     *
     * @param row Row of the mission on the spreadsheet. Acts as a unique identifier.
     * @param index Index of the mission on the spreadsheet.
     * @param missionBuilding The building the mission is completed in. (Sunnydale Apartments)
     * @param missionCity The city the mission is completed in. (Dallbow)
     * @param missionMission The mission of the mission. (Find Item, Find Person)
     * @param missionNote The note of the mission. (Wrist Watch, Pendant)
     * @param missionGuide The mission guide, step by step walkthrough.
     * @param questGiver The quest giver. (John Doe)
     * @param questGiverBuilding The building the quest giver is in. (Dallbow Police Department)
     * @param questGiverCity The city the quest giver is in. (Dallbow)
     * @param questGiverRoom The room the quest giver is in. (Lobby)
     * @param money The monetary reward for completion of the mission.
     * @param exp The experience rewarded for completing the mission.
     * @param confirmed The username of who the mission guide was confirmed by.
     * @param date The date the mission was available. (10-1-2018)
     * @param status The mission's status.
     * @param hidden The mission's hidden status.
     */
    public Mission(int row, int index, String missionBuilding, String missionCity,
                   String missionMission, String missionNote, String missionGuide, String questGiver,
                   String questGiverBuilding, String questGiverCity, String questGiverRoom,
                   int money, int exp, String confirmed, String date, int status, int hidden)
    {
        mRow = row;
        mIndex = index;
        mMissionBuilding = missionBuilding;
        mMissionCity = missionCity;
        mMissionMission = missionMission;
        mMissionNote = missionNote;
        mMissionGuide = missionGuide;
        mQuestGiver = questGiver;
        mQuestGiverBuilding = questGiverBuilding;
        mQuestGiverCity = questGiverCity;
        mQuestGiverRoom = questGiverRoom;
        mMoney = money;
        mExp = exp;
        mConfirmed = confirmed;
        mDate = date;
        mStatus = status;
        mHidden = hidden;
    }

    public int getRow()
    {
        return mRow;
    }

    public void setRow(int row)
    {
        mRow = row;
    }

    public int getIndex()
    {
        return mIndex;
    }

    public void setIndex(int index)
    {
        mIndex = index;
    }

    public String getMissionBuilding()
    {
        return mMissionBuilding;
    }

    public void setMissionBuilding(String missionBuilding)
    {
        mMissionBuilding = missionBuilding;
    }

    public String getMissionCity()
    {
        return mMissionCity;
    }

    public void setMissionCity(String missionCity)
    {
        mMissionCity = missionCity;
    }

    public String getMissionMission()
    {
        return mMissionMission;
    }

    public void setMissionMission(String missionMission)
    {
        mMissionMission = missionMission;
    }

    public String getMissionNote()
    {
        return mMissionNote;
    }

    public void setMissionNote(String missionNote)
    {
        mMissionNote = missionNote;
    }

    public String getMissionGuide()
    {
        return mMissionGuide;
    }

    public void setMissionGuide(String missionGuide)
    {
        mMissionGuide = missionGuide;
    }

    public String getQuestGiver()
    {
        return mQuestGiver;
    }

    public void setQuestGiver(String questGiver)
    {
        mQuestGiver = questGiver;
    }

    public String getQuestGiverBuilding()
    {
        return mQuestGiverBuilding;
    }

    public void setQuestGiverBuilding(String questGiverBuilding)
    {
        mQuestGiverBuilding = questGiverBuilding;
    }

    public String getQuestGiverCity()
    {
        return mQuestGiverCity;
    }

    public void setQuestGiverCity(String questGiverCity)
    {
        mQuestGiverCity = questGiverCity;
    }

    public String getQuestGiverRoom()
    {
        return mQuestGiverRoom;
    }

    public void setQuestGiverRoom(String questGiverRoom)
    {
        mQuestGiverRoom = questGiverRoom;
    }

    public int getMoney()
    {
        return mMoney;
    }

    public void setMoney(int money)
    {
        mMoney = money;
    }

    public int getExp()
    {
        return mExp;
    }

    public void setExp(int exp)
    {
        mExp = exp;
    }

    public String getConfirmed()
    {
        return mConfirmed;
    }

    public void setConfirmed(String confirmed)
    {
        mConfirmed = confirmed;
    }

    public String getDate()
    {
        return mDate;
    }

    public void setDate(String date)
    {
        mDate = date;
    }

    public int getStatus()
    {
        return mStatus;
    }

    public void setStatus(int status)
    {
        mStatus = status;
    }

    public int getHidden()
    {
        return mHidden;
    }

    public void setHidden(int hidden)
    {
        mHidden = hidden;
    }

    /**
     * Gets the title of a mission in the format of "Mission (Note)"
     * Ex. 1. Find Item (Pendant)
     */
    public String getTitle()
    {
        return String.valueOf(mIndex) + ". " + mMissionMission + " (" + mMissionNote + ")";
    }

    /**
     * Gets the location of a mission in the format of "Building (City)"
     * Ex. Donut King (Dallbow)
     */
    public String getMissionLocation()
    {
        return mMissionBuilding + " (" + mMissionCity + ")";
    }

    /**
     * Gets the location of a mission's quest giver in the format of "John Doe"
     */
    public String getQuestGiverLocation()
    {
        return mQuestGiverBuilding + " (" + mQuestGiverCity + ")";
    }
}

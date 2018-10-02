package mmendoza.missiontrackerfordeadfrontier2;

/**
 * Mission class to represent a single mission of Dead Frontier 2.
 *
 * @author mmendoza
 */
public class Mission
{
    // The Mission's index in the spreadsheet, acts as a unique identifier
    private int index;

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

    /**
     * Creates Mission objects to store the data for a single mission.
     *
     * @param index Index of the mission on the spreadsheet. Acts as a unique identifier.
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
    public Mission(int index, String missionBuilding, String missionCity, String missionMission,
                   String missionNote, String missionGuide, String questGiver,
                   String questGiverBuilding, String questGiverCity, String questGiverRoom,
                   int money, int exp, String confirmed, String date)
    {
        this.index = index;
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
    }
}

package com.example.kirmi.ks1807;

//This file is where the we define all of our tables and columns for our local database.
public class DatabaseSchema
{
    private static final String DBTable_UserAccount = "UserAccount";
    private static final String DBTable_UserPassword = "UserPassword";
    private static final String DBTable_MusicTrack = "MusicTrack";
    private static final String DBTable_PlayList = "PlayList";
    private static final String DBTable_TracksInPlayList = "TracksInPlayList";
    private static final String DBTable_UserMood = "UserMood";
    private static final String DBTable_UserDiary = "UserDiary";
    private static final String DBTable_UserSettings = "UserSettings";

    public String CreateAllTables()
    {
        String TableCreate = "";

        TableCreate = TableCreate + CreateUserAccount();
        TableCreate = TableCreate + CreateUserPassword();
        TableCreate = TableCreate + CreateMusicTrack();
        TableCreate = TableCreate + CreatePlayList();
        TableCreate = TableCreate + CreateUserMood();
        TableCreate = TableCreate + CreateTracksInPlayList();
        TableCreate = TableCreate + CreateUserDiary();
        TableCreate = TableCreate + CreateUserSettings();

        return TableCreate;
    }

    public String DropAllTables()
    {
        String TableDrop = "";

        TableDrop = TableDrop + "DROP TABLE IF EXISTS " + DBTable_UserAccount + " ";
        TableDrop = TableDrop + "DROP TABLE IF EXISTS " + DBTable_UserPassword + " ";
        TableDrop = TableDrop + "DROP TABLE IF EXISTS " + DBTable_MusicTrack + " ";
        TableDrop = TableDrop + "DROP TABLE IF EXISTS " + DBTable_PlayList + " ";
        TableDrop = TableDrop + "DROP TABLE IF EXISTS " + DBTable_TracksInPlayList + " ";
        TableDrop = TableDrop + "DROP TABLE IF EXISTS " + DBTable_UserMood + " ";
        TableDrop = TableDrop + "DROP TABLE IF EXISTS " + DBTable_UserDiary + " ";
        TableDrop = TableDrop + "DROP TABLE IF EXISTS " + DBTable_UserSettings + " ";
        return TableDrop;
    }

    private String CreateMusicTrack()
    {
        String MusicTrack;
        final String TrackID = "TrackID INTEGER PRIMARY KEY AUTOINCREMENT, ";
        final String UserID = "UserID INTEGER, ";
        final String TrackName = "TrackName TEXT, ";
        final String Genre = "Genre TEXT, ";
        final String Artist = "Artist TEXT, ";
        final String Length = "Length TEXT, ";
        final String HowManyTimesListened = "HowManyTimesListened TEXT ";

        MusicTrack = "CREATE TABLE " + DBTable_UserAccount + "(" + TrackID + UserID +
                TrackName + Genre + Artist + Length + HowManyTimesListened + ");\n";
        return MusicTrack;
    }

    private String CreatePlayList()
    {
        String PlayList;
        final String PlayListID = "PlayListID INTEGER PRIMARY KEY AUTOINCREMENT, ";
        final String UserID = "UserID INTEGER, ";
        final String PlayListName = "PlayListName TEXT, ";
        final String RecommendedBy = "RecommendedBy TEXT ";

        PlayList = "CREATE TABLE " + DBTable_UserAccount + "(" + PlayListID + UserID +
                PlayListName + RecommendedBy + ");\n";
        return PlayList;
    }

    private String CreateTracksInPlayList()
    {
        String TracksInPlayList;
        final String TracksInPlayListID = "TracksInPlayListID INTEGER PRIMARY KEY AUTOINCREMENT, ";
        final String UserID = "UserID INTEGER, ";
        final String PlayListID = "PlayListID INTEGER, ";
        final String TrackID = "TrackID INTEGER ";

        TracksInPlayList = "CREATE TABLE " + DBTable_UserAccount + "(" + TracksInPlayListID + UserID +
                PlayListID + TrackID + ");\n";
        return TracksInPlayList;
    }

    private String CreateUserMood()
    {
        String UserMood;
        final String MoodID = "MoodID INTEGER PRIMARY KEY AUTOINCREMENT, ";
        final String UserID = "UserID INTEGER, ";
        final String TrackID = "TrackID INTEGER, ";
        final String MoodBefore = "MoodBefore TEXT, ";
        final String MoodBeforeTime = "MoodBeforeTime DATETIME, ";
        final String MoodAfter = "MoodAfter TEXT, ";
        final String MoodAfterTime = "MoodAfterTime DATETIME, ";
        final String UserLiked = "UserLiked TEXT ";

        UserMood = "CREATE TABLE " + DBTable_UserAccount + "(" + MoodID + UserID +
                TrackID + MoodBefore + MoodBeforeTime + MoodAfter + MoodAfterTime +
                UserLiked + ");\n";
        return UserMood;
    }

    private String CreateUserPassword()
    {
        String UserPassword;
        final String PasswordID = "PasswordID INTEGER PRIMARY KEY AUTOINCREMENT, ";
        final String UserID = "UserID INTEGER, ";
        final String Password = "Password TEXT ";

        UserPassword = "CREATE TABLE " + DBTable_UserAccount + "(" + PasswordID + UserID +
                Password + ");\n";
        return UserPassword;
    }

    private String CreateUserAccount()
    {
        String UserAccount;
        final String UserID = "UserID INTEGER PRIMARY KEY AUTOINCREMENT, ";
        final String FirstName = "FirstName TEXT, ";
        final String LastName = "LastName TEXT, ";
        final String Age = "Age INTEGER, ";
        final String Gender = "Gender TEXT, ";
        final String EmailAddress = "EmailAddress TEXT, ";
        final String PreferredPlatform = "PreferredPlatform TEXT, ";
        final String SpotifyID = "SpotifyID TEXT, ";
        final String MusicQuestionOne = "MusicQuestionOne TEXT, ";
        final String MusicQuestionTwo = "MusicQuestionTwo TEXT, ";
        final String MusicQuestionThree = "MusicQuestionThree TEXT";

        UserAccount = "CREATE TABLE " + DBTable_UserAccount + "(" + UserID + FirstName +
                LastName + Age + Gender + EmailAddress + PreferredPlatform +
                SpotifyID + MusicQuestionOne + MusicQuestionTwo + MusicQuestionThree + ");\n";
        return UserAccount;
    }

    private String CreateUserDiary()
    {
        String UserDiary;
        final String UserDiaryID = "UserDiaryID INTEGER PRIMARY KEY AUTOINCREMENT, ";
        final String UserID = "UserID INTEGER, ";
        final String DiaryEntryDate = "DiaryEntryDate DATETIME, ";
        final String DiaryEntry = "DiaryEntry TEXT ";

        UserDiary = "CREATE TABLE " + DBTable_UserAccount + "(" + UserDiaryID + UserID +
                DiaryEntryDate + DiaryEntry + ");\n";
        return UserDiary;
    }

    private String CreateUserSettings()
    {
        String UserSettings;
        final String UserSettingID = "UserSettingID INTEGER PRIMARY KEY AUTOINCREMENT, ";
        final String UserID = "UserID INTEGER, ";
        final String MoodFrequency = "MoodFrequency TEXT, ";
        final String MakeRecommendations = "MakeRecommendations TEXT, ";
        final String AllowFriendRecommendations = "AllowFriendRecommendations TEXT ";

        UserSettings = "CREATE TABLE " + DBTable_UserAccount + "(" + UserSettingID + UserID +
                MoodFrequency + MakeRecommendations + AllowFriendRecommendations + ");\n";
        return UserSettings;
    }
}

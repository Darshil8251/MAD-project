package com.example.milkmantra;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class MyPreferenceManager {

    private String TAG = MyPreferenceManager.class.getSimpleName();
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    //id, name, email, dob,home_Address, phone_number, Current_place, Transplace1, Transplace2, Transplace3, Transplace4, Transplace5, sex, marrital_status
    private static final String PREF_NAME = "Transfer_Teacher";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_EMAIL = "user_college";
    private static final String KEY_USER_DOB = "user_dob";
    private static final String KEY_USER_HOME_ADDRESS = "user_home_address";
    private static final String KEY_USER_PHONE = "user_phone";

    private static final String KEY_USER_CURRENT_PLACE = "current_place";

    private static final String KEY_USER_TRANS_PLACE_1 = "trans_place_1";
    private static final String KEY_USER_TRANS_PLACE_2 = "trans_place_2";
    private static final String KEY_USER_TRANS_PLACE_3 = "trans_place_3";
    private static final String KEY_USER_TRANS_PLACE_4 = "trans_place_4";
    private static final String KEY_USER_TRANS_PLACE_5 = "trans_place_5";

    private static final String KEY_USER_CURRENT_DIST = "current_dist";
    private static final String KEY_USER_DIST_TO_TRANSFER = "dist_to_transfer";

    private static final String KEY_USER_Q1 = "current_q1";
    private static final String KEY_USER_Q2= "current_q2";
    private static final String KEY_USER_Q3= "current_q3";

    private static final String KEY_USER_CURRENT_TALUKA = "current_taluka";
    private static final String KEY_USER_TALUKA_TO_TRANSFER = "taluka_to_transfer";

    private static final String KEY_USER_LEVEL = "level";
    private static final String KEY_USER_SUBJECT_IN_LEVEL= "subject_in_level";

    private static final String KEY_UNREAD_COUNT = "unreadcount";
    private static final String KEY_LAST_MESSAGE = "lastmessage";
    private static final String KEY_NOTIFICATIONS = "notifications";
    private static final String KEY_DATA_PRIVACY = "dataprivacy";
    private static final String KEY_TALUKA_SAVED = "talukasaved";


    private  static  final  String KEY_PHONE_NUMBER_SAVED="Phone_Number";



    // Constructor
    public MyPreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

 /*
    public void storeUser(User user)
    {
       editor.putString(KEY_USER_ID, user.getId());
        editor.putString(KEY_USER_NAME, user.getName());
        editor.putString(KEY_USER_EMAIL, user.getEmail());

        editor.putString(KEY_USER_DOB, user.getdob());
        editor.putString(KEY_USER_HOME_ADDRESS, user.gethome_Address());
        editor.putString(KEY_USER_PHONE, user.getphone_number());

        editor.putString(KEY_USER_CURRENT_PLACE, user.getCurrent_place());

        editor.putString(KEY_USER_TRANS_PLACE_1, user.getTransplace1());
        editor.putString(KEY_USER_TRANS_PLACE_2, user.getTransplace2());
        editor.putString(KEY_USER_TRANS_PLACE_3, user.getTransplace3());
        editor.putString(KEY_USER_TRANS_PLACE_4, user.getTransplace4());
        editor.putString(KEY_USER_TRANS_PLACE_5, user.getTransplace5());

        editor.putString(KEY_USER_CURRENT_DIST, user.getcurrent_dist());
        editor.putString(KEY_USER_DIST_TO_TRANSFER, user.getdist_to_transfer());

        editor.putString(KEY_USER_Q1, user.getq1());
        editor.putString(KEY_USER_Q2, user.getq2());
        editor.putString(KEY_USER_Q3, user.getq3());

        editor.putString(KEY_USER_CURRENT_TALUKA, user.gettaluka_current());
        editor.putString(KEY_USER_TALUKA_TO_TRANSFER, user.gettaluka_to_transfer());

        editor.putString(KEY_USER_LEVEL, user.getlevel());
        editor.putString(KEY_USER_SUBJECT_IN_LEVEL, user.getlevel_subject());

        editor.commit();

        Log.e(TAG, "User is stored in shared preferences. " + user.getName() + ", " + user.getEmail());
    }*/

    public  void storePhnumber(String Number){
        editor.putString(KEY_PHONE_NUMBER_SAVED,Number);
        editor.commit();
    }
    public String get_PhoneNumber()
    {
        if (pref.getString(KEY_PHONE_NUMBER_SAVED, "0") != "0") {
            String flag;
            flag = pref.getString(KEY_PHONE_NUMBER_SAVED, "0");
            return flag;
        }
        return "0";
    }
    public void storeFlage(String flag){
        editor.putString("flag",flag);
        editor.commit();
    }

    public void storeUserID(String Id) {
        editor.putString(KEY_USER_ID, Id);
        editor.commit();
    }

   /* public User getUser() {
        String id, name, email, dob, home_Address, phone_number, Current_place, Transplace1, Transplace2, Transplace3, Transplace4, Transplace5, sex, marrital_status,current_dist,dist_to_transfer,q1,q2,q3,current_taluka,taluka_to_transfer,level,subject_in_level;
        id = pref.getString(KEY_USER_ID, "0");
        name = pref.getString(KEY_USER_NAME, "");
        email = pref.getString(KEY_USER_EMAIL, "");

        dob = pref.getString(KEY_USER_DOB, "");
        home_Address = pref.getString(KEY_USER_HOME_ADDRESS, "");
        phone_number = pref.getString(KEY_USER_PHONE, "");

        Current_place = pref.getString(KEY_USER_CURRENT_PLACE, "");

        Transplace1 = pref.getString(KEY_USER_TRANS_PLACE_1, "");
        Transplace2 = pref.getString(KEY_USER_TRANS_PLACE_2, "");
        Transplace3 = pref.getString(KEY_USER_TRANS_PLACE_3, "");
        Transplace4 = pref.getString(KEY_USER_TRANS_PLACE_4, "");
        Transplace5 = pref.getString(KEY_USER_TRANS_PLACE_5, "");

        current_dist = pref.getString(KEY_USER_CURRENT_DIST, "0");
        dist_to_transfer = pref.getString(KEY_USER_DIST_TO_TRANSFER, "0");

        q1= pref.getString(KEY_USER_Q1, "");
        q2 = pref.getString(KEY_USER_Q2, "");
        q3 = pref.getString(KEY_USER_Q3, "");

        dist_to_transfer = pref.getString(KEY_USER_DIST_TO_TRANSFER, "0");

        q1= pref.getString(KEY_USER_Q1, "");
        q2 = pref.getString(KEY_USER_Q2, "");
        q3 = pref.getString(KEY_USER_Q3, "");

        current_taluka= pref.getString(KEY_USER_CURRENT_TALUKA, "0");
        taluka_to_transfer= pref.getString(KEY_USER_TALUKA_TO_TRANSFER, "0");
        level= pref.getString(KEY_USER_LEVEL, "0");
        subject_in_level= pref.getString(KEY_USER_SUBJECT_IN_LEVEL, "0");

        User user = new User(id, name, email, dob, home_Address, phone_number, Current_place, Transplace1, Transplace2, Transplace3, Transplace4, Transplace5, "SEX", "MARITAL",current_dist,dist_to_transfer,q1,q2,q3,current_taluka,taluka_to_transfer,level,subject_in_level);
        return user;
    }*/

    public void clear() {
        editor.clear();
        editor.commit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public void reset_unread_count(String roomid) {
        editor.putInt(KEY_UNREAD_COUNT + "_" + roomid, 0);
        editor.commit();
    }


    public int Increament_unread_count(String roomid)
    {
        int count;
        count = pref.getInt(KEY_UNREAD_COUNT + "_" + roomid, 0);
        count = count +1;
        store_unread_count(count,roomid);

        return count;

    }

    public void store_unread_count(int count,String roomid) {
        editor.putInt(KEY_UNREAD_COUNT + "_" + roomid, count);
        editor.commit();
    }

    public int get_unread_count(String roomid)
    {
        if (pref.getInt(KEY_UNREAD_COUNT + "_" + roomid, 0) != 0) {
            int count;
            count = pref.getInt(KEY_UNREAD_COUNT + "_" + roomid, 0);
            return count;
        }
        return 0;
    }

    public void store_data_privacy_flag(int flag) {
        editor.putInt(KEY_DATA_PRIVACY, flag);
        editor.commit();
    }

    public int get_data_privacy_flag()
    {
        if (pref.getInt(KEY_DATA_PRIVACY, 0) != 0) {
            int flag;
            flag = pref.getInt(KEY_DATA_PRIVACY, 0);
            return flag;
        }
        return 0;
    }

    /*GET SET For LAST MESSAGE*/
    public void store_last_message(String  Message,String roomid)
    {
        //Log.v("KEY_roomid[s]", "#" + KEY_LAST_MESSAGE + "_" + roomid);
        editor.putString(KEY_LAST_MESSAGE + "_" + roomid, Message);
        editor.commit();
    }

    public String get_last_message(String roomid)
    {
        if (pref.getString(KEY_LAST_MESSAGE + "_" + roomid, null) != null)
        {
            String Message= pref.getString(KEY_LAST_MESSAGE + "_" + roomid, null);
            // Log.v("KEY_roomid[g]", "#" + KEY_LAST_MESSAGE + "_" + roomid + " : " +Message);
            return Message;
        }
        return null;
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
    public void addNotification(String notification) {

        // get old notifications
        String oldNotifications = getNotifications();

        if (oldNotifications != null) {
            oldNotifications += "|" + notification;
        } else {
            oldNotifications = notification;
        }

        editor.putString(KEY_NOTIFICATIONS, oldNotifications);
        editor.commit();
    }

    public void store_takuka_flag(String flag) {
        editor.putString(KEY_TALUKA_SAVED, flag);
        editor.commit();
    }

    public String get_taluka_flag()
    {
        if (pref.getString(KEY_TALUKA_SAVED, "0") != "0") {
            String flag;
            flag = pref.getString(KEY_TALUKA_SAVED, "0");
            return flag;
        }
        return "0";
    }

    public String getNotifications() {
        return pref.getString(KEY_NOTIFICATIONS, null);
    }

}

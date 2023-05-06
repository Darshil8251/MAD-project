package com.example.milkmantra;

import android.content.Context;
import android.content.SharedPreferences;

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




    private  static  final  String KEY_PHONE_NUMBER_SAVED="Phone_Number";
    private  static  final  String KEY_FLAG_SAVED="Flag";

    private  static  final  String KEY_CUSTOMER_NAME_SAVED="Customer_Name";
    private  static  final  String KEY_CUSTOMER_PINCODE_SAVED="Customer_Pincode";
    private  static  final  String KEY_CUSTOMER_ADDRESS_SAVED="Customer_Address";
    private  static  final  String KEY_CUSTOMER_MOBILE_NUMBER_SAVED="Custo";

    private  static  final  String KEY_PROVIDER_ID_SAVED="Provider_Id";

    private  static  final  String KEY_PROVIDER_NAME_SAVED="Provider_Name";

    private  static  final  String KEY_PROVIDER_PHONE_NUMBER_SAVED="Provider_Phone_Number";

    private  static  final  String KEY_PROVIDER_PINCODE_SAVED="Provider_Pincode";

    private  static  final  String KEY_PROVIDER_ADDRESS_SAVED="Provider_Address";

    private  static  final  String KEY_PROVIDER_VACATION_MODE_SAVED="Provider_Vacation_Mode";

    private  static  final  String KEY_PROVIDER_QR_CODE_SAVED="Provider_Qr_Code";

    private  static  final  String KEY_PROVIDER_IS_ACTIVE_SAVED="Provider_Is_Active";

    private  static  final  String KEY_PROVIDER_TIME_STAMP_SAVED="Provider_Time_Stamp";

    private  static  final  String KEY_PROVIDER_REMARK_SAVED="Provider_Remark";

    private  static  final  String KEY_PROVIDER_RESERVE1_SAVED="Provider_Reserve1";

    private  static  final  String KEY_PROVIDER_RESERVE2_SAVED="Provider_Reserve2";

    private  static  final  String KEY_PROVIDER_RESERVE3_SAVED="Provider_Reserve3";




    // Constructor
    public MyPreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }



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
        editor.putString(KEY_FLAG_SAVED,flag);
        editor.commit();
    }

    public  String  get_flage(){
        return pref.getString(KEY_FLAG_SAVED,"0");
    }


    public  void storeCustomer(String Number,String Pincode,String Address,String Name) {
        editor.putString(KEY_CUSTOMER_MOBILE_NUMBER_SAVED, Number);
        editor.putString(KEY_CUSTOMER_NAME_SAVED, Name);
        editor.putString(KEY_CUSTOMER_PINCODE_SAVED, Pincode);
        editor.putString(KEY_CUSTOMER_ADDRESS_SAVED, Address);
        editor.commit();

    }


    public String get_Customer_Phone_Number(){
        return pref.getString(KEY_CUSTOMER_MOBILE_NUMBER_SAVED,"");
    }
    public String get_Customer_Name(){
        return pref.getString(KEY_CUSTOMER_NAME_SAVED,"");
    }

    public String get_Customer_Pincode(){
        return pref.getString(KEY_CUSTOMER_PINCODE_SAVED,"");
    }

    public String get_Customer_Address(){
        return pref.getString(KEY_CUSTOMER_ADDRESS_SAVED,"");
    }


    public void set_Provider_id(String provider_id){
        editor.putString(KEY_PROVIDER_ID_SAVED,provider_id);
        editor.commit();
    }

    public void set_Provider_Name(String provider_Name){
        editor.putString(KEY_PROVIDER_NAME_SAVED,provider_Name);
        editor.commit();
    }

    public  void  set_Provider_Phone(String provider_phone){
        editor.putString(KEY_PROVIDER_PHONE_NUMBER_SAVED,provider_phone);
        editor.commit();
    }

    public  void set_Provider_Pincode(String provider_pincode){
        editor.putString(KEY_PROVIDER_PINCODE_SAVED,provider_pincode);
        editor.commit();
    }

    public  void set_Provider_Address(String provider_address){
        editor.putString(KEY_PROVIDER_ADDRESS_SAVED,provider_address);
        editor.commit();
    }
    public  void set_Provider_Vacation_Mode(String provider_vacation_mode){
        editor.putString(KEY_PROVIDER_PINCODE_SAVED,provider_vacation_mode);
        editor.commit();
    }


    public  void set_Provider_Qr_Code(String provider_qr_code){
        editor.putString(KEY_PROVIDER_QR_CODE_SAVED,provider_qr_code);
        editor.commit();
    }

    public  void set_Provider_Is_Active(String provider_is_active){
        editor.putString(KEY_PROVIDER_IS_ACTIVE_SAVED,provider_is_active);
        editor.commit();
    }

    public  void set_Provider_Time_Stamp(String provider_time_stamp){
        editor.putString(KEY_PROVIDER_TIME_STAMP_SAVED,provider_time_stamp);
        editor.commit();
    }

    public  String get_Provider_Id(){
        return  pref.getString(KEY_PROVIDER_ID_SAVED,"");
    }

    public  String get_Provider_Name(){
        return  pref.getString(KEY_PROVIDER_NAME_SAVED,"");
    }

    public  String get_Provider_Phone_Number(){
        return  pref.getString(KEY_PROVIDER_PHONE_NUMBER_SAVED,"");
    }
    public  String get_Provider_Vacation_Mode(){
        return  pref.getString(KEY_PROVIDER_VACATION_MODE_SAVED,"");
    }

    public  String get_Provider_Address(){
        return  pref.getString(KEY_PROVIDER_ADDRESS_SAVED,"");
    }

    public  String get_Provider_Qr_Code(){
        return  pref.getString(KEY_PROVIDER_QR_CODE_SAVED,"");
    }

    public  String get_Provider_Is_Active(){
        return  pref.getString(KEY_PROVIDER_IS_ACTIVE_SAVED,"");
    }

    public  String get_Provider_Time_Stamp(){
        return  pref.getString(KEY_PROVIDER_TIME_STAMP_SAVED,"");
    }




























    public void clear() {
        editor.clear();
        editor.commit();
    }






}

package com.example.milkmantra.provider;

public class EndPoints {


    // It is for creating customer account
    public  static  final  String CREATE_CUSTOMER_SAVE="http://meteorrider.socialstuf.com/milkmantra/v1/index_v2.php/create_customer";

    // it fot getting provider Detail
    public static final String LATEST_VALUES_NEW = "http://meteorrider.socialstuf.com/milkmantra/v1/index_v2.php/get_provider_details";

    //it is for update customer providern association
    public static final String SAVE_PROVIDER ="http://meteorrider.socialstuf.com/milkmantra/v1/index_v2.php/update_customer_provider_association_with_milk_details";


    //it is for create provider account
    public static final String ADD_PROVIDER="http://meteorrider.socialstuf.com/milkmantra/v1/index_v2.php/create_provider";


    //it is for getting Customer Detail
    public  static  final  String CUSTOMER_PROVIDER_ASSOCIATION="http://meteorrider.socialstuf.com/milkmantra/v1/index_v2.php/get_customer_provider_association_with_milk_details";


    // it is for accept provider customer milk order
    public  static  final String PROVIDER_ACCEPT_ORDERED="http://meteorrider.socialstuf.com/milkmantra/v1/index_v2.php/create_transaction";

    // it is for User identification
    public static  final String USER_IDENTIFICATION="http://meteorrider.socialstuf.com/milkmantra/v1/index_v2.php/IsUserExsists";

}

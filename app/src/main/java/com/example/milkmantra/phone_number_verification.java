package com.example.milkmantra;

 public class phone_number_verification {

  static  String phone_verification(String no){

   String validateNo=no.trim();
   validateNo=validateNo.substring(0,10);
   if(validateNo.length()!=10){
    return  "Enter Number Is Not Correct";
   }
   else{
    return validateNo;
   }

  }

}

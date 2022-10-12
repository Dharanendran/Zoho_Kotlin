package contactApplication

import contactApplication.Address

data class Contact(var user_name : String, var user_mobileNo : String, var user_emailId : String)
{
    var address: Address? = null
    var isFavourite :Boolean = false
    init
    {
        SqliteOperation.readQuery()
    }
    constructor(userName : String,
                userMobileNo : String,
                userEmailId : String ,
                door_no : String ,
                street : String ,
                district : String ,
                pincode : String ) : this(userName,userMobileNo,userEmailId)
    {
        address = Address(door_no,street,district,pincode)
    }

}
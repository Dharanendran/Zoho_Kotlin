package contactApplication

data class Address(
    var door_no : String ,
    var street : String  ,
    var district :String ,
    var pin_code :String )
{

    companion object  // I want to get address details and also there is a chance that address value is null ,so we want to put this in companion object that should be in address class
    {
        fun getAddressDetails(addressObject: Address?): String
        {
            return addressObject?.let { "\nDoor No    : ${it.door_no}\nStreet     : ${it.street}\nDistrict   : ${it.district}\nPIN Code   : ${it.pin_code}" }
                ?: "Door No   : Nil\nStreet    : Nil\nDistrict  : Nil\nPIN Code  : Nil"
        }
    }
}
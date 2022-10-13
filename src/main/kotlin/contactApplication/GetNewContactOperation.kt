package contactApplication

object GetContact:AddressGetter(){

    fun newContact():Contact
    {
        var name:String ; var mobileNo : String ; var mailId:String

        lateinit var newContact :Contact

        print("\nName        : ").also{ name = InputGetter.getUserInput({ userInput -> userInput.toString() })as String }
        print("MobileNo    : ").also{ mobileNo = GetValidData.getValidNumber() }
        print("Email Id    : ").also{ mailId = GetValidData.getValidEmail() }


        println("\n-------> 1 To Want Add Address")
        println("-------> 2 To Won't Add Address\n")

        var choice:Int
        while(true) {
            choice = InputGetter.getUserInput({ userInput -> userInput!!.toInt() } )as Int
            if(choice in 1..2) break
            else println("------------------Enter The Value Within The Range----------------------")
        }


        when(choice)
        {
            1 -> getAddress().also{ newContact = Contact(name ,mobileNo,mailId,"0",doorNo,streetName, district , pinCode ) }
            2 -> newContact = Contact(name ,mobileNo,mailId)
        }

        return newContact
    }
}
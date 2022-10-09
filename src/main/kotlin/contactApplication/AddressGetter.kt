package contactApplication

open class AddressGetter{

    var doorNo:String="" ;   var streetName :String ="" ; var district:String = "" ; var pinCode:String = ""

    fun getAddress()
    {
        print("Door No     : ").also {doorNo = InputGetter.getUserInput({ userInput -> userInput.toString() })as String }
        print("Street      : ").also {streetName = InputGetter.getUserInput({ userInput -> userInput.toString() })as String }
        print("District    : ").also {district = InputGetter.getUserInput({ userInput -> userInput.toString() })as String }
        print("PIN Code    : ").also {pinCode = InputGetter.getUserInput({ userInput -> userInput!!.toString() },"Enter The Valid PIN Code : " )as String}

    }
}
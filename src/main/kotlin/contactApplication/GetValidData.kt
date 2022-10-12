package contactApplication

import java.util.regex.Pattern

class GetValidData {

    companion object
    {
        fun getValidNumber():String
        {
            var number = InputGetter.getUserInput({ userInput -> userInput.toString()} ) as String
            return if(!Pattern.matches("[a-zA-Z]+", number) && number.length in 7..13 && SqliteOperation.readQuery("SELECT * FROM WHERE phone=$number")?.let{println("The Number You Entered Is Already Exists")
                    false} != false )
                number
            else{
                print("Enter Valid Mobile No : ")
                getValidNumber()
            }

        }

        fun getValidEmail():String
        {
            var emailId = InputGetter.getUserInput({ userInput -> userInput.toString()} ) as String
            val emailString = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")

            return if(Pattern.compile(emailString).matcher(emailId).matches() && SqliteOperation.readQuery("SELECT * FROM WHERE phone=$emailId")?.let{println("The Email Id You Entered Is Already Exists")
                    false} != false )
                emailId
            else{
                print("Enter Valid Email Id : ")
                getValidEmail()
            }
        }

        fun getValidPinCode():String
        {
            var pinCode = InputGetter.getUserInput({ userInput -> userInput.toString()} ) as String
            val pinString = ("^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$")

            return if(Pattern.compile(pinString).matcher(pinCode).matches())
                pinCode
            else{
                print("Enter Valid PIN Code : ")
                getValidPinCode()
            }
        }
    }
}
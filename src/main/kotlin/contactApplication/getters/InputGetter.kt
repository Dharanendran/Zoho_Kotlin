package contactApplication.getters

object InputGetter
{
    fun getUserInput(func: (userInput: String?) -> Any , message:String ="Enter The Option : "):Any {
        while(true) {
            var userInput: String? = readLine()
            try{
                return func(userInput)
            } catch (obj: Exception) {
                println("\n ---------------- Enter Valid Input ! -------------------\n")
                print(message)
            }
        }
    }

}

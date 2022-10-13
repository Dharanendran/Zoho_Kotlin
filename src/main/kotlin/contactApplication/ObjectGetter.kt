package contactApplication

import java.util.*

object ObjectGetter:GetContactObject {


    override fun getObject(): Contact?
    {

        println()
        println("""Enter Any Detail Of The Contact  :

                  |--------> Press 1 To MobileNo
                  |--------> Press 2 To E-mailId
                  |--------> Press 3 To Search by Any Data
                  |--------> Press -1 T0 Exit""".trimMargin() )


        var choice: Int
        while (true)
        {
            print("\nEnter the Option : ").also {
                choice = InputGetter.getUserInput({ userInput -> userInput!!.toInt() }, "Enter Numbers Only") as Int
            }
            if (choice in 1..3 || choice == -1)
                break
            println("------------Enter Value Within The Given Option----------------")
        }

        when(choice){
            1 -> return getObjectFromContacts("phone" )
            2 -> return getObjectFromContacts("email" )
            3 -> return searchBy()
        }
        return null
    }



    private fun searchBy():Contact?
    {
        println()
        println("""---------> Press 1 Contains Element
                  |---------> Press 2 Not Contains Element""".trimMargin())

        var choice:Int
        while(true)
        {
            print("\nEnter the Option : ").also{ choice = InputGetter.getUserInput({ userInput -> userInput!!.toInt() } ,"Enter Valid Input ") as Int}
            if(choice in 1..2)
                break
            println("--------------------- Enter The Value From The Given Option -----------------")
        }

        fun getSearchObject(containState :Boolean):Contact?
        {
            var text:String
            val textContainsContacts = mutableListOf<Contact>()
            val textNotContainsContact= mutableListOf<Contact>()
            print("Enter the Text : ").also{text = (InputGetter.getUserInput({userInput -> userInput.toString()}) as String).lowercase().trim() }
            var contacts = SqliteOperation.readQuery()
            for(contact in contacts)
            {
                with(contact){
                    if(user_name.lowercase().contains(text) || user_mobileNo.lowercase().contains(text) || user_emailId.lowercase().contains(text) || (address?.door_no)?.lowercase()?.contains(text) == true || (address?.street)?.lowercase()?.contains(text) == true || (address?.district)?.lowercase()?.contains(text) == true || (address?.pin_code)?.lowercase()?.contains(text) == true)
                        textContainsContacts.add(this)
                    else
                        textNotContainsContact.add(this) }
            }

            var table = ViewContactOperation.viewFullContactTable(if(containState)textContainsContacts else textNotContainsContact)

            var mobileNo:String
            if(table == "")
                return getObject()
            println(table)
            println("\n------------------------Enter The Mobile Number From Above Table --------------------- ")
            return getObjectFromContacts("phone")
        }

        when(choice){
            1 -> getSearchObject(true)?.let { return it }?:run {
                println("\n--------------- Enter The Valid Mobile No ! --------------\n")
                getObject() }

            2 -> getSearchObject(false)?.let{ return it }?:run {
                println("\n--------------- Enter The Valid Mobile No !  --------------\n")
                getObject()}
        }
        return  null
    }


    private fun getObjectFromContacts(data: String): Contact? {

        var value: String
        print("Enter the $data : ").also {
            value = InputGetter.getUserInput({ userInput -> userInput.toString() }) as String
        }

        val obj = SqliteOperation.readQuery("SELECT * FROM contact WHERE $data = \"$value\" ")
        if(obj.isEmpty())
            return null
        return obj[0]

    }
}
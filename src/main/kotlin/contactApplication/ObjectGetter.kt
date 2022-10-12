package contactApplication

import java.util.*

object ObjectGetter:GetContactObject {

    private lateinit var contacts:MutableList<Contact>


    override fun getObject(contacts:MutableList<Contact>): Contact?
    {

        this.contacts = contacts
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
            1 -> return getObjectFromContacts({ contact, value -> contact.user_mobileNo == value},"Mobile No" )
            2 -> return getObjectFromContacts({ contact, value -> contact.user_emailId == value},"Email Id" )
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
                return getObject(contacts)
            println(table)
            println("\n------------------------Enter The Mobile Number From Above Table --------------------- ")
            return getObjectFromContacts({contact , value -> contact.user_mobileNo == value} ,"Mobile No")
        }

        when(choice){
            1 -> getSearchObject(true)?.let { return it }?:run {
                println("\n--------------- Enter The Valid Mobile No ! --------------\n")
                getObject(contacts) }

            2 -> getSearchObject(false)?.let{ return it }?:run {
                println("\n--------------- Enter The Valid Mobile No !  --------------\n")
                getObject(contacts)}
        }
        return  null
    }




    private fun getObjectFromContacts(action :( contact:Contact ,value:String ) -> Boolean , data:String):Contact?
    {
        var value:String
        print("Enter the $data : ").also{ value = InputGetter.getUserInput({userInput -> userInput.toString()}) as String }

        for(contact in contacts)
        {
            if (action(contact, value))
                return contact
        }
        return null
    }
}
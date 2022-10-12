package contactApplication

object ContactOperation
{

    fun viewContact():String?
    {
        println()
        println("""
                  |-------> 1 to View Full Contact Table
                  |-------> 2 to View( Name ,PhoneNo , EmailID) Alone
                  |-------> -1 to exit""".trimMargin() )
        println()

        var choice:Int
        while(true)
        {
            print("Enter The Option : ").also{ choice = InputGetter.getUserInput({ userInput -> userInput!!.toInt() }, "Enter Valid Number : ") as Int }
            if (choice in 1..2 || choice == -1)
                break
            println("Enter The Number Within The Given Value !")
        }
        val contacts = SqliteOperation.readQuery()
        when(choice)
        {
            1-> return ViewContactOperation.viewFullContactTable(contacts)
            2-> return ViewContactOperation.viewAnyOneData(contacts)
        }
        return null
    }

    //2nd block for the Add contact operation

    fun addContact()
    {

        SqliteOperation.insertionQuery(GetContact.newContact())
    }


    // 3rd block for delete contact  operation

    fun deleteContact()
    {
        val contacts = SqliteOperation.readQuery()
        DeleteContactOperation.deleteOperation(contacts)
    }

    //4th block for update contact operation

    fun editContact()
    {
        val contacts = SqliteOperation.readQuery()
        EditContactOperation.editOperation(contacts)
    }

    fun favourite()
    {
        val contacts = SqliteOperation.readQuery()
        FavouriteOperation.favouriteOptions(contacts)
    }


}
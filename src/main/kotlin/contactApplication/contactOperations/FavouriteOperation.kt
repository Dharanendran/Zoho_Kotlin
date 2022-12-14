package contactApplication.contactOperations

import contactApplication.*
import contactApplication.dataBase.SqliteOperation
import contactApplication.getObjectInterface.GetContactObject
import contactApplication.getters.InputGetter
import contactApplication.getters.ObjectGetter

object FavouriteOperation: GetContactObject by ObjectGetter {

    fun favouriteOptions(contacts:MutableList<Contact>)
    {
        if(contacts.isEmpty())
        {
            println("\n There Is No Contact In Contact List To Add Favourite")
            return
        }

        println()
        println("""--------> Press 1 To Add Favourite
                  |--------> Press 2 To Remove Favourite
                  |--------> Press 3 To View Favourite Contacts
                  |--------> Press -1 To Exit""".trimMargin())
        println()

        var choice:Int
        while(true)
        {
            print("Enter The Option : ").also{ choice = InputGetter.getUserInput({ userInput -> userInput!!.toInt() }) as Int }
            if(choice in 1..3 || choice == -1)
                break
            println("\n--------------------Enter The Value Within The Given Option !---------------\n")
        }

        when(choice)
        {
            1 -> addFavourite(contacts)
            2 -> removeFavourite(contacts)
            3 -> {
                val contacts = SqliteOperation.readQuery("SELECT * FROM contact WHERE favourite = 1")
                viewFavouriteContacts(contacts)
            }
            -1 -> return
        }
    }

    private fun addFavourite(contacts:MutableList<Contact>)
    {
        getObject()?.let{
            val contactObject = it
            if(it.isFavourite == "0")
                it.isFavourite = "1".also{
                    SqliteOperation.updateQuery(contactObject, contactObject.user_mobileNo, "1")
                    println("SuccessFully , The Contact Is Added To Favourite List !") }
            else
                println(" The Contact Is Already In Favourite List !")
        }

    }

    private fun removeFavourite(contacts:MutableList<Contact>)
    {
        getObject()?.let{
            val contactObject = it
            if(it.isFavourite=="1")
            {
                it.isFavourite = "0".also {
                    SqliteOperation.updateQuery(contactObject, contactObject.user_mobileNo, "0")
                    println("SuccessFully , The Contact Is Removed From Favourite List !")
                }
            }
            else
                println(" The Contact Is Already Not In Favourite List !")
        }
    }

    private fun viewFavouriteContacts(contacts:MutableList<Contact>)
    {
        if(contacts.isNotEmpty())
             println("\n-------------------------Favourite Contacts--------------------\n").also{ println(
                 ViewContactOperation.viewFullContactTable(contacts)
             )}
        else
            println("\nThere Is No Contacts Present In The Favourite List !")
    }

}
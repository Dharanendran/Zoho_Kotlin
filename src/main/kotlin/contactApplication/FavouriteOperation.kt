package contactApplication

object FavouriteOperation:GetContactObject by ObjectGetter{


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
            print("Enter The Option : ").also{ choice = InputGetter.getUserInput({ userInput -> userInput!!.toInt()}) as Int }
            if(choice in 1..3 || choice == -1)
                break
            println("\n--------------------Enter The Value Within The Given Option !---------------\n")
        }

        when(choice)
        {
            1 -> addFavourite(contacts)
            2 -> removeFavourite(contacts)
            3 -> viewFavouriteContacts(contacts)
            -1 -> return
        }
    }

    private fun addFavourite(contacts:MutableList<Contact>)
    {
        getObject(contacts)?.let{
            if(!it.isFavourite)
                it.isFavourite = true.also{ println("SuccessFully , The Contact Is Added To Favourite List !") }
            else
                println(" The Contact Is Already In Favourite List !")
        }

    }

    private fun removeFavourite(contacts:MutableList<Contact>)
    {
        getObject(contacts)?.let{
            if(it.isFavourite)
                it.isFavourite = false.also{ println("SuccessFully , The Contact Is Removed From Favourite List !") }
            else
                println(" The Contact Is Already Not In Favourite List !")
        }
    }

    private fun viewFavouriteContacts(contacts:MutableList<Contact>)
    {
        if(contacts.any { it.isFavourite })
             println("\n-------------------------Favourite Contacts--------------------\n").also{ println(ViewContactOperation.viewFullContactTable( contacts.filter { it.isFavourite } as MutableList<Contact> ))}
        else
            println("\nThere Is No Contacts Present In The Favourite List !")
    }

}
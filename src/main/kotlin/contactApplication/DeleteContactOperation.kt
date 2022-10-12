package contactApplication

object DeleteContactOperation:GetContactObject by ObjectGetter {

    lateinit var contacts: MutableList<Contact>

    fun deleteOperation(contacts: MutableList<Contact>)
    {
        if(contacts.isNotEmpty())
        {
            getObject()?.let {
                SqliteOperation.deletionQuery(it)
                println("\n-------------- Contact Is SuccessFully Get Deleted --------------")
            }
        }

        else println("\n There Is No Contact  In Contact List To Delete !")
    }

}
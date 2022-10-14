package contactApplication.contactOperations

import contactApplication.Contact
import contactApplication.getObjectInterface.GetContactObject
import contactApplication.getters.ObjectGetter
import contactApplication.dataBase.SqliteOperation

object DeleteContactOperation: GetContactObject by ObjectGetter {

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
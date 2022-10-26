package contactApplication.contactOperations

import contactApplication.*
import contactApplication.dataBase.SqliteOperation
import contactApplication.getObjectInterface.GetContactObject
import contactApplication.getters.AddressGetter
import contactApplication.getters.InputGetter
import contactApplication.getters.ObjectGetter
import contactApplication.validation.GetValidData
import javax.sound.sampled.DataLine.Info

object EditContactOperation: GetContactObject by ObjectGetter, AddressGetter()
{

    private lateinit var editContact: Contact
    enum class Entity{ NAME , NUMBER , MAILID , DOORNO , STREET , PINCODE , DISTRICT }
    fun  editOperation(contacts: MutableList<Contact>)
    {

        if(contacts.isNotEmpty()) { getObject()?.let { editContact = it }?:println("The Number You Entered Is Not Present In The List !").also{return}}
        else{
            println("------------- There Is No Contact In Contact List To Edit ! ------------")
            return
        }

        println()
        println("""Enter The Detail You Want To Edit 
                       
                       |--------> Press 1 To Edit Name
                       |--------> Press 2 To Edit Mobile Number
                       |--------> Press 3 To Edit Email Id
                       |--------> Press 4 To Edit Address
                       |--------> Press -1 To Exit """.trimMargin())

        var choice: Int
        while (true)
        {
            print("\nEnter The Option :").also {choice = InputGetter.getUserInput({ userInput -> userInput!!.toInt() }) as Int }
            if (choice in 1..4 || choice == -1)
                break
            println("-------------------Enter The Value Within The Range -----------------\n")
        }


        fun detailsEditor(editor: (contact: Contact, newValue: String) -> Unit, info:Entity)
        {
            var newValue: String
            print("Enter the New $info : ").also {
                newValue = when(info)
                            {
                             Entity.NUMBER -> GetValidData.getValidNumber()
                             Entity.MAILID -> GetValidData.getValidEmail()
                             Entity.PINCODE -> GetValidData.getValidPinCode()
                             else -> InputGetter.getUserInput({ userInput -> userInput!!.toInt() }) as String
                            }
            }
            val primaryKey = editContact.user_mobileNo
            editContact.let { editor(it, newValue).also{
                SqliteOperation.updateQuery(editContact, primaryKey)
            } }
        }


        fun addressDetailsEditor() {

            editContact.address?.let {
                println()
                println("""--------> Press 1 To Edit Door NO 
                          |--------> Press 2 To Edit Street Name
                          |--------> Press 3 To Edit District
                          |--------> Press 4 To Edit PIN Code
                          |--------> Press -1 To Exit""".trimMargin()
                )

                var userInput: Int
                while (true) {
                    print("Enter The Option :").also {
                        userInput = InputGetter.getUserInput({ userInput -> userInput!!.toInt() }) as Int
                    }
                    if (choice in 1..4 || choice == -1)
                        break
                    println("-------------------Enter The Value Within The Range -----------------\n")
                }

                when (userInput) {
                    1 -> detailsEditor({ contact, newValue -> contact.address?.door_no = newValue }, Entity.DOORNO)
                    2 -> detailsEditor({ contact, newValue -> contact.address?.street = newValue }, Entity.STREET)
                    3 -> detailsEditor({ contact, newValue -> contact.address?.district = newValue }, Entity.DISTRICT)
                    4 -> detailsEditor({ contact, newValue -> contact.address?.pin_code = newValue }, Entity.PINCODE)
                }
            } ?: run {

                println("\nThere Is No Address To Edit !\n")
                println("""-------> Press 1 To Add Address Details
                          |-------> Press -1 To Exit""".trimMargin())
                println()

                var userInput: Int
                while (true)
                {
                    print("Enter The Option :").also {userInput = InputGetter.getUserInput({ userInput -> userInput!!.toInt() }) as Int }
                    if (userInput == 1 || userInput == -1)
                        break
                    println("\n-------------------Don't Enter The Value Other Than 1-----------------\n")
                }

                when (userInput)
                {
                    1 -> getAddress().also{
                        editContact.address = Address(doorNo,streetName,district,pinCode)
                        editContact.let { it1 -> SqliteOperation.updateQuery(it1, editContact.user_mobileNo) }
                    }
                }

            }

        }

        when (choice) {
            1 -> detailsEditor({ contact, newValue -> contact.user_name = newValue },Entity.NAME)
            2 -> detailsEditor({ contact, newValue -> contact.user_mobileNo = newValue },Entity.NUMBER)
            3 -> detailsEditor({ contact, newValue -> contact.user_emailId = newValue }, Entity.MAILID)
            4 -> addressDetailsEditor()
        }
    }
}
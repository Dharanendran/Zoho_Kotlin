package contactApplication.contactOperations

import contactApplication.*
import contactApplication.dataBase.SqliteOperation
import contactApplication.getObjectInterface.GetContactObject
import contactApplication.getters.AddressGetter
import contactApplication.getters.InputGetter
import contactApplication.getters.ObjectGetter
import contactApplication.validation.GetValidData

object EditContactOperation: GetContactObject by ObjectGetter, AddressGetter()
{
    fun  editOperation(contacts: MutableList<Contact>)
    {
        var editContact: Contact? = null

        if(contacts.isNotEmpty()) { getObject()?.let { editContact = it }}
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


        fun detailsEditor(editor: (contact: Contact, newValue: String) -> Unit, message: String)
        {
            var newValue: String
            print("Enter the New $message : ").also {
                newValue = when(message)
                            {
                                "Mobile Number" -> GetValidData.getValidNumber()
                                "Mail Id"       -> GetValidData.getValidEmail()
                                "PIN Code"      -> GetValidData.getValidPinCode()
                                 else           -> InputGetter.getUserInput({ userInput -> userInput!!.toString() }) as String
                            }

            }
            var primaryKey = editContact?.user_mobileNo
            editContact?.let { editor(it, newValue).also{
                if (primaryKey != null) {
                    SqliteOperation.updateQuery(editContact!!, primaryKey)
                }
            } }
        }


        fun addressDetailsEditor() {

            editContact?.address?.let {
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
                    1 -> detailsEditor({ contact, newValue -> contact.address?.door_no = newValue }, "Door No")
                    2 -> detailsEditor({ contact, newValue -> contact.address?.street = newValue }, "Street ")
                    3 -> detailsEditor({ contact, newValue -> contact.address?.district = newValue }, "District Name")
                    4 -> detailsEditor({ contact, newValue -> contact.address?.pin_code = newValue }, "PIN Code")
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
                        editContact?.address = Address(doorNo,streetName,district,pinCode)
                        editContact?.let { it1 -> SqliteOperation.updateQuery(it1, editContact!!.user_mobileNo) }
                    }
                }

            }

        }

        when (choice) {
            1 -> detailsEditor({ contact, newValue -> contact.user_name = newValue }, "Name")
            2 -> detailsEditor({ contact, newValue -> contact.user_mobileNo = newValue }, "Mobile Number")
            3 -> detailsEditor({ contact, newValue -> contact.user_emailId = newValue }, "Mail Id")
            4 -> addressDetailsEditor()
        }
    }
}
package contactApplication

object ViewContactOperation{


    fun viewFullContactTable(contacts : MutableList<Contact> ): String
    {
        if(contacts.isEmpty())
            return println("\n==========================There Is Not Contact To Show ! Please Add Contact ---------------------------").let{return ""}

        var contactHead =
            " _________________________________________________________________________________________\n"+
                    "|S.NO|         NAME            |           MAIL ID               |        Number          |\n"+
                    "|____|_________________________|_________________________________|________________________|"

        var contactBody =""
        for (i in 1 until contacts.size + 1)
        {
            contactBody+=
                java.lang.String.format(
                    "\n| %s  |   %-22s|  %-30s |    %-19s |",
                    i,
                    if (contacts[i - 1].user_name.length > 22)
                        contacts[i-1].user_name.subSequence(0, 17).toString() + "." + "." + "." + "."
                    else contacts[i -1].user_name ,

                    if (contacts[i - 1].user_emailId.length > 30)
                        contacts[i - 1].user_emailId.subSequence(0, 24).toString() + "." + "." + "." + "."
                    else contacts[i - 1].user_emailId ,

                    contacts[i - 1].user_mobileNo
                )
            contactBody+="\n|____|_________________________|_________________________________|________________________|"

        }
        var addressHead=
            "\n _________________________________________________________________________________________"+
            "\n|                                                                                         |"+
            "\n|                                    ADDRESS DETAILS                                      |"+
            "\n|_________________________________________________________________________________________|"+
            "\n|S.NO|      Door No            |         Street Name             |        pin code        |"+
            "\n|____|_________________________|_________________________________|________________________|"

        var addressBody =""
        for (i in 1 until contacts.size + 1) {
            addressBody+=
                java.lang.String.format(
                    "\n| %s  |        %-17s|      %-26s |        %-15s |", i,
                    contacts[i - 1].address?.let{it.door_no}?:"-",
                    contacts[i - 1].address?.let{it.street}?:"-",
                    contacts[i - 1].address?.let{it.pin_code}?:"-",
                )
            addressBody+=
                "\n|____|_________________________|_________________________________|________________________|"

        }
        return contactHead + contactBody + addressHead + addressBody

    }

    fun viewAnyOneData(contacts: MutableList<Contact>): String?
    {
        println()
        println(
            """---------> Press 1 To View Names
                  |---------> Press 2 To View Mobile Numbers
                  |---------> Press 3 To View Mail Id
                  |---------> Press 4 To View Door No
                  |---------> Press 5 To view District
                  |---------> Press 6 To View PIN Code
                  |---------> Press -1 To  Exit""".trimMargin()
        )
        println()

        var choice: Int
        while (true) {
            print("Enter The Choice : ").also {
                choice = InputGetter.getUserInput(
                    { userInput -> userInput!!.toInt() },
                    "Enter The Valid Number :"
                ) as Int
            }
            if (choice in 1..6 || choice == -1) break
            println("----------------------Enter The Number Within The Given Value----------------------")
        }

        fun anyOneDataViewer(getData: (contact: Contact) -> String, head: String): String {
            var data = ""
            for (i in 1..contacts.size)
                data += "  $i . ${getData(contacts[i - 1])} \n"
            data = if (data == "") "\n CONTACT LIST IS EMPTY ...! PLEASE ADD CONTACT " else data
            return head + data
        }

        when (choice) {

            1 -> return anyOneDataViewer({ contact -> contact.user_name }, "\n-------------ALL  NAMES----------------\n")

            2 -> return anyOneDataViewer( { contact -> contact.user_mobileNo },"\n-------------ALL Mobile Numbers-------------\n")

            3 -> return anyOneDataViewer({ contact -> contact.user_emailId },"\n-------------ALL MAIL ID---------------------\n")

            4 -> return anyOneDataViewer({ contact -> contact.address?.door_no ?: "Nil" },"\n-------------ALL Door No---------------------\n")

            5 -> return anyOneDataViewer({ contact -> contact.address?.district ?: "Nil" },"\n-------------ALL Districts---------------------\n")

            6 -> return anyOneDataViewer({ contact -> contact.address?.pin_code?.toString() ?:"Nil" },"\n-------------ALL PIN Codes---------------------\n")
        }
        return null
    }


}
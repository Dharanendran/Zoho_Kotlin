package contactApplication.main

import contactApplication.ContactOperation
import contactApplication.getters.InputGetter

object InitialView{

    fun viewOption()
    {

        println("       ZOHO CONTACTS        ")

        var choice: Int = 0

        fun showOptions()
        {
            println()
            println("""               
               |  ------>  1 TO VIEW CONTACT
               |  ------>  2 TO ADD CONTACT
               |  ------>  3 TO EDIT CONTACT
               |  ------>  4 TO DELETE CONTACT
               |  ------>  5 To FAVOURITE CONTACT
               |  ------> -1 TO EXIT         """.trimMargin())
            println()
            print("Enter The Option : ")
        }

        while(choice!=-1)
        {
            showOptions()
            while (true) {
                choice = InputGetter.getUserInput({ userInput -> userInput!!.toInt() },"Enter the Option :") as Int
                if (choice !in 1..5 && choice != -1) {
                    println("\n------------------- Please Enter Value From The Given Option-----------------\n")
                    print("Enter The Option : ")
                } else break
            }

            when(choice)
            {
                1 -> ContactOperation.viewContact()?.let{println(it)}
                2 -> ContactOperation.addContact().also{println("\n----------- Contact Added SuccessFully ----------------")}
                3 -> ContactOperation.editContact()
                4 -> ContactOperation.deleteContact()
                5 -> ContactOperation.favourite()
                -1 ->println("\n       Thanks For Using Zoho Contacts !     ")
            }

        }

    }
}
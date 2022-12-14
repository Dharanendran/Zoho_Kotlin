package contactApplication.dataBase

import contactApplication.Contact


class SqliteOperation {
    companion object {

        fun insertionQuery(contact: Contact) {
            val stmt1 =
                contact.let { "INSERT INTO CONTACT(name,email,phone) VALUES (\"${it.user_name}\",\"${it.user_emailId}\",\"${it.user_mobileNo}\")" }
            val stmt2 =
                contact.address?.let { "INSERT INTO CONTACT(name,email,phone,doorNo,street,district,pincode) VALUES (\"${contact.user_name}\",\"${contact.user_emailId}\",\"${contact.user_mobileNo}\",\"${it.door_no}\",\"${it.street}\",\"${it.district}\",\"${it.pin_code}\")" }
            SQLiteConnector.statement = SQLiteConnector.connection.createStatement()
            SQLiteConnector.statement.executeUpdate(contact.address?.let { stmt2 } ?: stmt1)
        }


        fun deletionQuery(contact: Contact) {
            val stmt = "DELETE FROM CONTACT WHERE phone =\"${contact.user_mobileNo}\""
            SQLiteConnector.statement = SQLiteConnector.connection.createStatement()
            SQLiteConnector.statement.executeUpdate(stmt)
        }


        fun updateQuery(contact: Contact, key: String, fav: String = "") {
            var stmt: String
            if (fav == "")
            {
                stmt =contact.let { "name=\"${it.user_name}\",email=\"${it.user_emailId}\",phone=\"${it.user_mobileNo}\"" }
                if (contact.address != null)
                    stmt += contact.address?.let { "," +
                            "doorNo =\"${it.door_no}\",street=\"${it.street}\",district=\"${it.district}\",pincode=\"${it.pin_code}\" " }
            }
            else
            {
                stmt = "favourite =$fav"
            }
            SQLiteConnector.statement.executeUpdate("UPDATE contact SET $stmt WHERE phone=\"${key}\"")
            println("Contact Is Updated SuccessFully !")
        }


        fun readQuery(stmt: String = "SELECT * FROM contact"): MutableList<Contact> {
            val result = SQLiteConnector.statement.executeQuery(stmt)
            val contacts = mutableListOf<Contact>()
            while (result.next()) {
                val name = result.getString("name")
                val email = result.getString("email")
                val phone = result.getString("phone")
                val doorNo = result.getString("doorNo")
                val street = result.getString("street")
                val district = result.getString("district")
                val pincode = result.getString("pincode")
                val favourite = result.getString("favourite")

                if (doorNo == null)
                    contacts.add(Contact(name, phone, email,favourite?:"0"))
                else
                    contacts.add(Contact(name, phone, email,favourite?:"0", doorNo, street, district, pincode))
            }
            return contacts
        }

    }
}

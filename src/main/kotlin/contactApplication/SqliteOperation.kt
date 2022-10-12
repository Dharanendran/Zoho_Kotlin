package contactApplication

import sqlitePractise.SqlConnection

class SqliteOperation
{
    companion object
    {

        fun insertionQuery(contact: contactApplication.Contact)
        {
            var stmt1 = "INSERT INTO CONTACT(name,email,phone) VALUES (\"${contact.user_name}\",\"${contact.user_emailId}\",\"${contact.user_mobileNo}\")"
            var stmt2 = contact.address?.let{"INSERT INTO CONTACT(doorNo,street,district,pincode) VALUES (\"${it.door_no}\",\"${it.street}\",\"${it.district}\",\"${it.pin_code}\")"}?:""
            SqlConnection.statement = SqlConnection.connection.createStatement()
            SqlConnection.statement.executeUpdate(stmt1+stmt2)
        }


        fun deletionQuery(data:String ,value:String)
        {
            var stmt = "DELETE FROM CONTACT WHERE $data =$value"
            SqlConnection.statement.executeUpdate(stmt).also { println("Contact Deleted Successfully !") }
        }


        fun updateQuery(set :String , value :String ,primaryKey:String,primaryKeyData:String,stmt:String = set+value)
        {
            var stmt = "UPDATE CONTACT SET $stmt WHERE $primaryKey =\"$primaryKeyData\""
            SqlConnection.statement.executeUpdate(stmt)
            println("Contact Is Updated SuccessFully !")
        }


        fun readQuery(stmt:String ="SELECT * FROM CONTACT"):MutableList<MutableMap<String,String>>
        {
            var resultArr = mutableListOf<MutableMap<String,String>>()
            val result = SqlConnection.statement.executeQuery(stmt)
            var data = mutableMapOf<String,String>()

            while (result.next())
            {
                data["name"] = result.getString("name")
                data["email"] = result.getString("email")
                data["phone"] = result.getString("phone")
                data["doorNo"] = result.getString("doorNo")?:""
                data["street"] = result.getString("street")?:""
                data["district"] = result.getString("district")?:""
                data["pincode"] = result.getString("pincode")?:""

                resultArr.add(data.toMutableMap())
                data.clear()
            }
            return resultArr
        }

    }
}
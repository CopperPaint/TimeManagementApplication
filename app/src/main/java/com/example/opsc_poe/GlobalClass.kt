package com.example.opsc_poe

import android.app.AlertDialog
import android.app.Application
import android.content.Context



class GlobalClass : Application()
{

    companion object {


        var activities = arrayListOf<Temp_ActivityDataClass>()
        var categories = arrayListOf<Temp_CategoryDataClass>()
        var goals = arrayListOf<Temp_GoalDataClass>()
        var logs = arrayListOf<Temp_LogDataClass>()
        var user = Temp_UserDataClass()


        fun InformUser(messageTitle: String, messageText: String, context: Context) {
            val alert = AlertDialog.Builder(context)
            alert.setTitle(messageTitle)
            alert.setMessage(messageText)
            alert.setPositiveButton("OK", null)
            alert.show()
        }

        //---------------------------------------------------------------------------------------------------------------------------------------------
        //temporary lists of registered users and their data
        //---------------------------------------------------------------------------------------------------------------------------------------------
        var listUserUserID = arrayListOf(1, 2, 3, 4)
        var listUserEmail = arrayListOf(
            "jake@turtletime.com",
            "jo@turtletime.com",
            "benji@turtletime.com",
            "marker@turtletime.com"
        )
        var listUserUsername = arrayListOf("Jake", "Jo", "Benji", "Mark", "jrtu")
        var listUserPasswordHash = arrayListOf(
            "cb75e237e40ab1e5f8481fb332076d0969cbf9c643d77133e16303730e501858",
            "53866f8bddeafb655d5f5a86599dafbec8c8eaefcd405d25952b957280e889f7",
            "4ba8e3bb0aef5e38db7f6e6dfbeefbd95af8e65035b69efe6e200d929c898b29",
            "434ce1bd62754f195bb35d9beadfd36add5956be76ff000cee6a4225497a93b0"
        )
        var listUserPasswordSalt = arrayListOf("aecd1edc24a0671710d7e92c85342454", "ab966010d04ea07efec8d7fe358ea4fb", "12b97f16dfa05f21f49169333685a243", "38780ed5ef02ad051cc85545302661e2")

        //user account email is first name + @turtletime.com
        //user account password is surname

        //marker email = marker@turtletime.com
        //marker password = marker
        //---------------------------------------------------------------------------------------------------------------------------------------------



    }

    override fun onCreate() {
        super.onCreate()
    }



}
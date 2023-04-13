package com.system

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.system.utilities.Constants
import io.realm.kotlin.internal.platform.runBlocking
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials

class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        val email = "purabmodi1991@gmail.com"
        val password = "Purab123"
        val app: App = App.create(Constants.APP_ID) // Replace this with your App ID
//        var user? = null
        runBlocking { // use runBlocking sparingly -- it can delay UI interactions
            val user = app.login(Credentials.emailPassword(email, password))
            if(user.loggedIn){
                startActivity(Intent(applicationContext,MainActivity::class.java))
            }
        }

    }
}
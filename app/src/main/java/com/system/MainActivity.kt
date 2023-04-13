package com.system

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.system.data.model.Order
import com.system.databinding.ActivityMainBinding
import com.system.ui.RealmViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<RealmViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottomNavBar)
        setupWithNavController(bottomNavBar,navController)

//
//        val email = "purabmodi1991@gmail.com"
//        val password = "Purab123"
//
////        val app: App = App.create(Constants.APP_ID)
////        runBlocking {
////            app.emailPasswordAuth.registerUser(email, password)
////            // once registered, you can log in with the user credentials
////            val user = app.login(Credentials.emailPassword(email, password))
////            Log.v("CHECKINGUSER","Successfully logged in ${user.id}")
////        }
//        val app: App = App.create(Constants.APP_ID) // Replace this with your App ID
////        runBlocking { // use runBlocking sparingly -- it can delay UI interactions
////            val user = app.login(Credentials.emailPassword(email, password))
////        }

//        viewModel.data.observe(this){
//            it.forEach {order->
//                Log.d("ORDERNAMES", "onCreate: ${order.name}")
//            }
//        }
//
//        binding.submitBtn.setOnClickListener{
//            if(binding.orderName.text.isNotBlank()){
//                viewModel.insertOrder(order = Order().apply {
//                    name = binding.orderName.text.toString()
//                })
//            }
//        }




    }
}
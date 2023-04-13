package com.system.data.repository

import android.util.Log
import com.system.data.model.Order
import com.system.utilities.Constants
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object MongoRepository {

//    Code for offfline database
//    fun getData(): Flow<List<Order>> {
//        return realm.query<Order>().asFlow().map { it.list }
//    }
//
    suspend fun insertOrder(order: Order) {
        if(currentUser!=null){
            Log.d("ORDERNAMES", "insertOrder: not null")
            realm.write {
                try {
                    copyToRealm(order.apply { customerId = currentUser.id })
                }catch (e:java.lang.Exception){
                    Log.d("ORDERNAMES", "insertOrder: $e")
                }
            }
        }
    }

//    suspend fun updateOrder(order: Order) {
//        realm.write {
//            val selectedOrder = query<Order>(query = "_id == $0", order._id).first().find()
//          Query for delete option
//              selectedOrder?.let{ delete(it) }
//        }
//    }

    private val app = App.Companion.create(Constants.APP_ID)
    private val currentUser = app.currentUser
    private lateinit var realm: Realm

    init {
        configureTheRealm()
    }

    private fun configureTheRealm() {
        if (currentUser != null) {
            Log.d("ORDERNAMES", "Hey i am inside")
            val config = SyncConfiguration.Builder(
                currentUser,
                setOf(Order::class)
            )
//                    code to authenticate with the customer id for future work
                .initialSubscriptions{sub->
                    add(query = sub.query<Order>(query = "customerId==$0", currentUser.id))
                }
                .log(LogLevel.ALL)
                .build()
            realm = Realm.open(config)
        }
    }
    fun getData(): Flow<List<Order>> {
        return realm.query<Order>().asFlow().map { it.list }
    }


}
package com.system.data.repository

import com.system.data.model.Order
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MongoRepository(val realm: Realm) {

    fun getData(): Flow<List<Order>> {
        return realm.query<Order>().asFlow().map { it.list }
    }

    suspend fun insertOrder(order: Order) {
        realm.write { copyToRealm(order) }
    }

//    suspend fun updateOrder(order: Order) {
//        realm.write {
//            val selectedOrder = query<Order>(query = "_id == $0", order._id).first().find()
//          Query for delete option
//              selectedOrder?.let{ delete(it) }
//        }
//    }

}
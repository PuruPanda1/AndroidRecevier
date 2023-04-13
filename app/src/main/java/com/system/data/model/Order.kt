package com.system.data.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Order : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var customerId:String = ""
    var name: String = ""
    var price: Int = 0
    var qunatity: Int = 0
}
package jp.ac.itcollege.s23015.memo_recovery.infrastructure.database.dao

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UserEntity>(UserTable)

    var email by UserTable.email
    var password by UserTable.password
    var name by UserTable.name
    var roleType by UserTable.roletype
}
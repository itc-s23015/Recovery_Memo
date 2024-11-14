package jp.ac.itcollege.s23015.memo_recovery.infrastructure.database.dao

import jp.ac.itcollege.s23015.memo_recovery.domain.types.RoleType
import org.jetbrains.exposed.dao.id.LongIdTable

object UserTable : LongIdTable("user") {
    val email = varchar("email", 256).uniqueIndex()
    val password = varchar("password", 128)
    val name = varchar("name", 32)
    val roletype = enumerationByName("role_type", 5, RoleType::class)
}
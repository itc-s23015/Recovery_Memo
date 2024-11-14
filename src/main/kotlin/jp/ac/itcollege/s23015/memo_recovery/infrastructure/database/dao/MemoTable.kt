package jp.ac.itcollege.s23015.memo_recovery.infrastructure.database.dao

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime


object MemoTable : LongIdTable("memo") {
    val content = varchar("content", 512)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at").nullable()
}
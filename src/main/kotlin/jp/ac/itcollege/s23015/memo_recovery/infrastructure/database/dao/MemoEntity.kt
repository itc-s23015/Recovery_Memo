package jp.ac.itcollege.s23015.memo_recovery.infrastructure.database.dao

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class MemoEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<MemoEntity>(MemoTable)

    var content by MemoTable.content
    var createdDate by MemoTable.createdAt
    var updatedAt by MemoTable.updatedAt

    override fun toString() = """
        MemoEntity(id=$id, content=$content, createdAt=$createdDate, updatedAt=$updatedAt)
    """.trimIndent()
}
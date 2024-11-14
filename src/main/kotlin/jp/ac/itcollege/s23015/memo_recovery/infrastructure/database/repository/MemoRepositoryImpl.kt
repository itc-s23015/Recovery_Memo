package jp.ac.itcollege.s23015.memo_recovery.infrastructure.database.repository

import jp.ac.itcollege.s23015.memo_recovery.domain.model.Memo
import jp.ac.itcollege.s23015.memo_recovery.domain.repository.MemoRepository
import jp.ac.itcollege.s23015.memo_recovery.infrastructure.database.dao.MemoEntity
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository



@Repository
class MemoRepositoryImpl : MemoRepository {
    override fun findAll(): List<Memo> {
        return transaction {
            MemoEntity.all().map(::toModel)
        }
    }

    override fun find(id: Long): Memo? {
        return transaction {
            MemoEntity.findById(id)?.let(::toModel)
        }
    }

    override fun add(memo: Memo) {
        transaction {
            MemoEntity.new(memo.id) {
                content = memo.content
                createdDate = memo.createdAt
                updatedAt = memo.createdAt
            }
        }
    }

    override fun update(id: Long, content: String?) {
        transaction {
            MemoEntity.findById(id)?.apply {
                content?.let { this.content = it }
                updatedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            }
        }
    }

    override fun delete(id: Long) {
        transaction {
            MemoEntity.findById(id)?.delete()
        }
    }

    private fun toModel(entity: MemoEntity): Memo {
        return Memo(
            id = entity.id.value,
            content = entity.content,
            createdAt = entity.createdDate
        )
    }
}
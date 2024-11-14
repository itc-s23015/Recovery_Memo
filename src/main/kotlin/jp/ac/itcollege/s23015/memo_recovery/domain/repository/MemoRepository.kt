package jp.ac.itcollege.s23015.memo_recovery.domain.repository

import jp.ac.itcollege.s23015.memo_recovery.domain.model.Memo

interface MemoRepository {
    fun findAll(): List<Memo>
    fun find(id: Long): Memo?
    fun add(memo: Memo)
    fun update(id: Long, content: String?)
    fun delete(id: Long)
}
package jp.ac.itcollege.s23015.memo_recovery.service

import jp.ac.itcollege.s23015.memo_recovery.domain.model.Memo
import jp.ac.itcollege.s23015.memo_recovery.domain.repository.MemoRepository
import jp.ac.itcollege.s23015.memo_recovery.presentation.exception.MemoNotFoundException
import org.springframework.stereotype.Service

@Service
class MemoService (
    private val memoRepository: MemoRepository
) {
    fun getList(): List<Memo> {
        return memoRepository.findAll()
    }

    fun getDetail(memoId: Long): Memo {
        return memoRepository.find(memoId)
            ?: throw MemoNotFoundException("存在しないメモID: $memoId")
    }
}
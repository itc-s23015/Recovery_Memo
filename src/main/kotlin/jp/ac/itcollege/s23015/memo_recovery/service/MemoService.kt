package jp.ac.itcollege.s23015.memo_recovery.service

import jp.ac.itcollege.s23015.memo_recovery.domain.model.Memo
import jp.ac.itcollege.s23015.memo_recovery.domain.repository.MemoRepository
import jp.ac.itcollege.s23015.memo_recovery.presentation.exception.MemoIdAlreadyExistsException
import jp.ac.itcollege.s23015.memo_recovery.presentation.exception.MemoNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

    @Transactional
    fun add(memo: Memo) {
        memoRepository.find(memo.id)?.let {
            throw MemoIdAlreadyExistsException("既に存在するメモID: ${memo.id}")
        }
        memoRepository.add(memo)
    }

    @Transactional
    fun update(id: Long, content: String?) {
        memoRepository.find(id) ?: throw MemoNotFoundException("存在しないメモID: $id")
        memoRepository.update(id, content)
    }

    @Transactional
    fun delete(memoId: Long) {
        memoRepository.find(memoId) ?: throw MemoNotFoundException("存在しないメモID: $memoId")
        memoRepository.delete(memoId)
    }

}
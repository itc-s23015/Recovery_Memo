package jp.ac.itcollege.s23015.memo_recovery.presentation.controller

import jp.ac.itcollege.s23015.memo_recovery.presentation.form.GetMemoDetailResponse
import jp.ac.itcollege.s23015.memo_recovery.presentation.form.GetMemoListResponse
import jp.ac.itcollege.s23015.memo_recovery.presentation.form.MemoInfo
import jp.ac.itcollege.s23015.memo_recovery.service.MemoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("memo")
class MemoController (
    private val memoService: MemoService,
) {
    @GetMapping("/list")
    fun getList(): GetMemoListResponse {
        val memoList = memoService.getList().map { memo ->
            MemoInfo(
                id = memo.id,
                content = memo.content,
                createdAt = memo.createdAt,
            )
        }
        return GetMemoListResponse(memoList)
    }

    @GetMapping("/detail/{memo_id}")
    fun getDetail(@PathVariable("memo_id") memoId: Long): GetMemoDetailResponse {
        val memo = memoService.getDetail(memoId)
        return GetMemoDetailResponse(
            id = memo.id,
            content = memo.content,
            createdDate = memo.createdAt
        )
    }
}
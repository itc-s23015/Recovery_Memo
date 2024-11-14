package jp.ac.itcollege.s23015.memo_recovery.presentation.controller

import jp.ac.itcollege.s23015.memo_recovery.domain.model.Memo
import jp.ac.itcollege.s23015.memo_recovery.presentation.form.*
import jp.ac.itcollege.s23015.memo_recovery.service.MemoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("memo")
class MemoController (
    private val memoService: MemoService,
) {
    @PostMapping("/add")
    fun add(@RequestBody request: AddMemoRequest) {
        memoService.add(
            request.run {
                Memo(id, content, createdDate)
            }
        )
    }

    @PatchMapping("/update")
    fun update(@RequestBody request: UpdateMemoRequest) {
        request.run {
            memoService.update(id, content)
        }
    }

    @DeleteMapping("/delete/{memo_id}")
    fun delete(@PathVariable("memo_id") memoId: Long) {
        memoService.delete(memoId)
    }

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
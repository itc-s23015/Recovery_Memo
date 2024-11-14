package jp.ac.itcollege.s23015.memo_recovery.presentation.controller

import jp.ac.itcollege.s23015.memo_recovery.domain.model.Memo
import jp.ac.itcollege.s23015.memo_recovery.presentation.form.AddMemoRequest
import jp.ac.itcollege.s23015.memo_recovery.presentation.form.UpdateMemoRequest
import jp.ac.itcollege.s23015.memo_recovery.service.AdminMemoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("admin/memo")
@CrossOrigin
class AdminMemoController (
    private val adminMemoService: AdminMemoService
){
    @PostMapping("/add")
    fun add(@RequestBody request: AddMemoRequest) {
        adminMemoService.add(
            request.run {
                Memo(id, content, createdDate)
            }
        )
    }

    @PatchMapping("/update")
    fun update(@RequestBody request: UpdateMemoRequest) {
        request.run {
            adminMemoService.update(id, content)
        }
    }

    @DeleteMapping("/delete/{memo_id}")
    fun delete(@PathVariable("memo_id") memoId: Long) {
        adminMemoService.delete(memoId)
    }
}
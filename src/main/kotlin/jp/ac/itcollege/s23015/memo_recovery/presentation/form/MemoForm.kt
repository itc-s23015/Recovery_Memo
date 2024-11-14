package jp.ac.itcollege.s23015.memo_recovery.presentation.form

import com.fasterxml.jackson.annotation.JsonFormat
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable


@Serializable
data class GetMemoListResponse(val memoList: List<MemoInfo>)

@Serializable
data class MemoInfo(
    val id: Long,
    val content: String,
    val createdAt: LocalDateTime,
){

}

@Serializable
data class GetMemoDetailResponse(
    val id: Long,
    val content: String,
    val createdDate: LocalDateTime
)

@Serializable
data class AddMemoRequest(
    val id: Long,
    val content: String,
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val createdDate: LocalDateTime
)

@Serializable
data class UpdateMemoRequest(
    val id: Long,
    val content: String?
//    val createdDate: LocalDateTime?,
)


package jp.ac.itcollege.s23015.memo_recovery.domain.model

import kotlinx.datetime.LocalDateTime

data class Memo(
    val id: Long,
    val content: String,
    val createdAt: LocalDateTime
)

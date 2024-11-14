package jp.ac.itcollege.s23015.memo_recovery.domain.model

import jp.ac.itcollege.s23015.memo_recovery.domain.types.RoleType

data class User (
    val id: Long,
    val email: String,
    val password: String,
    val name: String,
    val roleType: RoleType
    )
package jp.ac.itcollege.s23015.memo_recovery.domain.repository

import jp.ac.itcollege.s23015.memo_recovery.domain.model.User

interface UserRepository {
        fun find(email: String): User?
}
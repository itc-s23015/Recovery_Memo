package jp.ac.itcollege.s23015.memo_recovery.service

import jp.ac.itcollege.s23015.memo_recovery.domain.model.User
import jp.ac.itcollege.s23015.memo_recovery.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val userRepository: UserRepository
) {
    fun findUser(email: String): User? {
        return userRepository.find(email)
    }
}
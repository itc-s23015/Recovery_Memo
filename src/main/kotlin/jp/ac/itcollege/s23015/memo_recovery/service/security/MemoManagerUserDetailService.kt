package jp.ac.itcollege.s23015.memo_recovery.service.security

import jp.ac.itcollege.s23015.memo_recovery.domain.model.User
import jp.ac.itcollege.s23015.memo_recovery.domain.types.RoleType
import jp.ac.itcollege.s23015.memo_recovery.service.AuthenticationService
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException


class MemoManagerUserDetailsService (
    private val authenticationService: AuthenticationService
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        username ?: throw UsernameNotFoundException("メールアドレスが空です")

        val user = authenticationService.findUser(username)
            ?: throw UsernameNotFoundException("$username に該当するユーザーはいません")
        return MemoManagerUserDetails(user)
    }

    private data class MemoManagerUserDetails(
        val id: Long,
        val email: String,
        val pass: String,
        val roleType: RoleType
    ) : UserDetails {
        constructor(user: User) : this(user.id, user.email, user.password, user.roleType)

        override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
            return AuthorityUtils.createAuthorityList(this.roleType.toString())
        }

        override fun getPassword(): String {
            return pass
        }

        override fun getUsername(): String {
            return email
        }
    }
}
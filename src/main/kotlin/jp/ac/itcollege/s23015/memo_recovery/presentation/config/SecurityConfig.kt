package jp.ac.itcollege.s23015.memo_recovery.presentation.config


import jp.ac.itcollege.s23015.memo_recovery.service.security.MemoManagerUserDetailsService
import jp.ac.itcollege.s23015.memo_recovery.domain.types.RoleType
import jp.ac.itcollege.s23015.memo_recovery.presentation.handler.SecurityHandler
import jp.ac.itcollege.s23015.memo_recovery.service.AuthenticationService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val authenticationService: AuthenticationService
) {
    @Bean
    @Order(1)
    fun configure(http: HttpSecurity) : SecurityFilterChain {
        http {
            authorizeHttpRequests {
                authorize("/login", permitAll)
//                authorize("/admin/**", hasAuthority(RoleType.ADMIN.toString()))
                authorize("/memo/**", hasAuthority(RoleType.USER.toString()))
                authorize("/memo/**", authenticated)
//                authorize("/memo/**", permitAll)
                authorize(anyRequest, authenticated)
            }
            formLogin {
                loginProcessingUrl = "/login"
                usernameParameter = "email"
                passwordParameter = "pass"
                authenticationSuccessHandler = SecurityHandler
                authenticationFailureHandler = SecurityHandler
            }
            csrf {
                disable()
            }
            exceptionHandling {
                authenticationEntryPoint = SecurityHandler
                accessDeniedHandler = SecurityHandler
            }
        }
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = Argon2PasswordEncoder(
        16, 32, 1, 19 * 1024, 2
    )

    @Bean
    fun userDetailsService(): UserDetailsService =
        MemoManagerUserDetailsService(authenticationService)

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val config = CorsConfiguration().apply {
            allowedMethods = listOf(CorsConfiguration.ALL)
            allowedHeaders = listOf(CorsConfiguration.ALL)
            allowedOrigins = listOf(
                "http://localhost:3000"
            )
            allowCredentials = true
        }
        val source = UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", config)
        }
        return source
    }
}
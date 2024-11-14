package jp.ac.itcollege.s23015.memo_recovery.presentation.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class MemoNotFoundException(override val message: String) : IllegalArgumentException()

@ResponseStatus(HttpStatus.CONFLICT)
class MemoIdAlreadyExistsException(override val message: String) : Exception(message)
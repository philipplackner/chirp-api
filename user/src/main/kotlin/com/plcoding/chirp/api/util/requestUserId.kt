package com.plcoding.chirp.api.util

import com.plcoding.chirp.domain.exception.UnauthorizedException
import com.plcoding.chirp.domain.type.UserId
import org.springframework.security.core.context.SecurityContextHolder

val requestUserId: UserId
    get() = SecurityContextHolder.getContext().authentication?.principal as? UserId
        ?: throw UnauthorizedException()
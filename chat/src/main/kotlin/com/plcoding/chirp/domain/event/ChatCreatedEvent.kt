package com.plcoding.chirp.domain.event

import com.plcoding.chirp.domain.type.ChatId
import com.plcoding.chirp.domain.type.UserId

data class ChatCreatedEvent(
    val chatId: ChatId,
    val participantIds: List<UserId>
)

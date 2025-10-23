@file:Suppress("DEPRECATION")

package com.plcoding.chirp.infra.message_queue

import com.plcoding.chirp.domain.events.ChirpEvent
import com.plcoding.chirp.domain.events.user.UserEventConstants
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.JacksonJavaTypeMapper
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tools.jackson.databind.DefaultTyping
import tools.jackson.databind.json.JsonMapper
import tools.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import tools.jackson.databind.jsontype.PolymorphicTypeValidator
import tools.jackson.module.kotlin.kotlinModule

@Configuration
class RabbitMqConfig {

    @Bean
    fun messageConverter(): JacksonJsonMessageConverter {
        val polymorphicTypeValidator: PolymorphicTypeValidator = BasicPolymorphicTypeValidator.builder()
            .allowIfBaseType(ChirpEvent::class.java)
            .allowIfSubType("java.util.")
            .allowIfSubType("kotlin.collections.")
            .build()

        val objectMapper = JsonMapper.builder()
            .addModule(kotlinModule())
            .polymorphicTypeValidator(polymorphicTypeValidator)
            .activateDefaultTyping(polymorphicTypeValidator, DefaultTyping.NON_FINAL)
            .build()

        val converter = JacksonJsonMessageConverter(objectMapper)
        converter.setTypePrecedence(JacksonJavaTypeMapper.TypePrecedence.TYPE_ID)
        return converter
    }


        @Bean
    fun rabbitTemplate(
        connectionFactory: ConnectionFactory,
        messageConverter: JacksonJsonMessageConverter,
    ): RabbitTemplate {
        return RabbitTemplate(connectionFactory).apply {
            this.messageConverter = messageConverter
        }
    }

    @Bean
    fun userExchange() = TopicExchange(
        UserEventConstants.USER_EXCHANGE,
        true,
        false
    )

    @Bean
    fun notificationUserEventsQueue() = Queue(
        MessageQueues.NOTIFICATION_USER_EVENTS,
        true
    )
}
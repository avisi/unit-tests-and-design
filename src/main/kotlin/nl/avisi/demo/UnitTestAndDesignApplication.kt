package nl.avisi.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.web.client.RestTemplate
import org.springframework.context.annotation.Bean

@SpringBootApplication
class UnitTestAndDesignApplication {
    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate = builder.build()
}

fun main(args: Array<String>) {
    runApplication<UnitTestAndDesignApplication>(*args)
}

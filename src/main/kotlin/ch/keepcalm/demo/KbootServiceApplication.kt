package ch.keepcalm.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KbootServiceApplication

fun main(args: Array<String>) {
	runApplication<KbootServiceApplication>(*args)
}

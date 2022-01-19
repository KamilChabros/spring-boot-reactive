package xyz.reactivespringboot.demo.stockservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StockserviceApplication

fun main(args: Array<String>) {
	runApplication<StockserviceApplication>(*args)
}

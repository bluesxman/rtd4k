package com.smackwerks.rtd4k

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.ResourceLoader.Companion.Classpath
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.routing.static
import org.http4k.server.Netty
import org.http4k.server.asServer

fun main(args: Array<String>) {
    val port = if (args.isNotEmpty()) args[0].toInt() else 8080

    val app = routes(
        "/static" bind static(Classpath("/")),
        "/" bind GET to { _: Request -> Response(OK).body("{'msg': 'hello'}")
        })

    System.out.println("Starting server")
    app.asServer(Netty(port)).start().block()
}
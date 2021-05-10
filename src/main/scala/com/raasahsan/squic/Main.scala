package com.raasahsan.squic

import cats.effect.{ExitCode, IO, IOApp}
import fs2.io.net.Network
import cats.effect.kernel.Resource
import com.comcast.ip4s.Port
import javax.net.ssl.{SSLContext, SSLEngine, SSLParameters, X509TrustManager}
import java.security.cert.X509Certificate

object Main extends IOApp {
  def getSslContext: IO[SSLContext] =
    IO {
      val context = SSLContext.getInstance("TLSv1.3")
      val trustManager = new X509TrustManager {
        def checkClientTrusted(x: Array[X509Certificate], y: String): Unit = {}
        def checkServerTrusted(x: Array[X509Certificate], y: String): Unit = {}
        def getAcceptedIssuers(): Array[X509Certificate] = Array()
      }
      context.init(null, Array(trustManager), null)
      context
    }

  def createSslEngine(sslContext: SSLContext): Resource[IO, SSLEngine] =
    Resource.eval(
      IO {
        val engine = sslContext.createSSLEngine()
        engine.setUseClientMode(false)
        engine.setSSLParameters(new SSLParameters())
        engine
      }
    )

  def app: Resource[IO, Unit] = {
    val network = Network.forAsync[IO]
    for {
      sslContext <- Resource.eval(getSslContext)
      sslEngine <- createSslEngine(sslContext)
      socketGroup <- network.datagramSocketGroup()
      socket <- socketGroup.openDatagramSocket(port = Some(Port.fromInt(8080).get))
      _ <- socket.reads.evalMap(datagram => IO.println(datagram.bytes.toArray.toList)).compile.drain.background
      _ <- Resource.make(IO.println("Bound to UDP server"))(_ => IO.println("Shutting down"))
    } yield ()
  }

  override def run(args: List[String]): IO[ExitCode] =
    app.useForever.as(ExitCode.Success)
}

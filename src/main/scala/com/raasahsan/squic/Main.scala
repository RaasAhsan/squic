package com.raasahsan.squic

import cats.effect.{ExitCode, IO, IOApp}
import fs2.io.net.Network
import cats.effect.kernel.Resource
import com.comcast.ip4s.Port

object Main extends IOApp {
  def app: Resource[IO, Unit] = {
    val network = Network.forAsync[IO]
    for {
      socketGroup <- network.datagramSocketGroup()
      socket <- socketGroup.openDatagramSocket(port = Some(Port.fromInt(8080).get))
      _ <- socket.reads.evalMap(datagram => IO.println(datagram.bytes.toArray.toList)).compile.drain.background
      _ <- Resource.make(IO.println("Bound to UDP server"))(_ => IO.println("Shutting down"))
    } yield ()
  }

  override def run(args: List[String]): IO[ExitCode] =
    app.useForever.as(ExitCode.Success)
}

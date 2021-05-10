package com.raasahsan

sealed trait Frame

object Frame {
  final case class Padding() extends Frame

  final case class Ping() extends Frame

  final case class Ack(largestAcknowledged: Long, ackDelay: Long, ackRangeCount: Long, firstAckRange: Long, ackRanges: List[AckRange], ecnCounts: EcnCounts) extends Frame

  final case class AckRange(gap: Long, ackRangeLength: Long)

  final case class EcnCounts(ect0: Long, ect1: Long, ecnCe: Long)

  final case class ResetStream(streamId: Long, errorCode: Long, finalSize: Long) extends Frame

  final case class StopSending(streamId: Long, errorCode: Long) extends Frame

  final case class Crypto(offset: Long, length: Long, data: Array[Byte]) extends Frame

  final case class NewToken(tokenLength: Long, token: Array[Byte]) extends Frame

  final case class Stream(streamId: Long, offset: Option[Long], length: Option[Long], data: Array[Byte]) extends Frame

  final case class MaxData(maximumData: Long) extends Frame

  final case class MaxStreamData(streamId: Long, maximumStreamData: Long) extends Frame

  final case class MaxStreams(maximumStreams: Long) extends Frame

  final case class DataBlocked(maximumData: Long) extends Frame

  final case class StreamDataBlocked(streamId: Long, maximumStreamData: Long) extends Frame

  final case class StreamsBlocked(maximumStreams: Long) extends Frame

  final case class NewConnectionId(sequenceNumber: Long, retirePriorTo: Long, length: Int, connectionId: Array[Byte], statelessResetToken: Array[Byte]) extends Frame

  final case class RetireConnectionId(sequenceNumber: Long) extends Frame

  final case class PathChallenge(data: Array[Byte]) extends Frame

  final case class PathResponse(data: Array[Byte]) extends Frame

  final case class ConnectionClose(errorCode: Long, frameType: Option[Long], reasonPhraseLength: Long, reasonPhrase: Array[Byte]) extends Frame

  final case class HandshakeDone() extends Frame
}

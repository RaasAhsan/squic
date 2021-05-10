package com.raasahsan.squic

sealed trait TransportErrorCode

object TransportErrorCode {
  case object NoError extends TransportErrorCode
  case object InternalError extends TransportErrorCode
  case object ConnectionRefused extends TransportErrorCode
  case object FlowControlError extends TransportErrorCode
  case object StreamLimitError extends TransportErrorCode
  case object StreamStateError extends TransportErrorCode
  case object FinalSizeError extends TransportErrorCode

  case object FrameEncodingError extends TransportErrorCode
  case object TransportParameterError extends TransportErrorCode
  case object ConnectionIdLimitError extends TransportErrorCode
  case object ProtocolViolation extends TransportErrorCode
  case object InvalidToken extends TransportErrorCode
  case object ApplicationError extends TransportErrorCode
  case object CryptoBufferExceeded extends TransportErrorCode
  case object KeyUpdateError extends TransportErrorCode
  case object AeadLimitReached extends TransportErrorCode
  case object NoViablePath extends TransportErrorCode
  final case class CryptoError(code: Byte) extends TransportErrorCode
}

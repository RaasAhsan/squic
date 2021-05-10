package com.raasahsan.squic

sealed trait TransportParameter

object TransportParameter {
  
  case object OriginalDestinationConnectionId extends TransportParameter

  case object MaxIdleTimeout extends TransportParameter

  case object StatelessResetToken extends TransportParameter

  case object MaxUdpPayloadSize extends TransportParameter

  case object InitialMaxData extends TransportParameter

  case object InitialMaxStreamDataBidiLocal extends TransportParameter

  case object InitialMaxStreamDataBidiRemote extends TransportParameter

  case object InitialMaxStreamDataUni extends TransportParameter

  case object InitialMaxStreamsBidi extends TransportParameter

  case object InitialMaxStreamsUni extends TransportParameter

  case object AckDelayExponent extends TransportParameter

  case object MaxAckDelay extends TransportParameter

  case object DisableActiveMigration extends TransportParameter

  case object PreferredAddress extends TransportParameter

  case object ActiveConnectionIdLimit extends TransportParameter

  case object InitialSourceConnectionId extends TransportParameter

  case object RetrySourceConnectionId extends TransportParameter

}

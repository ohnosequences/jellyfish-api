package ohnosequences.jellyfish.api.jellyfish

import ohnosequences.jellyfish.api._, opt._
import ohnosequences.cosas._, types._, records._, fns._, klists._
import better.files._

case object queryAll extends AnyJellyfishCommand {

  // NOTE this is important, don't touch it
  override lazy val name = Seq("jellyfish", "query")

  type Arguments = arguments.type
  case object arguments extends RecordType(
    input    :×:
    sequence :×:
    output   :×:
    |[AnyJellyfishOption]
  )

  type ArgumentsVals =
    (input.type    := input.Raw)    ::
    (sequence.type := sequence.Raw) ::
    (output.type   := output.Raw)   ::
    *[AnyDenotation]

  type Options = options.type
  case object options extends RecordType(
    load     :×:
    no_load  :×:
    |[AnyJellyfishOption]
  )

  type OptionsVals =
    (load.type    := load.Raw)    ::
    (no_load.type := no_load.Raw) ::
    *[AnyDenotation]

  lazy val defaults = options(
    load(false)    ::
    no_load(false) ::
    *[AnyDenotation]
  )

}

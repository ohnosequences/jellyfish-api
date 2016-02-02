package ohnosequences.jellyfish.api.commands

import ohnosequences.jellyfish.api._, options._
import ohnosequences.cosas._, types._, records._, fns._, klists._
import better.files._

case object dump extends JellyfishCommand {

  type Arguments = arguments.type
  case object arguments extends RecordType(
    input   :×:
    output  :×:
    |[AnyJellyfishOption]
  )

  type ArgumentsVals =
    (input.type := input.Raw)    ::
    (output.type := output.Raw)  ::
    *[AnyDenotation]

  type Options = options.type
  case object options extends RecordType(
    column      :×:
    tab         :×:
    lower_count :×:
    upper_count :×: |[AnyJellyfishOption]
  )

  type OptionsVals =
    (column.type := column.Raw)             ::
    (tab.type := tab.Raw)                   ::
    (lower_count.type := lower_count.Raw)   ::
    (upper_count.type := upper_count.Raw)   ::
    *[AnyDenotation]

  lazy val defaults = options(
    column(true)    ::
    tab(true)       ::
    lower_count(1L) ::
    // 4 bytes counter size is twice this but...
    upper_count(Long.MaxValue) :: *[AnyDenotation]
  )

  def apply(
    argumentValues: ArgumentsVals,
    optionValues: OptionsVals
  )
  : JellyfishExpression[dump.type, ArgumentsVals, OptionsVals] =
    JellyfishExpression(dump)(arguments := argumentValues, options := optionValues)
}

package scala.parser

/**
  * Created by Johan on 2015-12-12.
  */
case class ParseResult(input: String, processed: List[String]) {
  def memoryCharCount = processed.length

  def codeCharCount = input.length

  def diff = input.length - memoryCharCount

  def +(that: ParseResult): ParseResult =
    ParseResult(this.input + that.input, this.processed ++ that.processed)
}

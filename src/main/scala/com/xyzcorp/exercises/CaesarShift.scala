package com.xyzcorp.exercises

object CaesarShift {
    private val ALPHA_SIZE = 26
    private val SMALL_A = 97
    private val LARGE_A = 65

    def decode(str: String, shift: BigInt): String = {
        encode(str, -shift)
    }

    def encode(str: String, shift: BigInt): String = {
        require(str != null, "String cannot be null")
        str.map(shiftChar(_, shift))
    }

    private def shiftChar(c: Char, shift: BigInt): Char = {
        if (!c.isLetter) c
        else convert(c, shift, if (c.isUpper) LARGE_A else SMALL_A)
    }

    private def convert(c: Char, shift: BigInt, selectedA: Int) =
        ((c - selectedA + (shift % ALPHA_SIZE) + ALPHA_SIZE) % ALPHA_SIZE +
            selectedA).toChar
}

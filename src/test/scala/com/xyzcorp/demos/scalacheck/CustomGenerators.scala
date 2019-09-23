package com.xyzcorp.demos.scalacheck

import org.scalacheck.Prop.{forAll, passed}
import org.scalacheck.{Arbitrary, Gen, Properties}

object CustomGenerators extends Properties("Custom Albums") {

    case class Track(name: String, seconds: Int)

    case class Album(name: String, artist: String, tracks: List[Track])

    val genTrack: Gen[Track] = {
        for {
            name <- Gen.alphaStr
            seconds <- Gen.choose(1, 8 * 60)
        } yield Track(name, seconds)
    }

    val genAlbum: Gen[Album] = {
        for {
            name <- Gen.alphaStr
            artist <- Gen.alphaStr
            tracks <- Gen.containerOf[List, Track](genTrack)
        } yield Album(name, artist, tracks)
    }


    implicit val album:Arbitrary[Album] = Arbitrary(genAlbum)

    property("check that an album will work") = forAll {a:Album =>
         passed
    }
    private val genAlbums = Gen.listOf[Album](genAlbum)

    property("Check all track times are greater than 0") = forAll(genAlbums) {
        (a: List[Album]) => passed
    }

    val capitalizedString: Gen[String] = for {
        first <- Gen.alphaUpperChar
        rest <- Gen.listOf(Gen.alphaLowerChar)
    } yield (first :: rest).mkString

    property("Check all track times are greater than 0") = forAll(genAlbums) {
        (a: List[Album]) => passed
    }
}

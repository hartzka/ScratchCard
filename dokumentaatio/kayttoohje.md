# Käyttöohje

Lataa tiedosto [ScratchCard.jar](https://github.com/hartzka/ot-harjoitustyo/releases/download/Loppupalautus/ScratchCard.jar)

## Konfigurointi

Ohjelma luo jar-tiedoston kansioon tiedoston data.db, jollei sitä ole valmiina. Erityisiä toimia ei tarvita.

Tiedostossa [config.properties](https://github.com/hartzka/ot-harjoitustyo/blob/master/ScratchCard/config.properties) määritellään tietokannassa käytettävä taulu.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar ScratchCard.jar
```

## Aloitus

Sovellus käynnistyy aloitusnäkymään:

<img src="https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Screenshot9.png">

## Uuden arvan ostaminen ja pelin aloittaminen

Aloitusnäkymässä on mahdollista ostaa uusi arpa _new/collect_-painikkeesta, päästä stats-näkymään _stats_-painikkeesta ja vaihtaa näkymää _win table_ -painikkeesta.

## Arvan raaputtaminen

Arvan kenttiä raaputetaan hiiren avulla. Kaikki kentät voi paljastaa _claim_-painikkeesta.

Kun uusi arpa on ostettu, uudet raaputuskentät ilmestyvät näkyville:

<img src="https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Screenshot2.png">

Tässä raaputus on jo loppusuoralla, ja näkymää on vaihdettu.

<img src="https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Screenshot3.png">

## Raaputuksen jälkeen

Kun kentät on avattu, mahdolliset voitot näkyvät ruudulla. Voiton voi ottaa talteen heti tai yrittää moninkertaistaa _double up_ -painikkeella, jolloin siirrytään tuplausmoodiin. Mikäli kierroksella ei tule voittoa, kierros päättyy ja voi ostaa uuden arvan.

Kuten huomataan, voitto voi osua myös useammalle riville. Tällöin kierrosvoitto lasketaan voittojen summana:

<img src="https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Screenshot4.png">

## Tuplausmoodi

Tuplauksessa arvataan tuplausruutuun ilmestyvä kuvio ja raaputetaan se esiin. Valittavana on 4 kuviota kertoimineen. Mitä suurempi kerroin, sitä harvinaisempaa voittaminen on. Voiton voi ottaa talteen _new/collect_ -painikkeella, jolloin palataan taas perusnäkymään, ja kierros on päättynyt. Mikäli arvaa väärin, menettää voittonsa ja tuplaus päättyy.

Valitaan kiivi kertoimella 3 ja aletaan raaputtamaan. Näyttäisi, että voitamme voiton 3-kertaisena!

<img src="https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Screenshot5.png">

Voitamme 27 yksikköä rahaa. Halutessamme voimme ottaa voiton talteen _new/collect_ -painikkeesta. Näkymää voi muuttaa myös tuplaustilassa:

<img src="https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Screenshot6.png">

Jatkamme kuitenkin tuplaamista ja yritämme vielä kaksinkertaistaa voittomme 54 rahayksikköön. Jännitys tiivistyy, tuleeko voitto? 

<img src="https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Screenshot7.png">

Tällä kertaa arvaus on väärin, tulee toinen kiivi peräkkäin, vaikka veikkasimme sitruunaa. Tuplaus päättyy, voitto menetetään ja voimme jatkaa pelaamista painamalla _new/collect_.

<img src="https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Screenshot8.png">

## Stats-näkymä

_Stats_-painikeesta saa esille stats-tilan, josta on nähtävissä monenlaista tilastoa pelistä, mm. kokonais- ja sessiorahatilanteen, voittojen lukumäärän, eri voittoluokkien lukumäärän, pelatut kierrokset ja tuplauspelin tilastot. Valikosta pääsee takaisin _close_-painikkeella.

<img src="https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Screenshot1.png">

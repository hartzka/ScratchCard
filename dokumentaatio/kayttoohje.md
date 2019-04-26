# Käyttöohje

Lataa tiedosto [ScratchCard.jar](https://github.com/hartzka/ot-harjoitustyo/releases/download/Viikko6/ScratchCard.jar)

## Konfigurointi

Ohjelma luo jar-tiedoston kansioon tiedoston data.db, jollei sitä ole valmiina. Erityisiä toimia ei tarvita.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar ScratchCard.jar
```

## Aloitus

Sovellus käynnistyy aloitusnäkymään:

<img src="">

## Uuden arvan ostaminen ja pelin aloittaminen

Aloitusnäkymässä on mahdollista ostaa uusi arpa _new/collect_-painikkeesta, päästä stats-näkymään _stats_-painikkeesta ja _more info_ -tekstistä sekä vaihtaa näkymää _win table_ -painikkeesta.

<img src="">

## Arvan raaputtaminen

Arvan kenttiä raaputetaan hiiren avulla. Kaikki kentät voi paljastaa _claim_-painikkeesta.

<img src="">

## Raaputuksen jälkeen

Kun kentät on avattu, mahdolliset voitot näkyvät ruudulla. Voiton voi ottaa talteen heti tai yrittää moninkertaistaa _double up_ -painikkeella, jolloin siirrytään tuplausmoodiin. Mikäli kierroksella ei tule voittoa, kierros päättyy ja voi ostaa uuden arvan.

## Tuplausmoodi

Tuplauksessa arvataan tuplausruutuun ilmestyvä kuvio ja raaputetaan se esiin. Valittavana on 4 kuviota kertoimineen. Mitä suurempi kerroin, sitä harvinaisempaa voittaminen on. Voiton voi ottaa talteen _new/collect_ -painikkeella, jolloin palataan taas perusnäkymään, ja kierros on päättynyt. Mikäli arvaa väärin, menettää voittonsa ja tuplaus päättyy.

## Stats-näkymä

_Stats_-painikeesta saa esille ja pois rahatilanteen. _More info_ -tekstiä klikkaamalla pääsee stats-tilaan, josta on nähtävissä monenlaista tilastoa pelistä, mm. kokonais- ja sessiorahatilanteen, voittojen lukumäärän, eri voittoluokkien lukumäärän, pelatut kierrokset ja tuplauspelin tilastot. Valikosta pääsee takaisin _close_-painikkeella.

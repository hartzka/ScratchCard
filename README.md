# **ScratchCard**

Raaputusarpasovellus, joka tallettaa käyttäjän voitot, rahatilanteen ja muuta tilastotietoa. Sovelluksessa on avattavia raaputuskenttiä ja ennalta määriteltyjä voittoluokkia, ja pelaaja voittaa, jos onnistuu saamaan samalle riville jotkin voittoon oikeuttavat kuviot. Kuviot ovat hedelmäsymboleja. Voitto voi osua useammalle riville.

Voittoa voi yrittää moninkertaistaa tuplaus-tilassa arvaamalla tuplausruutuun sijoitetun kuvion. Sovelluksen näkymää on mahdollista muuttaa niin, että voittotaulu on näkyvillä tai pois näkyvistä. Tilastoja pääsee tarkastelemaan stats-tilassa.

Tämä sovellus on Helsingin yliopiston kurssin **ohjelmistotekniikka** (2019) harjoitustyö.

## Dokumentaatio
[Käyttöohje]

[Vaatimusmäärittely](https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti]

[Työaikakirjanpito](https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

## Releaset
[Viikko 5](https://github.com/hartzka/ot-harjoitustyo/releases/tag/Viikko5)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _ScratchCard-1.0-SNAPSHOT.jar_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/hartzka/ot-harjoitustyo/blob/master/ScratchCard/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_



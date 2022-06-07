# **ScratchCard**

Scratch Card app

Software Techniques Lab Project at University of Helsinki (5cr, 2019). 

Raaputusarpasovellus, joka tallettaa käyttäjän voitot, rahatilanteen ja muuta tilastotietoa. Sovelluksessa on avattavia raaputuskenttiä ja ennalta määriteltyjä voittoluokkia, ja pelaaja voittaa, jos onnistuu saamaan samalle riville jotkin voittoon oikeuttavat kuviot. Kuviot ovat hedelmäsymboleja. Voitto voi osua useammalle riville.

Voittoa voi yrittää moninkertaistaa tuplaus-tilassa arvaamalla tuplausruutuun sijoitetun kuvion. Sovelluksen näkymää on mahdollista muuttaa niin, että voittotaulu on näkyvillä tai pois näkyvistä. Tilastoja pääsee tarkastelemaan stats-tilassa.

Tämä sovellus on Helsingin yliopiston kurssin **ohjelmistotekniikka** (2019) harjoitustyö.

## Documentation

[Manual](https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Requirements](https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Architecture](https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testing](https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/testaus.md)

[Bookkeeping](https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

## Releases

[Week 5](https://github.com/hartzka/ot-harjoitustyo/releases/tag/Viikko5)

[Week 6](https://github.com/hartzka/ot-harjoitustyo/releases/tag/Viikko6)

[Final](https://github.com/hartzka/ot-harjoitustyo/releases/tag/Loppupalautus)


## Command line instructions

The following commands are executed from the root directory.

### Testing

```
mvn test
```

Test report

```
mvn jacoco:report
```

Open the file _target/site/jacoco/index.html_ with your browser.

### Generate the .jar

```
mvn package
```

generates a .jar-file in _target_ folder: _ScratchCard-1.0-SNAPSHOT.jar_

run the .jar file with:

```
java -jar ScratchCard-1.0-SNAPSHOT.jar
``` 

### Checkstyle

[checkstyle.xml](https://github.com/hartzka/ot-harjoitustyo/blob/master/ScratchCard/checkstyle.xml)

```
mvn jxr:jxr checkstyle:checkstyle
```

Reports: _target/site/checkstyle.html_

### JavaDoc

[JavaDoc](https://github.com/hartzka/ShortestPathSolver/blob/master/ShortestPathSolver/apidocs/index.html)

```
mvn javadoc:javadoc
```

Open the file _target/site/apidocs/index.html_ with your browser.



# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattaa nelitasoista kerrosarkkitehtuuria seuraavalla pakkausrakenteella:

<img src="https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pakkauskaavio.png" width="300"/>

Pakkaus _kh.scratchcard.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän, _kh.scratchcard.domain_ sovelluslogiikan ja _kh.scratchcard.dao_ tietojen pysyväistallennuksesta vastaavan toiminnallisuuden. _kh.scratchcard.main_ sisältää vain ohjelman käynnistävän pääluokan.

## Käyttöliittymä

Käyttöliittymä sisältää viisi erilaista näkymää:
- perusnäkymä voittotaulun kanssa
- perusnäkymä ilman voittotaulua
- tuplausnäkymä voittotaulun kanssa
- tuplausnäkymä ilman voittotaulua
- stats-näkymä

Sovelluksessa on yksi Scene-olio, joka sisältää kaikki näkymät. Näkymistä yksi kerrallaan on näkyvänä ja muut piilotettuna. Kaikki on sijoitettu stageen. Käyttöliittymä on rakennettu ohjelmallisesti pakkauksessa [kh.scratchcard.ui](https://github.com/hartzka/ot-harjoitustyo/tree/master/ScratchCard/src/main/java/kh/scratchcard/ui). Käyttöliittymä ja grafiikka koostuvat varsinaisesta käyttöliittymästä, raaputuskentistä (Field) ja voittotaulusta (WinTable). Pakkaus ui sisältää siis kaikki luokat, jotka tarjoavat toiminnallisuuksia grafiikkaan.

Käyttöliittymäluokat on pyritty eristämään sovelluslogiikasta, ne ainoastaan kutsuvat sovelluslogiikan ScratchCard metodeja.

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat luokat [ScratchCard](https://github.com/hartzka/ot-harjoitustyo/blob/master/ScratchCard/src/main/java/kh/scratchcard/domain/ScratchCard.java) ja [Hand](https://github.com/hartzka/ot-harjoitustyo/blob/master/ScratchCard/src/main/java/kh/scratchcard/domain/Hand.java), jotka kuvaavat sovelluksen loogisia toimintoja, joita ei välttämättä näy ruudulla, mutta jotka ovat oleellisimpia pelin logiikan kannalta. ScratchCard sisältää sovelluksen päälogiikan ja Hand tarjoaa toiminnallisuudet, jotka pitävät kirjaa kentissä olevista symboleista ja huolehtivat kenttien päivityksestä ja arpomisesta. Lisäksi enum WinCategory sisältää voittoluokat.

luokka/pakkauskaavio:

<img src="https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/luokkakaavio.png" width="350"/>

## Tietojen pysyväistallennus

Pakkauksen _kh.scratchcard.dao_ luokka _DataDao_ huolehtii tietojen tallettamisesta tietokantaan. Tietokantatoteutus on SQL-pohjainen. Pelin alussa data haetaan tietokannasta ja lopetettaessa data tallennetaan tietokantaan.

Luokka noudattaa [Data Access Object](https://en.wikipedia.org/wiki/Data_access_object) -suunnittelumallia. Luokka on eristetty rajapinnan _Dao_ taakse eikä sovelluslogiikka käytä luokkia suoraan. Rajapinnan metodeita voi vaihtaa ja lisätä tarpeen tullen.

Luokka Data on apuluokka datan säilyttämiseen sisältäen kaksi konstruktoria. Toisessa arvot annetaan suoraan oliolle. Database-luokka sisältää käytössä olevan tietokannan osoitteen ja yhteyden, sekä tarjoaa palautustoiminnallisuuden näille objekteille. 

### Tietokanta

Sovellus tallettaa arpapelin tiedot tietokantaan data.db.

Sovelluksen juureen sijoitettu konfiguraatiotiedosto config.properties määrittelee tiedostojen nimet.

### Tyyli

Sovelluksen painikkeille on määritelty css-tyylit tiedostossa [style.css](https://github.com/hartzka/ot-harjoitustyo/blob/master/ScratchCard/src/main/resources/style/style.css)

### Päätoiminnallisuudet

Sovelluksen toimintalogiikka muutaman päätoiminnallisuuden osalta sekvenssikaaviona:

Uuden arvan ostaminen:

<img src="https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/newcard.png"/>

Voittojen talteenottaminen:

<img src="https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/wintaking.png"/>

#### Muut toiminnallisuudet

Sovellus käyttää arpomiseen satunnaislukugeneraattoria MersenneTwisterRNG pakkauksesta org.uncommons.maths.random.MersenneTwisterRNG. Tämä on satunnaisempi ja hieman nopeampi kuin Javan Math.Random-luokka.

## Ohjelman rakenteeseen jääneet heikkoudet

### Ui

Ui-luokkaa olisi voinut jakaa useampaan luokkaan ja siistiä, tosin käyttöliittymä sisältää nyt jo myös Field- ja WinTable -luokat, jotka sisältävät pelin graafiset toiminnot. Ui-luokkaa voidaan ajatella käyttöliittymän ydinluokaksi, jonka vastuulla on keskeisimmät käyttöliittymään liittyvät toiminnot. Ui sisältää paljon muuttujia, koska sovellus on laaja. Copypastea on pyritty hävittämään listojen avulla.

### Hand

Hand-luokka on melko laaja, luokan olisi voinut jakaa Hand-luokkaan ja erilliseen WinRandomizer-luokkaan, jossa olisi toteutettu käden voittoluokkien ja kuvioiden arvonta.

### DataDao

DataDaon save-metodi on melko pitkä, koska siinä käytetään transaktiota. Voisi jakaa useampaan metodiin, mutta transaktio on hyvä toteuttaa yhdessä metodissa.


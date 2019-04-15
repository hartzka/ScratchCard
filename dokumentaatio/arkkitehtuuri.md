# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattaa kolmitasoista kerrosarkkitehtuuria seuraavalla pakkausrakenteella:

<img src="https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/20190408_200919.jpg" width="300"/>

Pakkaus _kh.scratchcard.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän, _kh.scratchcard.domain_ sovelluslogiikan ja _kh.scratchcard.dao_ tietojen pysyväistallennuksesta vastaavan toiminnallisuuden.

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

Sovelluksen loogisen datamallin muodostavat luokat [ScratchCard](https://github.com/hartzka/ot-harjoitustyo/blob/master/ScratchCard/src/main/java/kh/scratchcard/domain/ScratchCard.java) ja [Hand](https://github.com/hartzka/ot-harjoitustyo/blob/master/ScratchCard/src/main/java/kh/scratchcard/domain/Hand.java), jotka kuvaavat sovelluksen toimintoja, joita ei välttämättä näy ruudulla. ScratchCard sisältää sovelluksen päälogiikan ja Hand tarjoaa toiminnallisuudet, jotka pitävät kirjaa kentissä olevista symboleista ja huolehtivat kenttien päivityksestä ja arpomisesta. Lisäksi enum WinCategory sisältää voittoluokat.

luokka/pakkauskaavio:

<img src="https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/20190408_200951.jpg" width="350"/>

Uuden arvan ostaminen:
<img src="https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/newcard.png" width="350"/>

Voittojen talteenottaminen:
<img src="https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/wintaking.png" width="350"/>

## Tietojen pysyväistallennus

Pakkauksen _kh.scratchcard.dao_ luokka _FileDao_ huolehtii tietojen tallettamisesta tietokantaan.

Luokat noudattavat [Data Access Object](https://en.wikipedia.org/wiki/Data_access_object) -suunnittelumallia. Luokka on eristetty rajapinnan _Dao_ taakse eikä sovelluslogiikka käytä luokkia suoraan. Rajapinnan metodeita voi vaihtaa tarpeen tullen.

### Tiedostot

Sovellus tallettaa arpapelin tiedot tietokantaan.

Sovelluksen juureen sijoitettu konfiguraatiotiedosto config.properties määrittelee tiedostojen nimet.

Sovellus tallettaa tiedot seuraavassa formaatissa:


### Päätoiminnallisuudet

Sovelluksen toimintalogiikka muutaman päätoiminnallisuuden osalta sekvenssikaaviona:

#### Muut toiminnallisuudet



## Ohjelman rakenteeseen jääneet heikkoudet



# Testausdokumentti

Ohjelmaa on testattu automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä järjestelmätason testein. Testit sijaitsevat pakkauksessa [tests](https://github.com/hartzka/ot-harjoitustyo/tree/master/ScratchCard/src/test/java/tests).

## Yksikkö- ja integraatiotestaus

### sovelluslogiikka

Sovelluslogiikan automatisoidut testit ovat testitiedostot ScratchCardTest ja HandTest eli pakkauksen [kh.scratchcard.domain](https://github.com/hartzka/ot-harjoitustyo/blob/master/ScratchCard/src/main/java/kh/scratchcard/domain) luokkia testaavat testit. Testit simuloivat luokkien toimintaa ja testaavat luokkien metodeja, mutta myös integraatiotestein luokkien välisiä yhteyksiä käyttöliittymän kautta.

ScratchCardTest testaa myös datan pysyväistallennuksen DAO-rajapinnan luokkaa DataDao tarkistaessaan tiedon tallennukseen liittyviä metodeja.

### DataDao-luokka

Pakkauksen [kh.scratchard.dao](https://github.com/hartzka/ot-harjoitustyo/blob/master/ScratchCard/src/main/java/kh/scratchcard/dao) DataDao-luokkaa on testattu testitiedostossa DataDaoTest. Tämä testaa luokan DataDao metodeja, jotka liittyvät datan tallentamiseen ja hakemiseen. Myös pakkauksen dao luokkien välisiä yhteyksiä testataan. Testissä tietokantaan luodaan testauksen aikana käytettävä valetaulu TestData, joka poistetaan testauksen päätyttyä.

### Testauskattavuus

Käyttöliittymäkerrosta eli pakkausta [kh.scratchcard.ui](https://github.com/hartzka/ot-harjoitustyo/blob/master/ScratchCard/src/main/java/kh/scratchcard/ui) lukuunottamatta sovelluksen testauksen rivikattavuus on korkea 93% ja haarautumakattavuus 88%:

<img src="https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/testaus.png" width="800">

Testaamatta jäivät seuraavat tilanteet:

- Jos ScratchCard-luokka heittää virheen muodostaessaan database- ja datadao-olioita
- ScratcCard-luokan käynnistävä start-metodi
- Jos kierrosvoitto >= 50000
- Jos data-objekti on null, kun tilastoja alustetaan
- Muutama setteri ja getteri
- Testitilanne tallennettaessa luokan ScratchCard save-metodilla tietokantaan
- Stop-metodi, kun suoritus päättyy
- Hand-luokassa isoimpien ja harvinaisten voittokategorioiden toiminnot
- Jos DataDaon tiedon hakemisessa tai tallentamisessa tapahtuu virhe
- Jos tietokannan luokan Database palauttama yhteys on null.


## Järjestelmätestaus

Sovelluksen järjestelmätestaus on tehty manuaalisesti.

### Asennus ja konfigurointi

Sovelluksen jar-tiedosto on generoitu ja sovellusta on testattu [käyttöohjeen](https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md) kuvaamalla tavalla Linux-ympäristöön siten, että sovelluksen käynnistyshakemistossa on ollut _config.properties_-tiedosto, joka määritellään myös käyttöohjeessa.

Sovelluksen testaamiseen ovat kuuluneet sekä tilanteet, joissa tietokanta on olemassa juurikansiossa, että tilanteet, joissa tietokantatiedostoa ei ole olemassa, jolloin sovellus luo sen automaattisesti.

### Toiminnallisuudet

Kaikki [määrittelydokumentissa](https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md) ja käyttöohjeessa määritellyt toiminnallisuudet on käyty läpi ja testattu. Lisäksi on testattu tilanteita, joissa ohjelma lopetetaan ennen voiton talteenottamista ja kun tuplauskenttää ollaan raaputtamassa. Jos ohjelman lopettaa, kun voitto on aktiivisena, eikä tuplauskenttää ole ehditty raaputtamaan, kierrosvoitto kirjautuu tilastoihin. Jos tuplauskenttää on jo ehditty avaamaan, voitto ei kirjaudu tilastoihin, koska muuten voisi avata vain osan tuplauskentästä ja lopettaa, jos huomaa häviävänsä. Tällöin voiton ei pidä tallentua.

## Sovellukseen jääneet laatuongelmat

Sovellus ei anna järkeviä virheilmoituksia, koska sovellusta tehdessä on oletettu, että virheitä ei tapahdu. Mikäli virheitä tapahtuu, esim. tietokantatiedosto on ongelmallinen, ohjelman käynnistävä järjestelmä on ongelmallinen tai sovellukseen ei ole luku- tai kirjoitusoikeuksia, tulee ohjelman käyttäjän itse pystyä korjaamaan ongelmat. Normaalissa käytössä ja kaikki vaadittavat ohjelmat asennettuna ongelmia ei pitäisi ilmetä.


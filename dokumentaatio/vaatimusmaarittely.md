# Vaatimusmäärittely

## Sovelluksen tarkoitus

Raaputusarpasovellus, joka tallettaa käyttäjän voitot, rahatilanteen ja muuta tilastotietoa. Pelaaja pystyy seuraamaan omaa pelitilannettaan ja voittotilastojaan sekä sitä, onko voitolla vai tappiolla. Sovelluksessa on avattavia raaputuskenttiä ja ennalta määriteltyjä voittoluokkia, ja pelaaja voittaa, jos onnistuu saamaan samalle riville jotkin voittoon oikeuttavat kuviot. Kuviot ovat hedelmäsymboleja. Voitto voi osua useammalle riville.

Voittoa voi yrittää moninkertaistaa tuplaus-tilassa arvaamalla ja raaputtamalla tuplausruutuun sijoitetun kuvion. Sovelluksen näkymää on mahdollista muuttaa niin, että voittotaulu on näkyvillä tai pois näkyvistä. Tilastoja pääsee tarkastelemaan stats-tilassa.

## Käyttäjät

Sovelluksessa on vain yksi käyttäjä, jolla on omat tilastonsa. Tilastot saa nollattua halutessaan, jolloin pelin voi aloittaa alusta. Kirjautumista ei tarvita. Jatkokehityksenä peliin voisi lisätä mahdollisuuden kirjautumiseen useammalle käyttäjälle, mutta se ei liene tällaisessa pelissä kovin tarpeellista.

## Käyttöliittymäluonnos

Sovellus koostuu neljästä eri perusnäkymästä:

<img src="https://github.com/hartzka/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/20190321_144956.jpg" width="750">

Sovellus avautuu aluksi oletusnäkymään, jossa on raaputuskentille varattu tila, alalaidan painikkeet ja voittotaulu. Voittotaulun voi ottaa pois näkyvistä ja laittaa takaisin näkyville. Tilastoja pääsee tarkastelemaan erillisessä stats-näkymässä. 

Voiton osuessa kohdalle voi voiton yrittää moninkertaistaa tuplaus-näkymässä, jossa arvataan tuplausruudussa oleva symboli. Jos arvaa väärin, tuplaus päättyy ja siirrytään takaisin oletusnäkymään joko voittotaulu näkyvillä tai pois näkyvistä. Tuplausnäkymäänkin voi asettaa voittotaulun näkyviin tai pois näkyvistä. Myös tuplausnäkymästä on mahdollisuus siirtyä stats-näkymään, jolloin stats-näkymästä poistuttaessa palataan takaisin tuplausnäkymään.

## Perusversion toiminnallisuus ja pelin säännöt

**Pelissä on 5 painiketta.** 
- *Win Table* -painikkeesta saa voittotaulun näkyville tai pois näkyvistä. 
- *Double Up* -painikkeesta pääsee tuplausmoodiin, mikäli voitto on osunut kohdalle.
- *Stats* -painikkeesta näkee pelitilanteensa, ja painikkeesta avautuu teksti *More info*, josta klikkaamalla pääsee stats-näkymään. Stats-näkymästä pääsee takaisin peliin painamalla *Close*.
- *Claim* -painikkeesta saa avattua raaputuskentät, jos ei jaksa raaputtaa niitä kaikkia näkyville.
- *New/Collect* -painikkeesta voi ostaa uuden arvan, jolloin uudet raaputuskentät ilmestyvät näkyville. Mikäli kierroksella on tullut voitto, sen voi ottaa talteen samalla painikkeella.

**Voittotaulun voittoluokilla voittaa.**
Jos pelaaja onnistuu raaputtamaan esiin yhdelle riville jotkin kuviot, jotka on määritelty voittotaulussa, pelaaja voittaa voittotaulussa määritellyn summan. Samalla kierroksella voitto voi osua useammalle riville. Tällöin kierrosvoitto lasketaan osavoittojen summana. Noin 1/4 arvoista voittaa.

**Raha**
Käytössä on fiktiivinen leikkirahayksikkö. Yksi arpa maksaa yhden yksikön verran, ja päävoitto on 50000 yksikköä. Pelitilanne näyttää, montako rahayksikköä pelaaja on voitolla tai tappiolla, mikäli tilanne on negatiivinen.

**Tuplausmoodi**
Tuplauksessa voi yrittää moninkertaistaa kierrosvoittonsa arvaamalla oikein yhden neljästä kuviosta, joka raaputetaan tuplausruudusta. Oikealla arvauksella voi moninkertaistaa voittonsa 2-, 3-, 7- tai 30-kertaiseksi. Mitä suuremman kertoimen asettaa arvaukselleen, sitä epätodennäköisempää on, että arvaus osuu oikeaan. Voiton jälkeen voi yrittää tuplausta uudelleen tai ottaa voiton talteen.

## Jatkokehitysideoita

Perusversion jälkeen peliin voisi lisätä seuraavat ominaisuudet:

- Uusia voittoluokkia ja pelimoodeja
- Uusia teemoja ja asetteluita
- Uusia kuvioita ja raaputustekniikoita
- Tilastojen ottaminen talteen esim. pdf-muodossa
- Pelin pelaamismahdollisuus ja tilastojen säilyminen useammalla laitteella, vaatisi sovelluksen siirtämisen webiin
- Mahdollisuus useille käyttäjille ja kirjautuminen
- Käyttäjille salasana ja käyttäjätietojen poisto


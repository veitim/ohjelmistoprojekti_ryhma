# TicketGuru-projekti #

Tämä projekti on tehty osana Haaga-Helian Ohjelmistoprojekti 1 - SOF005AS3A-3021 kurssia (syksy 2025).

Projektin jäsenet (scrum-tiimi):

* Jari Kuusikko
* Anton Mattila
* Markus Ovaska
* Samuel Fizum Semere
* Timo Veijalainen

Kurssitoteutuksen vetäjät (tuotteenomistajat):

* Petteri Lappalainen
* Markku Ruonavaara

## Johdanto ##

Projektin aiheena on luoda järjestelmä TicketGuru lipunmyyntipalvelulle. Asiakkaana on lipputoimisto, TicketGurun omistaja. Järjestelmältä halutaan mahdollisuus lipunmyyntiin, tapahtumien järjestelyyn, lippujen tulostamiseen, ja lippuihin koodit jotka kuitataan tapahtuman ovella.

 Järjestelmä toteutetaan Spring Boot ympäristössä.

Lähtökohtaisesti järjestelmä pyritään saada toimimaaan kaikilla päätelaitetyypeillä. Jatkokehityksessä on tarkoituksena luoda myös verkkokauppa lipunostoa varten

## Järjestelmän määrittely

## Käyttäjäroolit:

### * Asiakas: 
Ostaa lipun myyntipisteessä ja jatkossa verkkokaupassa. Haluaa varman pääsyn tapahtumaan lipulla. Voi saada lipun joko paperitulosteena tai säköisenä.

### * Lipunmyyjä: 
Myy ja tulostaa liput myyntipisteessä. Tulostaa jäljellä olevat liput ovimyyntiä varten. Valitsee lipputyypin (aikuinen, lapsi, erityisryhmä).

### * Lipuntarkastaja:
 Skannaa lipun ovella ja merkitsee sen käytetyksi. Saa järjestelmältä välittömän tiedon, onko lippu kelvollinen.

### * Järjestelmän ylläpitäjä:
 Lisää ja muokkaa tapahtumia, lippuja ja lipputyyppejä. Hallinnoi käyttäjätilejä ja oikeuksia. Näkee raportteja myynneistä ja tapahtumista. Vastaa järjestelmän toiminnasta ja jatkokehityksestä.

## Käyttäjätarinat:

### Tarina1

Lipunmyyjä haluaa pystyä myymään ja tulostamaan useita lippuja yhdelle asiakkaalle, jotta myyminen olisi tehokkaampaa.

### Tarina 2

Asiakas haluaa, että hän pääsee lippunsa kanssa takuuvarmasti tapahtumaan.

### Tarina 3

Lipuntarkastaja haluaisi, että liput voidaan lukea lukulaitteella. Näin ei tarvitse pitää lippulistoja, joita yliviivamalla merkataan käytetyt liput.

### Tarina 4

Järjestelmän ylläpitäjä haluaa kyetä jatkokehittämään järjestelmään, vaikka tämä olisi käytössä.

### Tarina 5

Lipunmyyjä haluaa tulostaa myymättömät liput ovelle kerralla ilman suurempaa päänvaivaa.

### Tarina 6

Lipunmyyjä haluaa pystyä valita lipun tyypin. Aikuinen, lapsi tai erityisryhmä.

### Tarina 7

Järjestelmän ylläpitäjä haluaa pystyä määrittämään uusia lipputyyppejä.

### Tarina 8

Asiakas haluaa voida ostaa lippunsa myös sähköisenä (esim. mobiililippuna), jotta hänen ei tarvitse kantaa paperilippua mukanaan.

### Tarina 9

Lipuntarkastaja haluaa, että järjestelmä ilmoittaa heti jos lippu on jo käytetty, jotta väärinkäytökset voidaan estää nopeasti.

## Käyttöliittymä

Tärkeimmät käyttöliittymän näkymät:
- Etusivu ja kirjautuminen
- Käyttäjän etusivu
- Toimintonäkymä(Myynti ja osto tilanteissa)
- Listaus- tai hakunäkymä
- profiilinäkymä

## Tietokanta

Asiakas
Asiakas-taulu sisältää asiakkaiden tiedot

| Kenttä      | Tyyppi  | Kuvaus                          |
| ----------- | ------- | ------------------------------- |
| Asiakas_id  | AN PK   | Asiakkaan yksilöllinen tunniste |
| Etunimi     | C/50    | Asiakkaan etunimi               |
| Sukunimi    | C/50    | Asiakkaan sukunimi              |
| Katuosoite  | C/150   | Asiakkaan katuosoite            |
| Postinumero | C/50 FK | Viittaus Postinumero-tauluun    |
| Syntymäaika | C/50    | Asiakkaan syntymäaika           |
| Sähköposti  | C/100   | Asiakkaan sähköpostiosoite      |
| Puhelin     | C/15    | Asiakkaan puhelinnumero         |
| Lisätiedot  | C/500   | Muut lisätiedot                 |

Postinumero
Postinumero-taulu sisältää postinumeron tiedot

| Kenttä           | Tyyppi | Kuvaus                |
| ---------------- | ------ | --------------------- |
| Postinumero      | C/5 PK | Postinumero           |
| Postitoimipaikka | C/100  | Postitoimipaikan nimi |

Myynti
Myynti-taulu sisältää asiakkaan tekemät ostotiedot

| Kenttä      | Tyyppi | Kuvaus                   |
| ----------- | ------ | ------------------------ |
| Myynti_id   | AN PK  | Myynnin tunniste         |
| Asiakas_id  | N FK    | Viittaus Asiakas-tauluun|
| Paivamaara  | N      | Myynnin päivämäärä       |
| Maksutapa   | C/50   | Maksutapa                |
| Tyyppi      | C/50   | Myynnin tyyppi           |

Myyntirivi
Myyntirivi-taulu sisältää yksittäiset myynnit

| Kenttä    | Tyyppi | Kuvaus                  |
| ----------| ------ | ----------------------- |
| Rivi_id   | AN PK  | Myyntirivin tunniste    |
| Myynti_id | N FK   | Viittaus Myynti-tauluun |
| Lippu_id  | N FK   | Viittaus Lippu-tauluun  |
| Paivamaara| N      | Rivin päivämäärä        |
| Summa     | N      | Rivin summa             |

Lippu
Lippu-taulu sisältää liput tapahtumiin

| Kenttä       | Tyyppi | Kuvaus                           |
| -------------| ------ | -------------------------------- |
| Lippu_id     | AN PK  | Lipun tunniste                   |
| Tapahtuma_id | N FK   | Viittaus Tapahtuma-tauluun       |
| Paikka       | C/15   | Lipun paikka                     |
| Tila         | B      | Lipun tila (esim. varattu/myyty) |

Tapahtuma
Tapahtuma-taulu sisältää tapahtumatiedot

| Kenttä        | Tyyppi   | Kuvaus                         |
| ------------- | -------- | ------------------------------ |
| Tapahtuma_id  | AN PK    | Tapahtuman tunniste            |
| Lippu_id      | N FK     | Viittaus Lippu-tauluun         |
| Nimi          | C/150    | Tapahtuman nimi                |
| Katuosoite    | C/150    | Tapahtuman katuosoite          |
| Jarjestaja    | C/150 FK | Viittaus Järjestäjä-tauluun    |
| AlkamisPvm    | D        | Tapahtuman alkamispäivämäärä   |
| PaattymisPvm  | D        | Tapahtuman päättymispäivämäärä |
| Hinta         | N        | Tapahtuman hinta               |
| Lisätiedot    | C/500    | Muut lisätiedot                |

Järjestäjä
Järjestäjä-taulu sisältää tapahtumien järjestäjät

| Kenttä         | Tyyppi | Kuvaus                    |
| -------------- | ------ | ------------------------- |
| Jarjestaja_id  | AN PK  | Järjestäjän tunniste      |
| Nimi           | C/150  | Järjestäjän nimi          |
| Yhteyshenkilö  | C/150  | Järjestäjän yhteyshenkilö |
| Sähköposti     | C/150  | Järjestäjän sähköposti    |
| Puhelin        | C/15   | Järjestäjän puhelinnumero |

## Tekninen kuvaus

Teknisessä kuvauksessa esitetään järjestelmän toteutuksen suunnittelussa tehdyt tekniset ratkaisut, esim.

    Missä mikäkin järjestelmän komponentti ajetaan (tietokone, palvelinohjelma) ja komponenttien väliset yhteydet (vaikkapa tähän tyyliin: https://security.ufl.edu/it-workers/risk-assessment/creating-an-information-systemdata-flow-diagram/)
    Palvelintoteutuksen yleiskuvaus: teknologiat, deployment-ratkaisut yms.
    Keskeisten rajapintojen kuvaukset, esimerkit REST-rajapinta. Tarvittaessa voidaan rajapinnan käyttöä täsmentää UML-sekvenssikaavioilla.
    Toteutuksen yleisiä ratkaisuja, esim. turvallisuus.

Tämän lisäksi

    ohjelmakoodin tulee olla kommentoitua
    luokkien, metodien ja muuttujien tulee olla kuvaavasti nimettyjä ja noudattaa johdonmukaisia nimeämiskäytäntöjä
    ohjelmiston pitää olla organisoitu komponentteihin niin, että turhalta toistolta vältytään

## Testaus
Tässä kohdin selvitetään, miten ohjelmiston oikea toiminta varmistetaan testaamalla projektin aikana: millaisia testauksia tehdään ja missä vaiheessa. Testauksen tarkemmat sisällöt ja testisuoritusten tulosten raportit kirjataan erillisiin dokumentteihin.

Tänne kirjataan myös lopuksi järjestelmän tunnetut ongelmat, joita ei ole korjattu.
## Asennustiedot

Järjestelmän asennus on syytä dokumentoida kahdesta näkökulmasta:

    järjestelmän kehitysympäristö: miten järjestelmän kehitysympäristön saisi rakennettua johonkin toiseen koneeseen

    järjestelmän asentaminen tuotantoympäristöön: miten järjestelmän saisi asennettua johonkin uuteen ympäristöön.

Asennusohjeesta tulisi ainakin käydä ilmi, miten käytettävä tietokanta ja käyttäjät tulee ohjelmistoa asentaessa määritellä (käytettävä tietokanta, käyttäjätunnus, salasana, tietokannan luonti yms.).
## Käynnistys- ja käyttöohje

Tyypillisesti tässä riittää kertoa ohjelman käynnistykseen tarvittava URL sekä mahdolliset kirjautumiseen tarvittavat tunnukset. Jos järjestelmän käynnistämiseen tai käyttöön liittyy joitain muita toimenpiteitä tai toimintajärjestykseen liittyviä asioita, nekin kerrotaan tässä yhteydessä.

Usko tai älä, tulet tarvitsemaan tätä itsekin, kun tauon jälkeen palaat järjestelmän pariin !

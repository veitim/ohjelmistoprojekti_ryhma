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

### Tarina 1

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

## Käyttötapaukset

### Lippujen myynti
    Myyjä valitsee oikeat liput ja lipputyypit, järjestelmä tekee tarkastukseen lippujen myyntitilasta ja merkitsee ne myydyiksi tietokantaan. 
### Myyntien selaaminen
    Admin ja myyjä pääsevät selaamaan myyntejä.
### Myyntien muokkaaminen ja poistaminen
    Admin voi muokata ja poistaa myyntejä tietokannasta.
### Kirjautuminen ja tunnistautuminen
    Myyjä ja admin kirjautuvat järjestelmään omilla tunnuksillaan ja pääsevät käsiksi heidän rooleille asetettuihin API-päätteisiin. 
### Lipputyyppien ja tapahtumien hallinta
    Admin pääsee hallinnoimaan tapahtumia ja lipputyyppejä.



## Käyttöliittymä

Tärkeimmät käyttöliittymän näkymät:
- Etusivu ja kirjautuminen
- Käyttäjän etusivu
- Toimintonäkymä(Myynti ja osto tilanteissa)
- Listaus- tai hakunäkymä
- profiilinäkymä

## Tietokanta

Järjestäjä
Järjestäjä-taulu sisältää tapahtumien järjestäjät

| Kenttä        | Tyyppi          | Kuvaus                     |
| ------------- | --------------- | -------------------------- |
| jarjestaja_id | N PK, NOT NULL  | Järjestäjän tunniste       |
| nimi          | C/150, NOT NULL | Järjestäjän nimi           |
| yhteyshenkilo | C/150           | Järjestäjän yhteyshenkilö  |
| sahkoposti    | C/150, NOT NULL | Järjestäjän sähköposti     |
| puhelin       | C/15            | Järjestäjän puhelinnumero  |
| tapahtumat    | REL (1–N)       | Viittaus Tapahtuma-tauluun |

Käyttäjä
Käyttäjä-taulu sisältää käyttäjän tiedot

| Kenttä       | Tyyppi              | Kuvaus                          |
| ------------ | ------------------- | ------------------------------- |
| kayttaja_id  | N PK                | Käyttäjän yksilöllinen tunniste |
| etunimi      | C/50, NOT NULL      | Käyttäjän etunimi               |
| sukunimi     | C/50, NOT NULL      | Käyttäjän sukunimi              |
| katuosoite   | C/150, NOT NULL     | Käyttäjän katuosoite            |
| syntymaaika  | DATE, NOT NULL      | Käyttäjän syntymäaika           |
| sahkoposti   | C/100, NOT NULL     | Käyttäjän sähköpostiosoite      |
| puhelinnro   | C/15                | Käyttäjän puhelinnumero         |
| lisatieto    | C/500               | Muut lisätiedot                 |
| postinumero  | N FK                | Viittaus Postinumero-tauluun    |
| username     | C, NOT NULL, UNIQUE | Käyttäjän tunnus järjestelmään  |
| passwordHash | C, NOT NULL         | Käyttäjän salasana (kryptattu)  |
| rooli        | C, NOT NULL         | Käyttäjän rooli (USER / ADMIN)  |
| myynnit      | REL (1–N)           | Viittaus Myynti-tauluun.        |

Lippu
Lippu-taulu sisältää liput tapahtumiin

| Kenttä      | Tyyppi         | Kuvaus                            |
| ----------- | -------------- | --------------------------------- |
| lippu_id    | N PK, NOT NULL | Lipun tunniste                    |
| lipputyyppi | N FK, NOT NULL | Viittaus LippuTyyppi-tauluun      |
| paikka      | C/30           | Lipun paikka                      |
| kaytetty    | B, NOT NULL    | Lipun tila (käytetty/käyttämätön) |
| koodi       | C, NOT NULL    | Lipun koodi                       |
| myyntirivit | REL (1–N)      | Viittaus Myyntirivi-tauluun       |

LippuTyyppi
LippuTyyppi-taulu sisältää tapahtumien erilaiset lipputyypit

| Kenttä    | Tyyppi          | Kuvaus                     |
| --------- | --------------- | -------------------------- |
| tyyppi_id | N PK, NOT NULL  | Lipputyypin tunniste       |
| nimi      | C/150, NOT NULL | Lipputyypin nimi           |
| hinta     | N               | Lipputyypin hinta          |
| tapahtuma | N FK, NOT NULL  | Viittaus Tapahtuma-tauluun |
| lippu     | REL (1–N)       | Viittaus Lippu-tauluun     |

Myynti
Myynti-taulu sisältää asiakkaan tekemät ostotiedot

| Kenttä      | Tyyppi         | Kuvaus                      |
| ----------- | -------------- | --------------------------- |
| myyntiId    | N PK, NOT NULL | Myynnin tunniste            |
| kayttaja    | N FK, NOT NULL | Viittaus Kayttaja-tauluun   |
| paivamaara  | DATE, NOT NULL | Myynnin päivämäärä          |
| maksutapa   | C/50, NOT NULL | Maksutapa                   |
| summa       | N              | Myynnin summa               |
| myyntirivit | REL (1–N)      | Viittaus Myyntirivi-tauluun |

Myyntirivi
Myyntirivi-taulu sisältää yksittäiset myynnit

| Kenttä    | Tyyppi         | Kuvaus                  |
| ----------| -------------- | ----------------------- |
| Rivi_id   | N PK, NOT NULL | Myyntirivin tunniste    |
| Myynti_id | N FK, NOT NULL | Viittaus Myynti-tauluun |
| Lippu_id  | N FK, NOT NULL | Viittaus Lippu-tauluun  |

Postinumero
Postinumero-taulu sisältää postinumeron tiedot

| Kenttä           | Tyyppi           | Kuvaus                    |
| ---------------- | ---------------- | ------------------------- |
| postinumero      | C/5 PK, NOT NULL | Postinumero               |
| postitoimipaikka | C/50, NOT NULL   | Postitoimipaikan nimi     |
| kayttajat        | REL (1–N)        | Viittaus Kayttaja-tauluun |

Tapahtuma
Tapahtuma-taulu sisältää tapahtumatiedot

| Kenttä       | Tyyppi          | Kuvaus                         |
| ------------ | --------------- | ------------------------------ |
| tapahtumaId  | N PK, NOT NULL  | Tapahtuman tunniste            |
| nimi         | C/150, NOT NULL | Tapahtuman nimi                |
| katuosoite   | C/150, NOT NULL | Tapahtuman katuosoite          |
| alkamisPvm   | DATE, NOT NULL  | Tapahtuman alkamispäivämäärä   |
| paattymisPvm | DATE, NOT NULL  | Tapahtuman päättymispäivämäärä |
| lisatiedot   | C/500           | Muut lisätiedot                |
| paikkamaara  | N, NOT NULL     | Tapahtuman paikkamäärä         |
| jarjestaja   | N FK, NOT NULL  | Viittaus Järjestäjä-tauluun    |
| lipputyyppi  | REL (1–N)       | Viittaus LippuTyyppi-tauluun   |

## Tekninen kuvaus

Spring Bootilla toteutettu sovellus, jossa käytetään RESTia backendin ja frontendin kommunikoinnissa. Frontend toimii erillisenä React-projektina. React kommunikoi RESTin kanssa ja tallentaa tapahtumat MySQL. 
Käytetyt teknologiat: Spring Boot, Java, Spring Data JPA, Spring Security, React, MySQL.

React toimii käyttäjän selaimessa, eli näyttää myyntinäkymän ja tekee REST-kutsuja.
REST vastaanottaa pyynnöt ja syöttäää ne Service-kerrokselle.
Service-kerros tarkastaa käyttäjän, varaa lipun ja hoitaa myyntitapahtuman.
Model ja Repository- kerrokset hallitsevat tietokantaa CRUD toiminnoilla olioille. 
Security-kerros autentikoi, auktorisoi ja valvoo CORS-säännöksiä. 
Tietokanta-kerros tallentaa kaikki tiedot. 

Järjestely:
    Model: Entiteetit
    Service: Toiminnot
    Controller: REST-kontrollerit
    SecurityConfig: autentikaatio ja auktorisaatio

Kommentointi:
    Kommentointi suoraan koodin pätkän eteen. 

## Testaus

Ohjelmaa testataan yksikkö, integraatio ja E2E testeillä. Testejä luodaan tuotetta kehittäessä/heti kun sitä voi testata. Testausta suoritetaan myös manuaalisesti.
Testien tavoite on varmentaa ohjelman tai sen osien toiminta, sekä löytää virheet.
Testaukseen käytettävät teknologiat: JUnit, Mockito, Spring Data JPA, Spring Boot Test, MockMvc.

## Asennustiedot

Järjestelmän asennus on syytä dokumentoida kahdesta näkökulmasta:

    järjestelmän kehitysympäristö: miten järjestelmän kehitysympäristön saisi rakennettua johonkin toiseen koneeseen

    järjestelmän asentaminen tuotantoympäristöön: miten järjestelmän saisi asennettua johonkin uuteen ympäristöön.

Asennusohjeesta tulisi ainakin käydä ilmi, miten käytettävä tietokanta ja käyttäjät tulee ohjelmistoa asentaessa määritellä (käytettävä tietokanta, käyttäjätunnus, salasana, tietokannan luonti yms.).
## Käynnistys- ja käyttöohje

Tyypillisesti tässä riittää kertoa ohjelman käynnistykseen tarvittava URL sekä mahdolliset kirjautumiseen tarvittavat tunnukset. Jos järjestelmän käynnistämiseen tai käyttöön liittyy joitain muita toimenpiteitä tai toimintajärjestykseen liittyviä asioita, nekin kerrotaan tässä yhteydessä.

Usko tai älä, tulet tarvitsemaan tätä itsekin, kun tauon jälkeen palaat järjestelmän pariin !

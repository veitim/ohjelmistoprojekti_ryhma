# TicketGuru-projekti

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

## Johdanto

Projektin aiheena on luoda järjestelmä TicketGuru lipunmyyntipalvelulle. Asiakkaana on lipputoimisto, TicketGurun omistaja. Järjestelmältä halutaan mahdollisuus lipunmyyntiin, tapahtumien järjestelyyn, lippujen tulostamiseen, ja lippuihin koodit jotka kuitataan tapahtuman ovella.

 Järjestelmä toteutetaan Spring Boot ympäristössä.

Lähtökohtaisesti järjestelmä pyritään saada toimimaaan kaikilla päätelaitetyypeillä. Jatkokehityksessä on tarkoituksena luoda myös verkkokauppa lipunostoa varten.

## Järjestelmän määrittely

## Käyttäjäroolit:

### Asiakas

Epäsuorasti käyttää järjestelmää. Pyytää lipunmyyjältä lipun/liput tiettyyn tapahtumaan ja maksaa ne.

### Lipunmyyjä

Lipunmyyjä myy asiakkaan valitsemalle tapahtumalle lipun/liput ja tulostaa tarvittaessa ne. Lipunmyyjä pystyy tarkastelemaan tapahtumia ja tekemään myyntitoimintoja.

### Lipuntarkastaja

Lipuntarkastaja tarkastaa lipun aitouden ja merkitsee lipun käytetksi, jotta yhtä lippua voi käyttää vain kerran.

### Järjestelmän ylläpitäjä

Pitää huolen siitä, että järjestelmä toimii oikein. Pystyy lisäämään, muokkaamaan ja poistamaan tapahtumia. Pystyy myös tekemään saman myynnin suhteen.

### Järjestäjä

Epäsuorasti käyttää järjestelmää. Ilmoittaa järjestelmän ylläpitäjälle tapahtuman tiedot, lipputyypit ja hinnat. 

## Käyttötapaus


## Käyttäjätarinat

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

![kayttoliittyma](images/Käyttöliittymäkaava.png)

## Tietokanta

![tietokanta](images/tietokantakaavio.png)

Järjestäjä
Järjestäjä-taulu sisältää tapahtumien järjestäjät

| Kenttä        | Tyyppi           | Kuvaus                     |
| ------------- | ---------------- | -------------------------- |
| jarjestaja_id | Long, PK         | Järjestäjän tunniste       |
| nimi          | String, NOT NULL | Järjestäjän nimi           |
| yhteyshenkilo | String           | Järjestäjän yhteyshenkilö  |
| sahkoposti    | String, NOT NULL | Järjestäjän sähköposti     |
| puhelin       | String           | Järjestäjän puhelinnumero  |

Käyttäjä
Käyttäjä-taulu sisältää käyttäjän tiedot

| Kenttä       | Tyyppi                   | Kuvaus                          |
| ------------ | ------------------------ | ------------------------------- |
| kayttaja_id  | Long, PK                 | Käyttäjän yksilöllinen tunniste |
| etunimi      | String, NOT NULL         | Käyttäjän etunimi               |
| sukunimi     | String, NOT NULL         | Käyttäjän sukunimi              |
| katuosoite   | String, NOT NULL         | Käyttäjän katuosoite            |
| syntymaaika  | LocalDate, NOT NULL      | Käyttäjän syntymäaika           |
| sahkoposti   | String, NOT NULL         | Käyttäjän sähköpostiosoite      |
| puhelinnro   | String                   | Käyttäjän puhelinnumero         |
| lisatieto    | String                   | Muut lisätiedot                 |
| postinumero  | String                   | Viittaus Postinumero-tauluun    |
| username     | String, NOT NULL, UNIQUE | Käyttäjän tunnus järjestelmään  |
| passwordHash | String, NOT NULL         | Käyttäjän salasana (kryptattu)  |
| rooli        | String, NOT NULL         | Käyttäjän rooli (USER / ADMIN)  |

Lippu
Lippu-taulu sisältää liput tapahtumiin

| Kenttä        | Tyyppi            | Kuvaus                            |
| ------------- | ----------------- | --------------------------------- |
| lippu_id      | Long PK, NOT NULL | Lipun tunniste                    |
| lipputyyppi   | Long FK, NOT NULL | Viittaus LippuTyyppi-tauluun      |
| paikka        | String            | Lipun paikka                      |
| kaytetty      | Boolean, NOT NULL | Lipun tila (käytetty/käyttämätön) |
| koodi         | String, NOT NULL  | Lipun koodi                       |

LippuTyyppi
LippuTyyppi-taulu sisältää tapahtumien erilaiset lipputyypit

| Kenttä      | Tyyppi            | Kuvaus                     |
| ----------- | ----------------- | -------------------------- |
| tyyppi_id   | Long PK, NOT NULL | Lipputyypin tunniste       |
| nimi        | String, NOT NULL  | Lipputyypin nimi           |
| hinta       | Double            | Lipputyypin hinta          |
| tapahtumaId | Long FK, NOT NULL | Viittaus Tapahtuma-tauluun |

Myynti
Myynti-taulu sisältää asiakkaan tekemät ostotiedot

| Kenttä      | Tyyppi              | Kuvaus                      |
| ----------- | ------------------- | --------------------------- |
| myyntiId    | Long PK, NOT NULL   | Myynnin tunniste            |
| kayttaja_id | Long FK, NOT NULL   | Viittaus Kayttaja-tauluun   |
| paivamaara  | LocalDate, NOT NULL | Myynnin päivämäärä          |
| maksutapa   | String, NOT NULL    | Maksutapa                   |
| summa       | Double              | Myynnin summa               |

Myyntirivi
Myyntirivi-taulu sisältää yksittäiset myynnit

| Kenttä    | Tyyppi            | Kuvaus                  |
| ----------| ----------------- | ----------------------- |
| rivi_id   | Long PK, NOT NULL | Myyntirivin tunniste    |
| myyntiId  | Long FK, NOT NULL | Viittaus Myynti-tauluun |
| lippu_id  | Long FK, NOT NULL | Viittaus Lippu-tauluun  |

Postinumero
Postinumero-taulu sisältää postinumeron tiedot

| Kenttä           | Tyyppi              | Kuvaus                    |
| ---------------- | ------------------- | ------------------------- |
| postinumero      | String PK, NOT NULL | Postinumero               |
| postitoimipaikka | String, NOT NULL    | Postitoimipaikan nimi     |


Tapahtuma
Tapahtuma-taulu sisältää tapahtumatiedot

| Kenttä        | Tyyppi            | Kuvaus                         |
| ------------- | ----------------- | ------------------------------ |
| tapahtumaId   | Long PK, NOT NULL | Tapahtuman tunniste            |
| nimi          | String, NOT NULL  | Tapahtuman nimi                |
| katuosoite    | String, NOT NULL  | Tapahtuman katuosoite          |
| alkamisPvm    | Date, NOT NULL    | Tapahtuman alkamispäivämäärä   |
| paattymisPvm  | Date, NOT NULL    | Tapahtuman päättymispäivämäärä |
| lisatiedot    | String            | Muut lisätiedot                |
| paikkamaara   | Long, NOT NULL    | Tapahtuman paikkamäärä         |
| jarjestaja_id | Long FK, NOT NULL | Viittaus Järjestäjä-tauluun    |

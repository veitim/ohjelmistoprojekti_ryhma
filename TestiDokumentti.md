# Testausdokumentaatio  
**Sovellus:** TicketGuru – Spring Boot -lipunmyyntijärjestelmä  

---

## 1. Testitasot ja vaatimukset

### Testitasot

| Testitaso        | Kuvaus                                                                 |
|------------------|------------------------------------------------------------------------|
| Yksikkötestit    | Service- ja controller-kerros, mockatut repositoryt                    |
| Integraatiotestit| MockMvc + Spring Security, profiili testi                             |
| E2E (manuaali)   | API + tietokanta + käyttöliittymä + kirjautuminen/roolit              |

### Yleiset vaatimukset

- Kaikki endpointit vaativat **autentikoidun käyttäjän**.
- **USER**
  - Saa tehdä `GET`-pyyntöjä.
  - Saa luoda myynnin `POST /api/myynnit`.
- **ADMIN**
  - Saa tehdä kaikki toiminnot (GET, POST, PUT, PATCH, DELETE).

Testauksessa varmistettiin:

- Oikeat HTTP-koodit:  
  `200`, `201`, `204`, `400`, `401`, `403`, `404`
- Data tallentuu ja päivittyy oikein tietokantaan.
- Kapasiteettia ei ylitetä (tapahtuman paikkamäärä).
- Syötevalidoinnit toimivat (vääränmuotoinen data hylätään).
- Roolipohjainen käyttöoikeus toimii (USER vs ADMIN).

---

## 2. Automaattiset yksikkötestit – Service

### 2.1 LippuMyyntiServiceTest

**Paketti:** `com.example.ticketguru`  
**Luokka:** `LippuMyyntiServiceTest`  
**Mockit:** `LippuRepository`, `LippuTyyppiRepository`, `MyyntiRepository`, `MyyntiriviRepository`, `KayttajaRepository`

Testien tavoite on varmistaa, että myyntien luontilogiikka toimii oikein:

- **Onnistunut myynti**
  - Käyttäjä löytyy ID:llä.
  - Lipputyyppi löytyy ja liittyy tapahtumaan.
  - Tapahtuman paikkamäärä ei ylity (myytyjen lippujen määrä + uudet liput ≤ kapasiteetti).
  - Odotetaan:
    - Myynti tallennetaan (summa laskettu oikein, esim. 2 × 50 € = 100 €).
    - Oikea maksutapa, käyttäjä ja päivämäärä (`LocalDate.now()`).
    - Oikea määrä lippu- ja myyntirivitalletuksia.

- **Ei tarpeeksi paikkoja**
  - Tapahtuman kapasiteetti on jo lähes täynnä.
  - Uusien lippujen myynti ylittäisi kapasiteetin.
  - Odotetaan: `ResponseStatusException` heitetään, myyntiä ei luoda.

- **Käyttäjää ei löydy**
  - `KayttajaRepository.findById` palauttaa tyhjän.
  - Odotetaan: poikkeus (myyntiä ei luoda ilman olemassa olevaa käyttäjää).

---

## 3. Automaattiset yksikkötestit – Controllerit

### 3.1 LippuRestControllerTest

**Paketti:** `com.example.ticketguru`  
**Luokka:** `LippuRestControllerTest`  
**Controller:** `LippuRestController`  
**Mock:** `LippuRepository`

Testit varmistavat, että lipun REST-endpointit palauttavat oikeat koodit ja käyttävät repositorya oikein.

Kattavuus:

- **Haku**
  - `GET /api/liput`  
    - Palauttaa listan lipuista (`200 OK`).
  - `GET /api/liput/{id}`  
    - Jos löytyy → `200 OK` + lippu.  
    - Jos ei löydy → `404 NOT_FOUND`.
  - `GET /api/liput?koodi={koodi}`  
    - Jos löytyy → `200 OK` + lippu.

- **Luonti**
  - `POST /api/liput`  
    - Lippu tallennetaan repositoryyn.  
    - Palautetaan tallennettu lippu (status `200/201` toteutuksesta riippuen).

- **Päivitys**
  - `PUT /api/liput/{id}`  
    - Jos lippu löytyy:
      - Lippu päivitetään ja tallennetaan → `200 OK`.
    - Jos ei löydy:
      - Ei tallennusta → `404 NOT_FOUND`.

- **Poisto**
  - `DELETE /api/liput/{id}`  
    - Jos lippu löytyy → poistetaan → `204 NO_CONTENT`.  
    - Jos ei löydy → `404 NOT_FOUND`.

- **Osittainen päivitys (PATCH)**
  - `PATCH /api/liput/{id}` (erityisesti `kaytetty`-kenttä):
    - Jos lippu löytyy:
      - `kaytetty` päivitetään, tallennetaan → `200 OK`.
    - Jos ei löydy:
      - `404 NOT_FOUND`.

---

### 3.2 MyyntiRestControllerTest

**Paketti:** `com.example.ticketguru`  
**Luokka:** `MyyntiRestControllerTest`  
**Controller:** `MyyntiRestController`  
**Mockit:** `MyyntiRepository`, `MyyntiService`

Testit varmistavat myynti-endpointtien peruskäyttäytymisen:

- **Kaikkien myyntien haku**
  - `GET /api/myynnit`  
  - `findAll()` kutsutaan, tulos ei null → `200 OK`.

- **Yksittäinen myynti ID:llä**
  - `GET /api/myynnit/{id}`
  - Jos myynti löytyy:
    - `findById(id)` palauttaa arvon → `200 OK` + myynti.
  - Jos ei löydy:
    - `404 NOT_FOUND` + tyhjä body.

- **Myynnin poisto**
  - `DELETE /api/myynnit/{id}`
  - Jos ID on olemassa:
    - `existsById(id) == true` → `deleteById(id)` + `204 NO_CONTENT`.
  - Jos ID puuttuu:
    - `existsById(id) == false` → ei poistokutsua + `404 NOT_FOUND`.

- **Myynnin päivitys**
  - `PUT /api/myynnit/{id}`
  - Jos myynti löytyy:
    - `findById(id)` → myynti  
    - `myyntiService.luoMyynti(updated)` kutsutaan  
    - `200 OK`.
  - Jos ei löydy:
    - `findById(id)` → tyhjä  
    - `luoMyynti` ei kutsuta  
    - `404 NOT_FOUND`.

---

## 4. Integraatiotestit – MockMvc

### 4.1 TapahtumaIntegrationTest

- **Paketti:** `com.example.ticketguru`  
- **Luokka:** `TapahtumaIntegrationTest`

Annotaatiot:

- `@SpringBootTest`  
- `@AutoConfigureMockMvc`  
- `@ActiveProfiles("test")`

Testit ajetaan oikeaa Spring-kontekstia vasten käyttäen `MockMvc`-oliota ja Spring Securityn mock-käyttäjiä (`@WithMockUser`).

#### Testatut tapaukset

**GET `/api/tapahtumat`**

- **Käyttäjä:** rooli `USER`  
- **Odotus:** `200 OK`  
- **Selite:** tavallinen käyttäjä saa listata tapahtumat.

---

**POST `/api/tapahtumat`**

- **Käyttäjä:** rooli `ADMIN`  
- **Body:** tapahtuma-JSON (nimi, osoite, päivämäärät, lisätiedot, paikkamäärä, järjestäjä).  
- **Odotus:** `200 OK`  
- **Selite:** admin voi luoda tapahtuman.

---

**PUT `/api/tapahtumat/{id}`**

- **Käyttäjä:** rooli `ADMIN`  
- **Body:** tapahtuma-JSON  
- **Odotus:** `200 OK`  
- **Selite:** olemassa oleva tapahtuma päivittyy.

---

**DELETE `/api/tapahtumat/{id}`**

- **Käyttäjä:** rooli `ADMIN`  
- **Odotus:** `204 NO_CONTENT`  
- **Selite:** tapahtuma poistuu.

---

Näillä integraatiotesteillä varmistetaan erityisesti, että:

- Security-konfiguraatio (`USER`/`ADMIN`-oikeudet) toimii.  
- HTTP-kerros ja controllerit toimivat yhdessä Spring-kontekstin kanssa.  
- `test`-profiilia käytetään erillisenä testausympäristönä.

---

## 5. Manuaaliset E2E-testit

### 5.1 API + tietokanta (Postman)

Postmanilla on manuaalisesti testattu kaikki keskeiset REST-resurssit:

- `jarjestajat`
- `kayttajat`
- `liput`
- `lipputyypit`
- `myynnit`
- `myyntirivit`
- `postinumerot`
- `tapahtumat`

#### Testatut operaatiot

- **GET** – listaus ja haku ID:llä  
- **POST** – uusien resurssien luonti  
- **PUT / PATCH** – resurssien päivitys  
- **DELETE** – resurssien poisto  

#### Tarkistettu, että

- Data syntyy oikein tietokantaan.  
- Päivitykset näkyvät seuraavissa hauissa.  
- Poistetut rivit eivät enää näy.  
- Tapahtuman paikkamäärä ei ylity lipunmyynnissä.  
- Uniikkiusvaatimukset (esim. lipun koodi) säilyvät.

---

### 5.2 Validoinnit

Manuaalisesti testattuja validointiskenaarioita:

- Kirjaimia numeerisissa kentissä (esim. puhelinnumero).  
- Puuttuvat pakolliset arvot (esim. nimi, käyttäjätunnus, rooli).  
- Vääränmuotoiset arvot (päivämäärät, tyyppivirheet).

**Odotettu käyttäytyminen:**

- `400 Bad Request`, kun pyyntö on virheellinen.  
- Virheellistä dataa ei tallenneta tietokantaan.

---

### 5.3 Login, autentikointi ja roolit

#### USER-rooli

**Saa:**

- `GET`-kutsut (tapahtumat, liput, lipputyypit jne.).  
- Myynnin luonnin: `POST /api/myynnit`.

**Ei saa:**

- ADMIN-oikeuksia vaativia operaatioita (esim. `POST /api/tapahtumat`, `DELETE /api/kayttajat/{id}`).

**Väärissä toiminnoissa:**

- Palautuu `403 Forbidden` tai `401 Unauthorized`.

#### ADMIN-rooli

**Saa:**

Luoda, päivittää ja poistaa kaikkia keskeisiä resursseja:

- tapahtumat  
- järjestäjät  
- käyttäjät  
- liput  
- lipputyypit  
- postinumerot  
- myynnit  
- myyntirivit  

Manuaalisesti on vahvistettu, että API-dokumentin mukaiset ADMIN-toiminnot toimivat.

---

### 5.4 Käyttöliittymä (UI) – lipunmyynnin E2E

Käyttöliittymä on testattu käsin lipunmyynnin perusprosessilla:

1. Kirjaudu sisään käyttöliittymään.  
2. Valitse tapahtuma ja lipputyyppi.  
3. Syötä lipun määrä ja maksutapa.  
4. Suorita lipunmyynti.

#### Tarkistettu, että

- Uudet liput syntyvät tietokantaan.  
- Myynti tallentuu oikein (summa, päivämäärä, käyttäjä).  
- Väärät syötteet eivät kaada sovellusta (ei `500`-virheitä).  
- Lippujen tilan/käytön (esim. `kaytetty = true`) päivitys heijastuu backendin ja UI:n välillä odotetusti.


 

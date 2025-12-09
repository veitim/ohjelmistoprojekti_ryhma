
# API-dokumentaatio

## Endpointteja

| Endpoints         | Methods                      |
| -------------     |:----------------------------:| 
| jarjestajat       | GET, POST, PUT, DELETE       |
| kayttajat         | GET, POST, PUT, DELETE       |
| liput             | GET, POST, PUT, PATCH, DELETE       |
| lipputyypit       | GET, POST, PUT, DELETE       |
| myynnit           | GET, POST, PUT, DELETE       |
| myyntirivit       | GET, POST, PUT, DELETE       |
| postinumerot      | GET, POST, PUT, DELETE       |
| tapahtumat        | GET, POST, PUT, DELETE       |

## Vaatimukset

* Kaikki endpointit vaativat autentikoidun käyttäjän.
* USER: tason käyttäjä voi tehdä GET-pyyntöjä ja luoda myyntitapahtuman POST-pyynnöllä.
* ADMIN: Voi tehdä kaikkea.

## Käyttöesimerkki

* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/{endpoint}
* Metodit: GET, POST, PUT, PATCH, DELETE
* Esimerkki: GET: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/jarjestajat
* Result:

        {
            "jarjestaja_id": 1,
            "nimi": "uber",
            "yhteyshenkilo": "pekka",
            "sahkoposti": "uber@uber",
            "puhelin": "+3580000",
            "tapahtumat": 
        }
            {
                "tapahtuma_id": 1,
                "nimi": "hälläväliä",
                "katuosoite": "koti",
                "alkamisPvm": "2025-01-07",
                "paattymisPvm": "2025-01-08",
                "lisatiedot": "ikärajaa ei ole",
                "paikkamaara": 200,
            }

* PUT ja DELETE endpointteja haetaan id:n peruteella
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/{endpoint}/{id}
* Esimerkki: DELETE: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/kayttajat/3
* Result: 203 no content. Käyttäjä poistettu.

# Endpoint: jarjestajat

### HUOM POST, PUT ja DELETE vaativat ADMIN oikeudet.

### Hae kaikki järjestäjät

### Method: GET

* Vaatimus: autentikoitu käyttäjä
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/jarjestajat"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/jarjestajat"

### Hae järjestäjä ID:llä

* Vaatimus: autentikoitu käyttäjä
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/jarjestajat/{id}"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/jarjestajat/1"

### Lisää uusi järjestäjä

### Method: POST

* URL: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/jarjestajat
* Body:

        {
            "nimi": "Konserttiyhtiö Oy",
            "yhteyshenkilo": "Matti Meikäläinen",
            "sahkoposti": "matti@konserttiyhtio.fi",
            "puhelin": "0401234567"
        }

* Pakollinen arvo: "nimi"
* Result: 201 Created

        {
            "jarjestaja_id": {id},
            "nimi": "Konserttiyhtiö Oy",
            "yhteyshenkilo": "Matti Meikäläinen",
            "sahkoposti": "matti@konserttiyhtio.fi",
            "puhelin": "0401234567"
        }


### Muokkaa järjestäjää

### Method: PUT

* URL: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/jarjestajat/{id}
* Body:

        {
            "nimi": "MUUTOS",
            "yhteyshenkilo": "MUUTOS",
            "sahkoposti": "matti@konserttiyhtio.fi",
            "puhelin": "0401234567"
        }

* Result: 200 OK   
    
### Poista järjestäjä

### Method: DELETE

* URL: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/jarjestajat/{id}

* Results:
    * 201 Created. Pyyntö onnistunut.
    * 400 Bad Request. Pyyntö tehty väärin tai nimi puuttuu.
    * 403 Forbidden. Ei oikeuksia, eli yritetään tehdä väärillä oikeuksilla.
    * 404 Not Found. Jarjestajaa ei löydy (ID:tä ei ole)

# Endpoint: kayttajat

### HUOM POST, PUT ja DELETE vaativat ADMIN oikeudet.

### Hae käyttäjät

### Method: GET

* Vaatimus: autentikoitu käyttäjä
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/kayttajat"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/kayttajat"

### Lisää uusi käyttäjä

### Method: POST

* URL: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/kayttajat
* Body:

        {
            "etunimi": "Matti",
            "sukunimi": "Meikäläinen",
            "katuosoite": "Esimerkkikatu 1",
            "syntymaaika": "1995-05-15",
            "sahkoposti": "matti@example.com",
            "puhelinnro": "0401234567",
            "lisatieto": "Ei lisätietoja",
            "postinumero": {
                "postinumero": "00100"
            },
            "username": "matti123",
            "password": "salasana123",
            "rooli": "USER"
        }

* Pakolliset arvot: "etunimi", "sukunimi", "sahkoposti", "username", "password", "rooli"
* Result: 201 Created

        {
            "kayttaja_id": {id},
            "etunimi": "Matti",
            "sukunimi": "Meikäläinen",
            "katuosoite": "Esimerkkikatu 1",
            "syntymaaika": "1995-05-15",
            "sahkoposti": "matti@example.com",
            "puhelinnro": "0401234567",
            "lisatieto": "Ei lisätietoja",
            "postinumero": {
                "postinumero": "00100",
            },
            "username": "matti123",
            "passwordHash": "$2a$10$...",
            "rooli": "USER",
            "myynnit": []
        }

### Muokkaa käyttäjää

### Method: PUT

* URL: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/kayttajat/{id}
* Body:

        {
            "etunimi": "MUUTOS",
            "sukunimi": "MUUTOS",
            "katuosoite": "Esimerkkikatu 1",
            "syntymaaika": "1995-05-15",
            "sahkoposti": "matti@example.com",
            "puhelinnro": "0401234567",
            "lisatieto": "Päivitetty",
            "postinumero": {
                "postinumero": "00100"
            },
            "username": "matti123",
            "rooli": "USER"
        }

Result: 200 OK

### Poista käyttäjä

### Method: DELETE

* URL: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/kayttajat/{id}


* Results:
    * 201 Created. Pyyntö onnistunut.
    * 400 Bad Request. Pyyntö tehty väärin tai nimi puuttuu.
    * 403 Forbidden. Ei oikeuksia, eli yritetään tehdä väärillä oikeuksilla.
    * 404 Not Found. Jarjestajaa ei löydy (ID:tä ei ole)




# Endpoint: liput

### HUOM POST, PUT, PATCH ja DELETE vaativat ADMIN oikeudet.

### Method: GET

### Hae kaikki liput

* URL: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/liput

### Lipun haku koodin perusteella

* URL: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/liput?koodi={koodi}
* Esim: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/liput?koodi=3b5dab83
* Result: 200 OK! (onnistui), 404 Not Found (Lippua ei löydy kyseisellä koodilla)

### Lipun haku id:n perusteella

* URL: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/liput/{id}

### Lisää lippu

### Method: POST

* URL: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/liput
* Body: 

            {
                "lipputyyppi": {
                    "tyyppi_id": 7
                }
            }

* Pakolliset arvot: "tapahtuma", "lipputyyppi" (olemassa olevat lipputyypit pitää tarkastaa)
* Result: 201 Created

        {
        "lippu_id": {id},
        "paikka": "Helsinki",
        "tila": true,
        "kaytetty": false,
        "tapahtuma": {
            "tapahtuma_id": 1,
            "nimi": "hälläväliä",
            "katuosoite": "koti",
            "alkamisPvm": "2025-01-07",
            "paattymisPvm": "2025-01-08",
            "lisatiedot": "ikärajaa ei ole",
            "paikkamaara": 200
        },
        "lipputyyppi": {
            "tyyppi_id": 1,
            "nimi": "Normaali",
            "hinta": 50
        },
        "myyntirivit": null
        }

### Muokkaa lippua

### Method: PUT

* URL: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/liput/{id}
* Body:

        {
        "paikka": "MUUTOS",
        "tila": false,
        "kaytetty": true,
        },
        "lipputyyppi": {
            "tyyppi_id": 2
        }

### Method: PATCH

* URL: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/liput/{id}
* Body:
        
            {    
                "kaytetty": true,
                "lipputyyppi": 
                {
                    "tyyppi_id": 4
                }
            }

* Response: 200 OK
* Tämä asettaa false tilan trueksi (eli lippu käytetty)
* Validoinnit haluavat lipuntyypin mukaan!

### Poista lippu

### Method: DELETE

URL: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/liput/{id}

* Results:
    * 201 Created. Pyyntö onnistunut.
    * 400 Bad Request. Pyyntö tehty väärin tai nimi puuttuu.
    * 403 Forbidden. Ei oikeuksia, eli yritetään tehdä väärillä oikeuksilla.
    * 404 Not Found. Jarjestajaa ei löydy (ID:tä ei ole)




# Endpoint: tapahtumat


### Hae tapahtumat

### MEthod: GET

* Vaatimus: autentikoitu käyttäjä
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/tapahtumat"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/tapahtumat"

* VirheKoodi: 404 Not Found 

### Lisää tapahtuma

### Method: POST

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/tapahtumat"
* Esimerkki: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/tapahtumat
  
  Body:
  
       {
            "nimi": "TESTAILEE2",
            "katuosoite": "koti",
            "alkamisPvm": "2025-01-07",
            "paattymisPvm": "2025-01-08",
            "lisatiedot": "ikärajaa ei ole",
            "liput": [],
            "jarjestaja": {
                "jarjestaja_id": 1
              }
        }

Response: 200

### Päivitä tapahtuma

### Method: PUT

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/tapahtumat/{id}"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/tapahtumat/1"
  
  Body:

      {
          "nimi": "hälläväliä",
          "katuosoite": "koti",
          "alkamisPvm": "2025-01-07",
          "paattymisPvm": "2025-01-08",
          "lisatiedot": "ikärajaa ei ole",
          "liput": [],
          "jarjestaja": {
              "jarjestaja_id": 1
          }
      }

### Poista tapahtuma

### Method: DELETE

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/tapahtumat/{id}"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/tapahtumat/1"



# Endpoint: Lipputyyppi

### Hae lipputyypit

### Method: GET

* Vaatimus: autentikoitu käyttäjä
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/lipputyypit"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/lipputyypit"

### Lisää lipputyyppi

### Method: POST

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/lipputyypit"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/lipputyypit"

Body:

    {
        "nimi": "1 päivän lippu",
        "hinta": 90,
        "tapahtuma": [],
    }
 

### Päivitä lippytyyppi

### Method: PUT

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/lipputyypit/{id}"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/lipputyypit/1"

Body:

    {
        "nimi": "normilippu",
        "hinta": 30,
        "tapahtuma": [],
    }

### Poista lipputyyppi

### Method: DELETE

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/lipputyypit/{id}"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/lipputyypit/2"

# Endpoint: Myynti

### Hae kaikki myynnit

### Method: GET

* Vaatimus: autentikoitu käyttäjä
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myynnit"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myynnit"

### Hae myynti ID:llä

* Vaatimus: autentikoitu käyttäjä
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myynnit/{id}"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myynnit/1"

### Lisää myynti

### Method: POST

* Vaatimus: autentikoitu käyttäjä
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myynnit"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myynnit"

Body:

      {
          "kayttaja": {
              "kayttaja_id": 1
          },
          "paivamaara": "2025-09-30",
          "maksutapa": "Kortti",
          "summa":
          "myyntirivit": []
      }

### Päivitä myynti

### Method: PUT

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myynnit/{id}"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myynnit/1"

Body:
      
        {
          "kayttaja_id": 1,
          "maksutapa": "kortti",
          "kayttaja": {"kayttaja_id": 1},
          "rivit": [
            { "tyyppi_id": 2, "maara": 10 },
            { "tyyppi_id": 5, "maara": 10 }
          ]
        }

### Poista myynti

### Method: DELETE

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myynnit/{id}"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myynnit/1"

# Endpoint: Postinumero

### Hae postinumerot/postitoimipaikat

### Method: GET

* Vaatimus: autentikoitu käyttäjä
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/postinumerot"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/postinumerot"

### Lisää postinumero/postitoimipaikka

### Method: POST

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/postinumerot"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/postinumerot"

Body:

    {
        "postinumero": "33100",
        "postitoimipaikka": "Tampere"
    }
 

### Päivitä postinumero/postitoimipaikka

### Method: PUT

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/postinumerot/{postinumero}"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/postinumerot/00980"

Body:

    {
        "postinumero": "33200",
        "postitoimipaikka": "Tampere"
    }

### Poista postinumero/postitoimipaikka

### Method: DELETE

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/postinumerot/{postinumero}"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/postinumerot/00980"

# Endpoint: Myyntirivi

### Hae kaikki myyntirivit

### Method: GET

* Vaatimus: autentikoitu käyttäjä
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myyntirivit"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myyntirivit"

### Hae myyntirivi ID:llä

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myyntirivit/{id}"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myyntirivit/1"

### Lisää uusi myyntirivi

### Method: POST

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myyntirivit"
* Metodi: POST
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myyntirivit"

Body:

    {
        "myynti": {
            "myynti_id": 1
        },
        "lippu": {
            "lippu_id": 5
        }
    }

### Päivitä myyntirivi

### Method: PUT

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myyntirivit/{id}"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myyntirivit/1"

Body:

    {
        "myynti": {
            "myynti_id": 1
        },
        "lippu": {
            "lippu_id": 1
        }
    }

### Poista myyntirivi

### Method: DELETE

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myyntirivit/{id}"
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myyntirivit/1"

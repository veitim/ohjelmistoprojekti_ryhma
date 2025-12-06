
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

* Vaatimus: autentikoitu käyttäjä
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/jarjestajat"
* Metodi: GET
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/jarjestajat"

### Hae järjestäjä ID:llä

* Vaatimus: autentikoitu käyttäjä
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/jarjestajat/{id}"
* Metodi: GET
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/jarjestajat/1"

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
            "puhelin": "0401234567",
            "tapahtumat": null
        }


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

* Vaatimus: autentikoitu käyttäjä
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/kayttajat"
* Metodi: GET
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/kayttajat"

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
                "postitoimipaikka": "Helsinki"
            },
            "username": "matti123",
            "passwordHash": "$2a$10$...",
            "rooli": "USER",
            "myynnit": []
        }

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

### Kaikki liput

* URL: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/liput

### Lipun haku koodin perusteella

* URL: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/liput?koodi={koodi}
* Esim: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/liput?koodi=3b5dab83
* Result: 200 OK! (onnistui), 404 Not Found (Lippua ei löydy kyseisellä koodilla)

### Lipun haku id:n perusteella

* URL: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/liput/{id}

### Method: POST

* URL: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/liput
* Body: 

        {
            "paikka": "Helsinki",
            "tila": true,
            "tapahtuma": {
                "tapahtuma_id": 1
            },
            "lipputyyppi": {
                "tyyppi_id": 1
            }
        }

* Pakolliset arvot: "tapahtuma", "lipputyyppi"
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

### Method: PUT

* URL: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/liput/{id}
* Body:

        {
        "paikka": "MUUTOS",
        "tila": false,
        "kaytetty": true,
        "tapahtuma": {
            "tapahtuma_id": 1
        },
        "lipputyyppi": {
            "tyyppi_id": 2
        }
        }

### Method: PATCH

* URL: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/liput/{id}
* Body:

        {
            "kaytetty": true
        }

* Response: 200 OK
* Tämä asettaa false tilan trueksi (eli lippu käytetty)

### Method: DELETE

URL: https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/liput/{id}

* Results:
    * 201 Created. Pyyntö onnistunut.
    * 400 Bad Request. Pyyntö tehty väärin tai nimi puuttuu.
    * 403 Forbidden. Ei oikeuksia, eli yritetään tehdä väärillä oikeuksilla.
    * 404 Not Found. Jarjestajaa ei löydy (ID:tä ei ole)




# Endpoint: tapahtumat


### Hae tapahtumat

* Vaatimus: autentikoitu käyttäjä
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/tapahtumat"
* Metodi: GET
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/tapahtumat"

* VirheKoodi: 404 Not Found 

### Lisää tapahtuma

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/tapahtumat"
* Metodi: POST
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

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/tapahtumat/{id}"
* Metodi: PUT
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

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/tapahtumat/{id}"
* Metodi: DELETE
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/tapahtumat/1"



# Endpoint: Lipputyyppi

### Hae lipputyypit

* Vaatimus: autentikoitu käyttäjä
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/lipputyypit"
* Metodi: GET
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/lipputyypit"

### Lisää lipputyyppi

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/lipputyypit"
* Metodi: POST
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/lipputyypit"

Body:

    {
        "nimi": "1 päivän lippu",
        "hinta": 90
    }
 

### Päivitä lippytyyppi

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/lipputyypit/{id}"
* Metodi: PUT
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/lipputyypit/1"

Body:

    {
        "nimi": "normilippu",
        "hinta": 30
    }

### Poista lipputyyppi

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/lipputyypit/{id}"
* Metodi: DELETE
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/lipputyypit/2"

# Endpoint: Myynti

### Hae kaikki myynnit

* Vaatimus: autentikoitu käyttäjä
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myynnit"
* Metodi: GET
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myynnit"

### Hae myynti ID:llä

* Vaatimus: autentikoitu käyttäjä
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myynnit/{id}"
* Metodi: GET
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myynnit/1"

### Lisää myynti

* Vaatimus: autentikoitu käyttäjä
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myynnit"
* Metodi: POST
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myynnit"

Body:

      {
          "kayttaja": {
              "kayttaja_id": 1
          },
          "paivamaara": "2025-09-30",
          "maksutapa": "Kortti",
          "tyyppi": "Verkkokauppa",
          "myyntirivit": []
      }

### Päivitä myynti

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myynnit/{id}"
* Metodi: PUT
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myynnit/1"

Body:
      
      {
          "kayttaja": {
              "kayttaja_id": 1
          },
          "paivamaara": "2025-10-01",
          "maksutapa": "Käteinen",
          "tyyppi": "Kassa",
          "myyntirivit": []
      }

### Poista myynti

ENDPOINTIT MITÄ VOI KÄYTTTÄÄ  TAPAHTUMAT; LIPUT; JNE: MENEE ESIM TILALLE

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/{ESIM}/{id}"
* Metodi: DELETE
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myynnit/1"

# Endpoint: Postinumero

### Hae postinumerot/postitoimipaikat

* Vaatimus: autentikoitu käyttäjä
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/postinumerot"
* Metodi: GET
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/postinumerot"

### Lisää postinumero/postitoimipaikka

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/postinumerot"
* Metodi: POST
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/postinumerot"

Body:

    {
        "postinumero": "33100",
        "postitoimipaikka": "Tampere"
    }
 

### Päivitä postinumero/postitoimipaikka

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/postinumerot/{postinumero}"
* Metodi: PUT
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/postinumerot/00980"

Body:

    {
        "postinumero": "33200",
        "postitoimipaikka": "Tampere"
    }

### Poista postinumero/postitoimipaikka

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/postinumerot/{postinumero}"
* Metodi: DELETE
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/postinumerot/00980"

# Endpoint: Myyntirivi

### Hae kaikki myyntirivit

* Vaatimus: autentikoitu käyttäjä
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myyntirivit"
* Metodi: GET
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myyntirivit"

### Hae myyntirivi ID:llä

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myyntirivit/{id}"
* Metodi: GET
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myyntirivit/1"

### Lisää uusi myyntirivi

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
        },
        "paivamaara": "2025-01-07",
        "summa": 59.90
    }

### Päivitä myyntirivi

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myyntirivit/{id}"
* Metodi: PUT
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myyntirivit/1"

Body:

    {
        "myynti": {
            "myynti_id": 1
        },
        "lippu": {
            "lippu_id": 1
        },
        "paivamaara": "2025-01-07",
        "summa": 59.90
    }

### Poista myyntirivi

* Vaatimus: ADMIN, oikeudet
* URL: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myyntirivit/{id}"
* Metodi: DELETE
* Esimerkki: "https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/myyntirivit/1"
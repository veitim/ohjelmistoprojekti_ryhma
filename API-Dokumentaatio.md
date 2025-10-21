# Tapahtuma

### Hae tapahtumat

* URL: "http://localhost:8080/api/tapahtumat"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/tapahtumat"

### Lisää tapahtuma

* URL: "http://localhost:8080/api/tapahtumat/ADMIN"
* Metodi: POST
* Esimerkki: http://localhost:8080/api/tapahtumat/ADMIN
  
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

### Päivitä tapahtuma

* URL: "http://localhost:8080/api/tapahtumat/ADMIN/{id}"
* Metodi: PULL
* Esimerkki: "http://localhost:8080/api/tapahtumat/ADMIN/1"
  
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

* URL: "http://localhost:8080/api/tapahtumat/ADMIN/{id}"
* Metodi: DELETE
* Esimerkki: "http://localhost:8080/api/tapahtumat/ADMIN/1"

# Lippu

### Hae liput

* URL: "http://localhost:8080/api/liput"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/liput"

### Lisää lippu

* URL: "http://localhost:8080/api/liput/ADMIN"
* Metodi: POST
* Esimerkki: http://localhost:8080/api/liput/ADMIN"

Body:

    {
        "paikka": "A1",
        "tila": true,
        "tapahtuma": {
        "tapahtuma_id": 1
    },
        "lipputyyppi": {
        "tyyppi_id": 1
    },
        "myyntirivit": []
    }

### Päivitä lippu

* URL: "http://localhost:8080/api/liput/{id}"

* Metodi: PUT

* Esimerkki: "http://localhost:8080/api/liput/1"

Body:

    {
        "paikka": "B2",
        "tila": false,
        "tapahtuma": {
        "tapahtuma_id": 1
    },
        "lipputyyppi": {
        "tyyppi_id": 1
    },
        "myyntirivit": []
    }

### Poista lippu

* URL: "http://localhost:8080/api/liput/{id}"

* Metodi: DELETE

* Esimerkki: "http://localhost:8080/api/liput/1"

Body:

    {
        "paikka": "B2",
        "tila": false,
        "tapahtuma": {
        "tapahtuma_id": 1
    },
        "lipputyyppi": {
        "tyyppi_id": 1
    },
        "myyntirivit": []
    }

# Lipputyyppi

### Hae lipputyypit

* URL: "http://localhost:8080/api/lipputyypit"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/lipputyypit"

### Lisää lipputyyppi

* URL: "http://localhost:8080/api/lipputyypit"
* Metodi: POST
* Esimerkki: "http://localhost:8080/api/lipputyypit"

Body:

    {
        "nimi": "1 päivän lippu",
        "hinta": 90
    }
 

### Päivitä lippytyyppi

* URL: "http://localhost:8080/api/lipputyypit/{id}"
* Metodi: PUT
* Esimerkki: "http://localhost:8080/api/lipputyypit/1"

Body:

    {
        "nimi": "normilippu",
        "hinta": 30
    }

### Poista lipputyyppi

* URL: "http://localhost:8080/api/lipputyypit/{id}"
* Metodi: DELETE
* Esimerkki: "http://localhost:8080/api/lipputyypit/2"

# Käyttäjä

### Hae käyttäjät

* URL: "http://localhost:8080/api/kayttajat"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/kayttajat"

### Lisää käyttäjä

* URL: "http://localhost:8080/api/kayttajat/ADMIN"
* Metodi: POST
* Esimerkki: http://localhost:8080/api/kayttajat/ADMIN"

Body:

    {
        "etunimi": "John",
        "sukunimi": "Doe",
        "katuosoite": "Street 1",
        "syntymaaika": "2000-01-01",
        "sahkoposti": "John@example.com",
        "puhelinnro": "0401234567",
        "lisatieto": "Testikäyttäjä",
        "postinumero": {
            "postinumero": "11111"
        },
        "rooli": {
            "rooli_id": 1
        }
    }

### Päivitä käyttäjä

* URL: "http://localhost:8080/api/kayttajat/ADMIN/{id}"
* Metodi: PUT
* Esimerkki: "http://localhost:8080/api/kayttajat/ADMIN/1"

  Body:

      {
          "etunimi": "John",
          "sukunimi": "DoeDoe",
          "katuosoite": "Street 2",
          "syntymaaika": "2000-01-01",
          "sahkoposti": "John.updated@example.com",
          "puhelinnro": "0409876543",
          "lisatieto": "Päivitetty käyttäjä",
          "postinumero": {
            "postinumero": "11111"
          },
          "rooli": {
            "rooli_id": 1
          }
      }

### Poista käyttäjä

* URL: "http://localhost:8080/api/kayttajat/ADMIN{id}"
* Metodi: DELETE
* Esimerkki: "http://localhost:8080/api/kayttajat/ADMIN/1"

# Myynti

### Hae kaikki myynnit

* URL: "http://localhost:8080/api/myynnit"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/myynnit"

### Hae myynti ID:llä

* URL: "http://localhost:8080/api/myynnit/{id}"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/myynnit/1"

### Lisää myynti

* URL: "http://localhost:8080/api/myynnit/ADMIN"
* Metodi: POST
* Esimerkki: "http://localhost:8080/api/myynnit/ADMIN"

Body:

      {
          "kayttaja": {
              "kayttajaId": 1
          },
          "paivamaara": "2025-09-30",
          "maksutapa": "Kortti",
          "tyyppi": "Verkkokauppa",
          "myyntirivit": []
      }

### Päivitä myynti

* URL: "http://localhost:8080/api/myynnit/ADMIN/{id}"
* Metodi: PUT
* Esimerkki: "http://localhost:8080/api/myynnit/ADMIN/1"

Body:
      
      {
          "kayttaja": {
              "kayttajaId": 1
          },
          "paivamaara": "2025-10-01",
          "maksutapa": "Käteinen",
          "tyyppi": "Kassa",
          "myyntirivit": []
      }

### Poista myynti

* URL: "http://localhost:8080/api/myynnit/ADMIN/{id}"
* Metodi: DELETE
* Esimerkki: "http://localhost:8080/api/myynnit/ADMIN/1"

# Postinumero

### Hae postinumerot/postitoimipaikat

* URL: "http://localhost:8080/api/postinumerot"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/postinumerot"

### Lisää postinumero/postitoimipaikka

* URL: "http://localhost:8080/api/postinumerot/ADMIN"
* Metodi: POST
* Esimerkki: "http://localhost:8080/api/postinumerot/ADMIN"

Body:

    {
        "postinumero": "33100",
        "postitoimipaikka": "Tampere"
    }
 

### Päivitä postinumero/postitoimipaikka

* URL: "http://localhost:8080/api/postinumerot/ADMIN/{postinumero}"
* Metodi: PUT
* Esimerkki: "http://localhost:8080/api/postinumerot/ADMIN/00980"

Body:

    {
        "postinumero": "33200",
        "postitoimipaikka": "Tampere"
    }

### Poista postinumero/postitoimipaikka

* URL: "http://localhost:8080/api/postinumerot/ADMIN/{postinumero}"
* Metodi: DELETE
* Esimerkki: "http://localhost:8080/api/postinumerot/ADMIN/00980"

 
 # Järjestäjä

### Hae kaikki järjestäjät
* URL: "http://localhost:8080/api/jarjestajat"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/jarjestajat"

### Hae järjestäjä ID:llä
* URL: "http://localhost:8080/api/jarjestajat/{id}"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/jarjestajat/1"

### Lisää järjestäjä
* URL: "http://localhost:8080/api/jarjestajat/ADMIN"
* Metodi: POST
* Esimerkki: "http://localhost:8080/api/jarjestajat/ADMIN"

Body:
{
    "nimi": "Konserttiyhtiö Oy",
    "yhteyshenkilo": "Matti Meikäläinen",
    "sahkoposti": "matti@konserttiyhtio.fi",
    "puhelin": "0401234567"
}

### Päivitä järjestäjä
* URL: "http://localhost:8080/api/jarjestajat/ADMIN/{id}"
* Metodi: PUT
* Esimerkki: "http://localhost:8080/api/jarjestajat/ADMIN/1"

Body:
{
    "nimi": "Konserttiyhtiö Oy",
    "yhteyshenkilo": "Matti Meikäläinen",
    "sahkoposti": "matti.uusiosoite@konserttiyhtio.fi",
    "puhelin": "0509876543"
}

### Poista järjestäjä
* URL: "http://localhost:8080/api/jarjestajat/ADMIN/{id}"
* Metodi: DELETE
* Esimerkki: "http://localhost:8080/api/jarjestajat/ADMIN/1"

# Rooli

### Hae kaikki roolit

* URL: "http://localhost:8080/api/roolit"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/roolit"

### Hae rooli ID:llä

* URL: "http://localhost:8080/api/roolit/{id}"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/roolit/1"

### Lisää rooli

* URL: "http://localhost:8080/api/roolit"
* Metodi: POST
* Esimerkki: "http://localhost:8080/api/roolit"

Body:

    {
        "name": "Admin"
    }

### Päivitä rooli

* URL: "http://localhost:8080/api/roolit/{id}"
* Metodi: PUT
* Esimerkki: "http://localhost:8080/api/roolit/1"

Body:

    {
        "name": "Käyttäjä"
    }

### Poista rooli

* URL: "http://localhost:8080/api/roolit/{id}"
* Metodi: DELETE
* Esimerkki: "http://localhost:8080/api/roolit/1"

# Myyntirivi

### Hae kaikki myyntirivit
* URL: "http://localhost:8080/api/myyntirivit"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/myyntirivit"

### Hae myyntirivi ID:llä
* URL: "http://localhost:8080/api/myyntirivit/{id}"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/myyntirivit/1"

### Lisää uusi myyntirivi
* URL: "http://localhost:8080/api/myyntirivit/ADMIN"
* Metodi: POST
* Esimerkki:

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
* URL: "http://localhost:8080/api/myyntirivit/ADMIN/{id}"
* Metodi: PUT
* Esimerkki: "http://localhost:8080/api/myyntirivit/ADMIN/1"

Body:

    {
        "myynti": {
            "myyntiId": 1
        },
        "lippu": {
            "lippu_id": 1
        },
        "paivamaara": "2025-01-07",
        "summa": 59.90
    }

### Poista myyntirivi
* URL: "http://localhost:8080/api/myyntirivit/ADMIN/{id}"
* Metodi: DELETE
* Esimerkki: "http://localhost:8080/api/myyntirivit/ADMIN/1"
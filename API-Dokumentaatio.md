# Tapahtuma

### Hae tapahtumat

* URL: "http://localhost:8080/api/tapahtumat"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/tapahtumat"

### Lisää tapahtuma

* URL: "http://localhost:8080/api/tapahtumat"
* Metodi: POST
* Esimerkki: http://localhost:8080/api/tapahtumat
  
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

* URL: "http://localhost:8080/api/tapahtumat/{id}"
* Metodi: PULL
* Esimerkki: "http://localhost:8080/api/tapahtumat/1"
  
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

* URL: "http://localhost:8080/api/tapahtumat/{id}"
* Metodi: DELETE
* Esimerkki: "http://localhost:8080/api/tapahtumat/1"

# Lippu

### Hae liput

* URL: "http://localhost:8080/api/liput"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/liput"

### Lisää lippu

* URL: "http://localhost:8080/api/liput"
* Metodi: POST
* Esimerkki: http://localhost:8080/api/liput

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

URL: "http://localhost:8080/api/liput/{id}"

Metodi: PUT

Esimerkki: "http://localhost:8080/api/liput/1"

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

# Käyttäjä
### Hae käyttäjät

* URL: "http://localhost:8080/api/kayttajat"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/kayttajat"

### Lisää käyttäjä

* URL: "http://localhost:8080/api/kayttajat"
* Metodi: POST
* Esimerkki: http://localhost:8080/api/kayttajat

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

* URL: "http://localhost:8080/api/kayttajat/{id}"
* Metodi: PUT
* Esimerkki: "http://localhost:8080/api/kayttajat/1"

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

* URL: "http://localhost:8080/api/kayttajat/{id}"
* Metodi: DELETE
* Esimerkki: "http://localhost:8080/api/kayttajat/1"

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

* URL: "http://localhost:8080/api/myynnit"
* Metodi: POST
* Esimerkki: "http://localhost:8080/api/myynnit"

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

* URL: "http://localhost:8080/api/myynnit/{id}"
* Metodi: PUT
* Esimerkki: "http://localhost:8080/api/myynnit/1"

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

* URL: "http://localhost:8080/api/myynnit/{id}"
* Metodi: DELETE
* Esimerkki: "http://localhost:8080/api/myynnit/1"
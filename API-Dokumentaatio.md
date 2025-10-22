# Tapahtuma

### Hae tapahtumat

* Vaatimus: autentikoitu käyttäjä
* URL: "http://localhost:8080/api/tapahtumat"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/tapahtumat"

### Lisää tapahtuma

* Vaatimus: ADMIN, oikeudet
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

* Vaatimus: ADMIN, oikeudet
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

* Vaatimus: ADMIN, oikeudet
* URL: "http://localhost:8080/api/tapahtumat/ADMIN/{id}"
* Metodi: DELETE
* Esimerkki: "http://localhost:8080/api/tapahtumat/ADMIN/1"

# Lippu

### Hae liput

* Vaatimus: autentikoitu käyttäjä
* URL: "http://localhost:8080/api/liput"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/liput"

### Lisää lippu

* Vaatimus: ADMIN, oikeudet
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

* Vaatimus: ADMIN, oikeudet
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

* Vaatimus: ADMIN, oikeudet
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

* Vaatimus: autentikoitu käyttäjä
* URL: "http://localhost:8080/api/lipputyypit"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/lipputyypit"

### Lisää lipputyyppi

* Vaatimus: ADMIN, oikeudet
* URL: "http://localhost:8080/api/lipputyypit"
* Metodi: POST
* Esimerkki: "http://localhost:8080/api/lipputyypit"

Body:

    {
        "nimi": "1 päivän lippu",
        "hinta": 90
    }
 

### Päivitä lippytyyppi

* Vaatimus: ADMIN, oikeudet
* URL: "http://localhost:8080/api/lipputyypit/{id}"
* Metodi: PUT
* Esimerkki: "http://localhost:8080/api/lipputyypit/1"

Body:

    {
        "nimi": "normilippu",
        "hinta": 30
    }

### Poista lipputyyppi

* Vaatimus: ADMIN, oikeudet
* URL: "http://localhost:8080/api/lipputyypit/{id}"
* Metodi: DELETE
* Esimerkki: "http://localhost:8080/api/lipputyypit/2"

# Käyttäjä

### Hae käyttäjät

* Vaatimus: autentikoitu käyttäjä
* URL: "http://localhost:8080/api/kayttajat"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/kayttajat"

### Lisää käyttäjä

* Vaatimus: ADMIN, oikeudet
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

* Vaatimus: ADMIN, oikeudet
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

* Vaatimus: ADMIN, oikeudet
* URL: "http://localhost:8080/api/kayttajat/{id}"
* Metodi: DELETE
* Esimerkki: "http://localhost:8080/api/kayttajat/1"

# Myynti

### Hae kaikki myynnit

* Vaatimus: autentikoitu käyttäjä
* URL: "http://localhost:8080/api/myynnit"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/myynnit"

### Hae myynti ID:llä

* Vaatimus: autentikoitu käyttäjä
* URL: "http://localhost:8080/api/myynnit/{id}"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/myynnit/1"

### Lisää myynti

* Vaatimus: ADMIN, oikeudet
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

* Vaatimus: ADMIN, oikeudet
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

* Vaatimus: ADMIN, oikeudet
* URL: "http://localhost:8080/api/myynnit/ADMIN/{id}"
* Metodi: DELETE
* Esimerkki: "http://localhost:8080/api/myynnit/ADMIN/1"

# Postinumero

### Hae postinumerot/postitoimipaikat

* Vaatimus: autentikoitu käyttäjä
* URL: "http://localhost:8080/api/postinumerot"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/postinumerot"

### Lisää postinumero/postitoimipaikka

* Vaatimus: ADMIN, oikeudet
* URL: "http://localhost:8080/api/postinumerot"
* Metodi: POST
* Esimerkki: "http://localhost:8080/api/postinumerot"

Body:

    {
        "postinumero": "33100",
        "postitoimipaikka": "Tampere"
    }
 

### Päivitä postinumero/postitoimipaikka

* Vaatimus: ADMIN, oikeudet
* URL: "http://localhost:8080/api/postinumerot/{postinumero}"
* Metodi: PUT
* Esimerkki: "http://localhost:8080/api/postinumerot/00980"

Body:

    {
        "postinumero": "33200",
        "postitoimipaikka": "Tampere"
    }

### Poista postinumero/postitoimipaikka

* Vaatimus: ADMIN, oikeudet
* URL: "http://localhost:8080/api/postinumerot/{postinumero}"
* Metodi: DELETE
* Esimerkki: "http://localhost:8080/api/postinumerot/00980"

 
 # Järjestäjä

### Hae kaikki järjestäjät

* Vaatimus: autentikoitu käyttäjä
* URL: "http://localhost:8080/api/jarjestajat"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/jarjestajat"

### Hae järjestäjä ID:llä

* Vaatimus: autentikoitu käyttäjä
* URL: "http://localhost:8080/api/jarjestajat/{id}"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/jarjestajat/1"

### Lisää järjestäjä

* Vaatimus: ADMIN, oikeudet
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

* Vaatimus: ADMIN, oikeudet
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

* Vaatimus: ADMIN, oikeudet
* URL: "http://localhost:8080/api/jarjestajat/ADMIN/{id}"
* Metodi: DELETE
* Esimerkki: "http://localhost:8080/api/jarjestajat/ADMIN/1"

# Myyntirivi

### Hae kaikki myyntirivit

* Vaatimus: autentikoitu käyttäjä
* URL: "http://localhost:8080/api/myyntirivit"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/myyntirivit"

### Hae myyntirivi ID:llä

* Vaatimus: ADMIN, oikeudet
* URL: "http://localhost:8080/api/myyntirivit/{id}"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/myyntirivit/1"

### Lisää uusi myyntirivi

* Vaatimus: ADMIN, oikeudet
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

* Vaatimus: ADMIN, oikeudet
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

* Vaatimus: ADMIN, oikeudet
* URL: "http://localhost:8080/api/myyntirivit/ADMIN/{id}"
* Metodi: DELETE
* Esimerkki: "http://localhost:8080/api/myyntirivit/ADMIN/1"
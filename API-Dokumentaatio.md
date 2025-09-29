# Tapahtuma

### Hae tapahtumat

* URL: "http://localhost:8080/api/tapahtumat"
* Metodi: GET
* Esimerkki: "http://localhost:8080/api/tapahtumat"

### Lisää tapahtuma

* URL: "http://localhost:8080/api/tapahtumat"
* Metodi: POST
* Esimerkki: http://localhost:8080/api/tapahtumat
    Body:     {
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
    Body:    {
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


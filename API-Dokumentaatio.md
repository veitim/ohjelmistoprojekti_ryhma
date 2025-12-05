# API-Dokumentaatio

## Perustiedot

## Kaikki endpointit
Endpoint	Metodit
/jarjestajat	GET, POST, PUT, DELETE

/kayttajat	GET, POST, PUT, DELETE

/liput	GET, POST, PUT, PATCH, DELETE

/lipputyypit	GET, POST, PUT, DELETE

/myynnit	GET, POST, PUT, DELETE

/myyntirivit	GET, POST, PUT, DELETE

/postinumerot	GET, POST, PUT, DELETE

/tapahtumat	GET, POST, PUT, DELETE


## Käyttöoikeudet

Kaikki endpointit edellyttävät autentikointia.

USER: Saa tehdä GET-pyynnöt ja luoda myynnin (POST).

ADMIN: Täydet oikeudet (GET, POST, PUT, DELETE, PATCH).

Yleinen käyttöesimerkki

Base URL:
https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/{endpoint}

Esimerkki (GET /jarjestajat):
https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/jarjestajat

Esimerkkivastaus:

{
    "jarjestaja_id": 1,
    "nimi": "uber",
    "yhteyshenkilo": "pekka",
    "sahkoposti": "uber@uber",
    "puhelin": "+3580000",
    "tapahtumat": {
        "tapahtuma_id": 1,
        "nimi": "hälläväliä",
        "katuosoite": "koti",
        "alkamisPvm": "2025-01-07",
        "paattymisPvm": "2025-01-08",
        "lisatiedot": "ikärajaa ei ole",
        "paikkamaara": 200
    }
}


PUT/DELETE tehdään id:n perusteella:
https://ticketguru-git-ticketguru.2.rahtiapp.fi/api/{endpoint}/{id}


# Endpoint-kohtainen dokumentaatio


## api/jarjestajat
Käyttöoikeus

GET: USER/ADMIN

POST/PUT/DELETE: ADMIN

POST /jarjestajat

Body

{
    "nimi": "Konserttiyhtiö Oy",
    "yhteyshenkilo": "Matti Meikäläinen",
    "sahkoposti": "matti@konserttiyhtio.fi",
    "puhelin": "0401234567"
}


Pakollinen kenttä: nimi
Vastaus 201 Created

{
    "jarjestaja_id": {id},
    "nimi": "...",
    "yhteyshenkilo": "...",
    "sahkoposti": "...",
    "puhelin": "...",
    "tapahtumat": null
}

PUT /jarjestajat/{id}

Body

{
    "nimi": "MUUTOS",
    "yhteyshenkilo": "MUUTOS",
    "sahkoposti": "matti@konserttiyhtio.fi",
    "puhelin": "0401234567"
}


Vastaus: 200 OK

DELETE /jarjestajat/{id}

Mahdolliset vastaukset:
201 Created — Poistettu
400 Bad Request — Virheellinen pyyntö
403 Forbidden — Ei oikeuksia
404 Not Found — ID ei löydy


## api/kayttajat
Käyttöoikeus

GET: USER/ADMIN

POST/PUT/DELETE: ADMIN

POST /kayttajat

Pakolliset:
etunimi, sukunimi, sahkoposti, username, password, rooli

Body

{
    "etunimi": "Matti",
    "sukunimi": "Meikäläinen",
    "katuosoite": "Esimerkkikatu 1",
    "syntymaaika": "1995-05-15",
    "sahkoposti": "matti@example.com",
    "puhelinnro": "0401234567",
    "lisatieto": "Ei lisätietoja",
    "postinumero": { "postinumero": "00100" },
    "username": "matti123",
    "password": "salasana123",
    "rooli": "USER"
}


Vastaus: 201 Created

{
    "kayttaja_id": {id},
    "etunimi": "...",
    "sukunimi": "...",
    ...
    "postinumero": {
        "postinumero": "00100",
        "postitoimipaikka": "Helsinki"
    },
    "username": "matti123",
    "passwordHash": "$2a$10$...",
    "rooli": "USER",
    "myynnit": []
}

PUT /kayttajat/{id}

Body (esimerkki)

{
    "etunimi": "MUUTOS",
    "sukunimi": "MUUTOS",
    ...
    "rooli": "USER"
}


Vastaus: 200 OK

DELETE /kayttajat/{id}

Mahdolliset vastaukset:
201 Created — Poistettu
400 Bad Request — Virheellinen pyyntö
403 Forbidden — Ei oikeuksia
404 Not Found — ID ei löydy


## api/liput
Käyttöoikeus

GET: USER/ADMIN

POST/PUT/PATCH/DELETE: ADMIN

GET /liput
Haku koodilla

/liput?koodi={koodi}

Vastaus: 200 OK
tai
404 Not Found

Haku ID:llä

/liput/{id}

POST /liput

Pakolliset: tapahtuma, lipputyyppi

Body

{
    "paikka": "Helsinki",
    "tila": true,
    "tapahtuma": { "tapahtuma_id": 1 },
    "lipputyyppi": { "tyyppi_id": 1 }
}


Vastaus: 201 Created

{
    "lippu_id": {id},
    "paikka": "...",
    "tila": true,
    "kaytetty": false,
    ...
}

PUT /liput/{id}

Body

{
    "paikka": "MUUTOS",
    "tila": false,
    "kaytetty": true,
    "tapahtuma": { "tapahtuma_id": 1 },
    "lipputyyppi": { "tyyppi_id": 2 }
}

PATCH /liput/{id}

Body

{
    "kaytetty": true
}


Vastaus: 200 OK
(Päivittää käytetty-tilan)

DELETE /liput/{id}

Samat vastauskoodit kuin muissa DELETE-pyynnöissä.


## api/tapahtumat
Käyttöoikeus

GET: USER/ADMIN

POST/PUT/DELETE: ADMIN

GET /tapahtumat

Mahdollinen virhekoodi: 404 Not Found

POST /tapahtumat

Body

{
    "nimi": "TESTAILEE2",
    "katuosoite": "koti",
    "alkamisPvm": "2025-01-07",
    "paattymisPvm": "2025-01-08",
    "lisatiedot": "ikärajaa ei ole",
    "liput": [],
    "jarjestaja": { "jarjestaja_id": 1 }
}

Vastaus: 200 OK

PUT /tapahtumat/{id}
Sama rakenne kuin POSTissa.

DELETE /tapahtumat/{id}
Samat vastauskoodit kuin muissa DELETE-pyynnöissä.


## api/lipputyypit
Käyttöoikeus

GET: USER/ADMIN

POST/PUT/DELETE: ADMIN

POST /lipputyypit:

Body

{
    "nimi": "1 päivän lippu",
    "hinta": 90
}

PUT /lipputyypit/{id}:

Body

{
    "nimi": "normilippu",
    "hinta": 30
}


## api/myynnit
Käyttöoikeus

GET: USER/ADMIN

POST: USER/ADMIN

PUT/DELETE: ADMIN

POST /myynnit:

Body

{
    "kayttaja": { "kayttajaId": 1 },
    "paivamaara": "2025-09-30",
    "maksutapa": "Kortti",
    "tyyppi": "Verkkokauppa",
    "myyntirivit": []
}


## api/postinumerot
Käyttöoikeus

GET: USER/ADMIN

POST/PUT/DELETE: ADMIN

POST /postinumerot:

Body

{
    "postinumero": "33100",
    "postitoimipaikka": "Tampere"
}

PUT /postinumerot/{postinumero}

Body

{
    "postinumero": "33200",
    "postitoimipaikka": "Tampere"
}


## api/myyntirivit
Käyttöoikeus

GET: USER/ADMIN

POST/PUT/DELETE: ADMIN

POST /myyntirivit:

Body

{
    "myynti": { "myynti_id": 1 },
    "lippu": { "lippu_id": 5 },
    "paivamaara": "2025-01-07",
    "summa": 59.90
}
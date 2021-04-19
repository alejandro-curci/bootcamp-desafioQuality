"DESAFÍO QUALITY" / Spring Boot and JUnit 5

This API intends to solve hotel and flight reservations. Both parts are independent. It includes exceptions handlers and unit tests (coverage over 90%).

HOTEL RESERVATION

1- You can ask for every available hotel by making a request to (GET) api/v1/hotels

2- The previous endpoint accepts the following params: "dateFrom", "dateTo" and "destination". They filter the search according to the user needs

3- You can perform a hotel reservation by making a request to (POST) api/v1/booking. A body object is required, same format as the following example:

{
    "userName" : "seba_gonzalez@unmail.com",
    "booking" : {
        "dateFrom" : "10/02/2021",
        "dateTo" : "20/02/2021",
        "destination" : "Buenos Aires",
        "hotelCode" : "HB-0001",
        "peopleAmount" : 1,
        "roomType" : "SINGLE",
        "people" : [
            {
                "dni" : "12345678",
                "name" : "Pepito",
                "lastName" : "Gomez",
                "birthDate" : "10/11/1982",
                "mail" : "pepitogomez@gmail.com"
            },
             {
                "dni" : "13345678",
                "name" : "Fulanito",
                "lastName" : "Gomez",
                "birthDate" : "10/11/1983",
                "mail" : "fulanitogomez@gmail.com"
            }
        ],
        "paymentMethod" : {
            "type" : "CREDIT",
            "number" : "1234-1234-1234-1234",
            "dues" : 6
        }
    }
}

FLIGHT RESERVATION

1- You can ask for every available flight by making a request to (GET) api/v1/flights

2- The previous endpoint accepts the following params: "dateFrom", "dateTo", "origin" and "destination". They filter the search according to the user needs

3- You can perform a flight reservation by making a request to (POST) api/v1/flight-reservation. A body object is required, same format as the following example:

{
    "userName": "arjonamiguel@gmail.com",
    "flightReservation": {
        "dateFrom": "10/02/2021",
        "dateTo": "15/02/2021",
        "origin": "Buenos Aires",
        "destination": "Puerto Iguazú",
        "flightNumber": "BAPI-1235",
        "seats": 2,
        "seatType": "ECONOMY",
        "people": [
            {
                "dni": "12345678",
                "name": "Pepito",
                "lastName": "Gomez",
                "birthDate": "10/11/1982",
                "mail": "pepitogomez@gmail.com"
            },
            {
                "dni": "13345678",
                "name": "Fulanito",
                "lastName": "Gomez",
                "birthDate": "10/11/1983",
                "mail": "fulanitogomez@gmail.com"
            }
        ],
        "paymentMethod": {
            "type": "CREDIT",
            "number": "1234-1234-1234-1234",
            "dues": 6
        }
    }
}

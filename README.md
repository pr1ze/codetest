# codetest

Jeg har lavet 2 services
Jeg har valgt at basere min codetest på at designe en løsning som kan skalere horisontalt.

I et rigtigt scenarie ville jeg dog aldrig have skildt forretningslogikken fre datalaget, da det fjerner muligheden for at have rigtige automous microservices.

For testens skyld har jeg fulgt det der blev efterspurgt i opgaven.

## Countable-resource-service 
RESTful webservice som udstiller de 3 funktioner som efterspurgt. 
API er genereret med OpenAPI og kan ses her: `/apis/countable-resource-service-api.yaml` API'et kan ses på https://editor.swagger.io ved at kopiere indholdet ind.

Der er ikke noget fancy event sourcing el. CQRS. 
Tilgengæld et fint dokumenteret API via OpenAPI.

Det interessante i denne service er følgende:
- Servicen arbejder med ACID transaktioner
- For at opdatere bruges der ETag headeren If-Match (Påkrævet header) - for at kunne have forretnings logikken med at incrementere og dekrementere i den anden service
- Hexagonal architecture
- Der bruges SELECT * FOR UPDATE så resourcen låses inden den opdateres - for at kunne skalere horizontalt
-- I stedet for SELECT * FOR UPDATE ville en løsning med actors have været langt mere performant end database lås

Interessant klasser/directories at kigge i:
`/src/main/groovy/dk/lunar/codetest/application.web/*`
`/src/main/groovy/dk/lunar/codetest/domain/*`
`/src/test/groovy/dk/lunar/codetest/application.web/CountableResourcesControllerSpec.groovy`

## Business-logic-service
En spring boot application som har en actor, implementeret med Akka.
Actoren sikrer synkronisering, og Akka sikre at service ville kunne skalere horizontalt. 
Actoren kan modtage kommandore om inkrementere og dekrementere.
Actoren kan holde state om hvad sidste "count" & ETag var, og behøver derfor ikke lave et GET før den opdatere.

Interessant klasser/directories at kigge i:
`/src/main/groovy/dk/lunar/businesslogic/domain/CountableResourceActor.groovy`
`/src/test/groovy/dk/lunar/businesslogic/domain/CountableResourceServiceSpec.groovy`

## Test og bevis af samtidighedsproblemer at løst
Se `business-logic-service/test/groovy/dk.lunar.businesslogic/domain/CountableResourceServiceSpec.groovy`

## Clients
Der ligger udover det et clients lib, som er genereret ud fra swagger filen for at integrere

## Kørsel
`./gradlew clean build`
Og så sprint boot run
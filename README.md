Start project: mvn spring-boot:run

Here is the Integration Testing:

1. Get total number of events:
curl --location --request GET 'http://localhost:8080/dataset/stats/total'

2. Get number of events grouped by offense(KY_CD)
curl --location --request GET 'http://localhost:8080/dataset/stats/offenses'

3. Create record
curl --location --request POST 'http://localhost:8080/dataset' \
--header 'Content-Type: application/json' \
--data-raw '{
    "complaintID": 100000001,
    "offenseCode": 231
}'

4. Create record with invalid input
4.1
curl --location --request POST 'http://localhost:8080/dataset' \
--header 'Content-Type: application/json' \
--data-raw '{
    "complaintID": 100,
    "offenseCode": 231
}'
4.2
curl --location --request POST 'http://localhost:8080/dataset' \
--header 'Content-Type: application/json' \
--data-raw '{
    "complaintID": 200090901,
    "offenseCode": 23
}'
4.3
curl --location --request POST 'http://localhost:8080/dataset' \
--header 'Content-Type: application/json' \
--data-raw '{
    "complaintID": 2002,
    "offenseCode": 23
}'

5. Delete record
curl --location --request DELETE 'http://localhost:8080/dataset/100000001'

6. Delete record with invalid input
curl --location --request DELETE 'http://localhost:8080/dataset/1000'

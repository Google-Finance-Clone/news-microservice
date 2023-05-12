# News Microservice

This is a simple news microservice that provides the latest news for a specific stock ticker and the latest news in general. The service is built using Spring Boot and Spring Data MongoDB.

## Endpoints

### Get Latest News for a Specific Stock Ticker

Returns the latest 6 news items for the specific stock ticker.

**Request**:

`GET http://localhost:8080/news/{ticker}`

**Path Parameters**:

- `ticker`: Stock ticker symbol (e.g., "AAPL" for Apple Inc.)

**Example**:

```
GET http://localhost:8080/news/AAPL
```

### Get Latest News in General

Returns the latest 10 news items in general, ordered by date.

**Request**:

`GET http://localhost:8080/news`

**Example**:

```
GET http://localhost:8080/news
```

## Setup

### Prerequisites

- Java 8 or higher
- Maven
- MongoDB

### Steps

1. Clone the repository:

```
git clone https://github.com/your-username/news-microservice.git
```

2. Navigate to the project directory:

```
cd news-microservice
```

3. Build the project:

```
mvn clean install
```

4. Run the application:

```
mvn spring-boot:run
```

The application will start on port 8080, and you can access the endpoints described above.

## License

This project is licensed under the [MIT License](LICENSE).

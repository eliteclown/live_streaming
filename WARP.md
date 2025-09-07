# WARP.md

This file provides guidance to WARP (warp.dev) when working with code in this repository.

## Project Overview

This is a Spring Boot application for live streaming platform called VisionLabs. The application manages live streaming sessions, integrates with ZenStream API for streaming functionality, and provides features like transcription, Q&A, summaries, and bookmarks.

## Development Commands

### Build and Run
```bash
# Navigate to the Spring Boot module
cd visionlabs

# Build the project
./mvnw clean compile

# Run tests
./mvnw test

# Run the application
./mvnw spring-boot:run

# Build JAR package
./mvnw clean package
```

### Development Workflow
```bash
# Run specific test class
./mvnw test -Dtest=LivedemoApplicationTests

# Run with specific profile (if profiles are configured)
./mvnw spring-boot:run -Dspring.profiles.active=dev

# Skip tests during build
./mvnw clean package -DskipTests

# Generate dependency tree
./mvnw dependency:tree
```

## Architecture Overview

### Core Domain Model
The application follows a domain-driven design with these key entities:

- **LiveSession**: Central entity representing streaming sessions with ZenStream integration
- **User**: Supports STUDENT and INSTRUCTOR roles  
- **Transcript**: AI-generated transcriptions of live sessions
- **Questions**: Time-stamped Q&A during sessions
- **Summary**: AI or manual summaries of sessions
- **Bookmarks**: User bookmarks within sessions

### Service Layer Architecture
- **StreamService**: Core business logic for stream lifecycle (create, start, stop, schedule)
- **CallService**: HTTP client wrapper for ZenStream API integration
- **External API Integration**: ZenStream API for actual streaming infrastructure

### Key Design Patterns
- **Repository Pattern**: JPA repositories for data access
- **DTO Pattern**: Separate request/response DTOs for API boundaries  
- **Configuration Pattern**: Centralized RestClient configuration for ZenStream API
- **Enum-driven State Management**: Status enum for session lifecycle

### Database Design
- MySQL database with JPA/Hibernate ORM
- Auto-generated primary keys using GenerationType.AUTO
- Foreign key relationships between sessions, users, and related entities
- Timestamp tracking with @CreationTimestamp annotations

## Configuration Requirements

### Environment Variables
- `ZENSTREAM_API_KEY`: Required for ZenStream API authentication

### Database Configuration
The application expects a MySQL database:
- Database name: `live`
- Default connection: `localhost:3306`
- Auto-DDL enabled for schema updates

### Application Properties
Key configuration in `src/main/resources/application.properties`:
- ZenStream API base URL: `https://api.zenstream.live/v1`
- Database connection settings
- JPA/Hibernate configuration with SQL logging enabled

## Key Integration Points

### ZenStream API Integration
- **Authentication**: Bearer token via API key
- **Stream Management**: Create, start, stop operations via REST API
- **URL Structure**: `/v1/streams` endpoint for stream operations
- **Response Mapping**: Maps ZenStream responses to local LiveSession entities

### Service Dependencies
- StreamService depends on CallService for external API calls
- CallService uses configured RestClient with authentication headers
- Repository layer provides data persistence abstraction

## Development Notes

### Known Issues
- CallService has incomplete RestClient implementation (anonymous class with null returns)
- Missing REST controllers for HTTP endpoints
- LiveSessionRepository references non-existent courseId and instructor fields

### Testing Strategy
- Basic Spring Boot test context loading
- Minimal test coverage currently implemented
- Integration tests should focus on ZenStream API mocking

### Technology Stack
- Spring Boot 3.5.4
- Java 17
- MySQL with JPA/Hibernate
- Lombok for boilerplate reduction
- ModelMapper for entity-DTO mapping
- Maven for build management

## Recommended Development Approach

When working with this codebase:

1. **API Development**: Add REST controllers for HTTP endpoints
2. **Service Layer**: Fix CallService RestClient implementation  
3. **Entity Relationships**: Add missing fields referenced by repositories
4. **Error Handling**: Implement proper exception handling beyond RuntimeException
5. **Integration Testing**: Mock ZenStream API responses for reliable testing
6. **Security**: Add authentication/authorization layers
7. **Validation**: Implement proper DTO validation using Spring Validation

The application structure supports scalable development with clear separation of concerns between web, service, and data access layers.

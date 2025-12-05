# Real Estate Platform (Buy/Sell/Rent)

This project is a real-time real-estate platform where users can:
- Post apartments for sale or rent
- Chat with owners in real-time
- View property details
- Manage user accounts
- Use secure authentication

## Tech Stack
- Java 21 / Spring Boot
- Spring Security & JWT
- WebSocket (Real-time chat)
- MySQL / PostgreSQL
- Redis (Performance)
- Docker (Optional)
- Lombok

## Project Structure
- `/domain`
- `/service`
- `/controller`
- `/repository`
- `/config`

## Branch Strategy
- `main` – production-ready code
- `develop` – development branch
- `feature/*` – feature branches

## Day 1 Progress
- Created project structure
- Configured dependencies
- Initialized Git repo
- Set up base branches

## Day 2 Progress
- Project structure
- Technologies (Spring Boot, Spring Security, OAuth2, WebSockets)
- Branching strategy (main + develop + feature/*)

## Day 3 — Registration, Login, JWT
- User register API
- User login API
- JWT generation & validation
- Custom Authentication Provider

## Day 4 — Google OAuth2 Login Integration
- Implemented Google OAuth2 login
- Added CustomOAuth2UserService to fetch user info from Google
- Created OAuth2LoginSuccessHandler for automatic user creation
- Updated User entity (added roles, avatarUrl, authProvider)
- Migrated from enum Role to RoleEntity (ManyToMany relationship)
- Updated SecurityConfig to support OAuth2 login flow
- Added default role assignment (USER) on first login
- Updated UserMapper (mapping RoleEntity set instead of enum)

## Day 5 — Property Listing Module
- Created Property entity
- Added enums (PropertyType, HouseCategory)
- Implemented User → Property relationship
- Added PropertyDto + PropertyMapper
- Implemented PropertyService (CRUD)
- Implemented PropertyController
- Added Controller-level logging
- Added validation and structured domain layer


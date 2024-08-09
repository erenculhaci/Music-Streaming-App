# Tunebox

## Introduction

Welcome to the Tunebox! This project is a comprehensive music streaming service built using Java, Spring, and PostgreSQL. It’s designed to manage and stream music efficiently while adhering to SOLID principles and object-oriented design.

## Features

- **User Management**: Allows creating users, and updating or deleting their profiles.
- **Music Library**: Manage and browse through a rich catalog with support for albums, artists, and genres.
- **Playlists**: Users can create, update, and manage playlists.
- **Subscriptions**: Supports different subscription plans.
- **Search Functionality**: Efficient search for songs, albums, and artists.
- **CRUD Operations**: Full CRUD operations for all entities in the system.

## Technologies

- **Java**: The primary programming language used for the backend logic.
- **Spring Framework**: Used for building the RESTful APIs and managing application contexts.
- **PostgreSQL**: The database system used to store and manage application data.
- **JPA/Hibernate**: ORM framework for data persistence and manipulation.

## Project Structure

The project is organized into several modules, each serving a specific purpose:

- **`erenculhaci.tunebox.entity`**: Contains entity classes such as `Album`, `Artist`, `Genre`, `Playlist`, `Song`, `Subscription`, and `User`.
- **`erenculhaci.tunebox.dto`**: Contains DTO classes such as `AlbumDTO`, `ArtistDTO`, `GenreDTO`, `PlaylistDTO`, `SongDTO`, `SubscriptionDTO`, and `UserDTO` for transferring data between the application layers and the external interfaces.
- **`erenculhaci.tunebox.repository`**: Contains repository interfaces for database operations.
- **`erenculhaci.tunebox.service`**: Contains service classes that implement business logic.
- **`erenculhaci.tunebox.controller`**: Contains REST controllers that handle HTTP requests and responses.
- **`erenculhaci.tunebox.config`**: Contains configuration classes for setting up the Spring application.

## Entities

- **Album**: Represents a music album with fields:
  - `id`: Unique identifier
  - `title`: Title of the album
  - `artist`: The artist who created the album (many-to-one relationship with `Artist`)
  - `songs`: List of songs in the album (one-to-many relationship with `Song`)

- **Artist**: Represents a music artist with fields:
  - `id`: Unique identifier
  - `name`: Name of the artist
  - `surname`: Surname of the artist
  - `albums`: List of albums created by the artist (one-to-many relationship with `Album`)
  - `songs`: List of songs created by the artist (one-to-many relationship with `Song`)

- **Genre**: Represents a genre of music with fields:
  - `id`: Unique identifier
  - `name`: Name of the genre
  - `songs`: List of songs in this genre (many-to-many relationship with `Song`)

- **Playlist**: Represents a user-created playlist with fields:
  - `id`: Unique identifier
  - `name`: Name of the playlist
  - `user`: The user who created the playlist (many-to-one relationship with `User`)
  - `songs`: List of songs in the playlist (many-to-many relationship with `Song`)

- **Song**: Represents a music track with fields:
  - `id`: Unique identifier
  - `title`: Title of the song
  - `duration`: Duration of the song in seconds
  - `album`: The album to which the song belongs (many-to-one relationship with `Album`)
  - `genres`: List of genres associated with the song (many-to-many relationship with `Genre`)
  - `artist`: The artist who performed the song (many-to-one relationship with `Artist`)
  - `playlists`: List of playlists that include the song (many-to-many relationship with `Playlist`)

- **Subscription**: Represents a user’s subscription plan with fields:
  - `id`: Unique identifier
  - `type`: Type of subscription (e.g., Student, Premium)
  - `startDate`: Start date of the subscription
  - `endDate`: End date of the subscription
  - `user`: The user associated with the subscription (one-to-one relationship with `User`)

- **User**: Represents a user of the service with fields:
  - `id`: Unique identifier
  - `username`: Username of the user
  - `password`: Password for user authentication
  - `email`: Email address of the user
  - `playlists`: List of playlists created by the user (one-to-many relationship with `Playlist`)
  - `subscription`: The user's subscription (one-to-one relationship with `Subscription`)

### UML Diagram of Entities
![UML Diagram](https://github.com/user-attachments/assets/49921e5d-e4a0-4aab-8a3e-233885e7e69d)

## Getting Started

### Prerequisites

- Java 11 or higher
- PostgreSQL 12 or higher
- Maven (for dependency management)

### Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/erenculhaci/Music-Streaming-App.git
   cd Music-Streaming-App

2. **Set Up the Database**

Create a PostgreSQL database and update the application.properties file with your database connection details.

3. **Build the Project**
   ```bash
   mvn clean install

4. **Run the Project**
   ```bash
   mvn spring-boot:run

## Usage

Once the application is running, you can access it via `http://localhost:8080`.

You can send requests to this address using corresponding mappings.

## Acknowledgments

- This project is made for techcareer.net Spring+Java Bootcamp. Thanks to the techcareer.net community. 


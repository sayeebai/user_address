# User Address
This repository contains a project for managing user and address information using DTOs, entities, controllers, mappers, and tests. Below are the details of the components and how to use them.

## Components

### DTOs
- `UserDto`: Data Transfer Object for user information with validation.
- `AddressDto`: Data Transfer Object for address information with validation.

### Entities
- `UserEntity`: Entity class representing a user with a one-to-many relationship with addresses.
    - Properties: id, name, email, etc.
- `AddressEntity`: Entity class representing an address with a many-to-one relationship with users.
    - Properties: id, street, city, state, zipCode, country.

### Mappers
- `AddressMapper`: Interface for mapping AddressDto to AddressEntity and vice versa.
- `UserMapper`: Interface for mapping UserDto to UserEntity and vice versa.

### Controllers
- `AddressController`: Handles HTTP requests related to addresses.
    - Methods:
        - `create`: Create a new address.
        - `getAddresssByBothIds`: Get an address by both user and address ids.
        - `addAddressInUserId`: Add an address to a user by user id.

- `UserController`: Handles HTTP requests related to users.
    - Methods:
        - `createUser`: Create a new user.
        - `findUserById`: Find a user by id.
        - `getUserByName`: Get users by name.
        - `deleteUserById`: Delete a user by id.
        - `updateUserById`: Update a user by id.

### Tests
#### Controller Tests
- `AddressControllerTest`:
    - `testCreateAddress`: Test creating a new address.
    - `testGetAddresssByBothIds`: Test getting an address by both user and address ids.
    - `testAddAddressInUserId`: Test adding an address to a user by user id.

- `UserControllerTest`:
    - `testCreateUser`: Test creating a new user.
    - `testFindUserById`: Test finding a user by id.
    - `testGetUserByName`: Test getting users by name.
    - `testDeleteUserById`: Test deleting a user by id.
    - `testUpdateUser`: Test updating a user.

## Usage

To use this project, follow these steps:

1. Clone the repository: 
    ```bash
    git clone https://github.com/your_username/user_address.git
    ```

2. Build and run the project using your preferred IDE or build tool.

3. Access the endpoints through the defined URL mappings in the controllers.

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests to improve the project.

## License

This project is licensed under the [MIT License](LICENSE).

# PAO Library

**Idea:** Both the library user and the librarian use this same app. The user orders the book it wants (so it's not
borrowed by others), then goes to the library to pick it up. There, the librarian marks the order as being satisfied. In
the end, the librarian marks the book as being returned when the user comes back with it.

**Credentials for the librarian (has authorized access)**:  
Username: `admin`  
Password: `admin`

**Example credentials for a normal user:**  
Username: `luca`  
Password: `luca`

**Run the app**:  
`./gradlew run`

## Database diagram

![](https://i.imgur.com/XS4bZXF.png)

## Possible actions

- A user can see details about all books in the library
- A user can borrow a book from the library
- A librarian can create / update / delete any library entity
- A librarian can give books to users
- A librarian can receive back books from users
- A librarian can see the history of all borrows
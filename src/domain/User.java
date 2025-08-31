package domain;

public class User {
    private Long userId;
    private String userName;
    private Book borrowedBook = null; // → String 타입의 bookName이 아닌, Book타입의 book으로 필드 set!! → 서비스의 borrowBook 메서드 수정!!
    private Boolean borrowAvailable = true;

    public User(
        Long userId,
        String userName
    ) {
        this.userId = userId;
        this.userName = userName;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getBorrowedBookName() {
        return this.borrowedBook.bookName;
    }
    public Book getBorrowedBook() {
        return this.borrowedBook;
    }

    public void setUserBorrowedBook(Book book) {
        this.borrowedBook = book;
    }

    public void setUserBorrowAvailable(Boolean borrowAvailable) {
        this.borrowAvailable = borrowAvailable;
    }

}

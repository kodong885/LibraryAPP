package domain;

public class Book {
    Long bookId;
    String bookName;
    Integer bookPrice;
    Boolean loanAvailable = true;

    public Book(
            Long bookId,
            String bookName,
            Integer bookPrice
    ) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
    }

    public String getBookName() {
        return this.bookName;
    }

    public Long getBookId() {
        return this.bookId;
    }

    public Boolean getLoanAvailable() {
        return  this.loanAvailable;
    }

    public void setBookLoanAvailable(Boolean bookLoanAvailable) {
        this.loanAvailable = bookLoanAvailable;
    }
}

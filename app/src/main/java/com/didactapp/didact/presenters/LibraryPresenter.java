package com.didactapp.didact.presenters;

import com.didactapp.didact.entities.Book;
import com.didactapp.didact.contracts.LibraryContract;

import java.util.List;

/**
 * class to handle books presentation logic
 */
public final class LibraryPresenter implements LibraryContract.Presenter{
    private LibraryContract.View view;

    public void loadBooks() {
        view.showSpinner();
        view.hideSpinner();
    }

    @Override
    public void openBookDetails(Book requestedBook) {
//        view.showBookDetailsUi();
    }

    @Override
    public void onBooksLoaded(List<Book> bookList) {
        view.showBooks(bookList);
    }

    @Override
    public void takeView(LibraryContract.View view) {
        this.view = view;
        loadBooks();
    }

    @Override
    public void dropView() {
        view = null;
    }
}

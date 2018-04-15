package com.didactapp.didact.contracts;

import com.didactapp.didact.entities.Book;

import java.util.List;


public interface LibraryContract {

    interface View extends BaseView<Presenter>, android.view.View.OnClickListener {

        void showBooks(List<Book> bookList);

        void hideBooks();

        void showBookDetailsUi(String bookId);



    }

    interface Presenter extends BasePresenter<View> {

        void openBookDetails(Book requestedBook);

    }

}

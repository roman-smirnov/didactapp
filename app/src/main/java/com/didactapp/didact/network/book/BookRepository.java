package com.didactapp.didact.network.book;// TODO: a repository is required for using a cache
//package com.didactapp.android.data;
//
//import android.support.annotation.NonNull;
//
//import com.didactapp.android.cloudlibrary.models.BookModel;
//
///**
// * Created by roman on 12/03/2018.
// */
//
//public class BookRepository implements BookDataSource {
//
//    private static BookRepository INSTANCE = null;
//
//    @NonNull
//    private final BookDataSource remoteDataSource;
//
//    @NonNull
//    private final BookDataSource localDataSource;
//
//    public BookRepository(@NonNull BookDataSource remoteDataSource, @NonNull BookDataSource localDataSource) {
//        this.remoteDataSource = remoteDataSource;
//        this.localDataSource = localDataSource;
//    }
//
//
//    @Override
//    public void getBookList(@NonNull ChapterRemoteGatewayCallback callback) {
//
//    }
//
//    public static BookRepository getInstance(BookDataSource remoteDataSource, BookDataSource localDataSource) {
//        if (INSTANCE == null) {
//            INSTANCE = new BookRepository(remoteDataSource, localDataSource);
//        }
//        return INSTANCE;
//    }
//
//
//
//
//}

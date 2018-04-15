package com.didactapp.didact.network;// TODO: a repository is required for using a cache
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
//public class BookRepository implements BookRemoteDataSource {
//
//    private static BookRepository INSTANCE = null;
//
//    @NonNull
//    private final BookRemoteDataSource remoteDataSource;
//
//    @NonNull
//    private final BookRemoteDataSource localDataSource;
//
//    public BookRepository(@NonNull BookRemoteDataSource remoteDataSource, @NonNull BookRemoteDataSource localDataSource) {
//        this.remoteDataSource = remoteDataSource;
//        this.localDataSource = localDataSource;
//    }
//
//
//    @Override
//    public void getBookList(@NonNull RemoteGatewayCallback callback) {
//
//    }
//
//    public static BookRepository getInstance(BookRemoteDataSource remoteDataSource, BookRemoteDataSource localDataSource) {
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

package com.didactapp.didact.persistence;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * an interface that should be implemented by clients that ask for Data from disk
 **/
public interface LocalGateway<T> {

    void getItemList(@NonNull LocalGatewayCallback<T> callback);

    void storeItemList(List<T> itemList);

}

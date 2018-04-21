package com.didactapp.didact.persistence;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by roman on 11/03/2018.
 */

public interface LocalGateway<T> {

    void getItemList(@NonNull LocalGatewayCallback<T> callback);

    void storeItemList(List<T> itemList);

}

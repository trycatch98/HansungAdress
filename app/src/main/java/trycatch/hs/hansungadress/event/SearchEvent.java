package trycatch.hs.hansungadress.event;

import java.util.ArrayList;

import trycatch.hs.hansungadress.model.AddressModel;

/**
 * Created by trycatch on 2017. 12. 2..
 */

public class SearchEvent {
    private boolean isSuccess;
    private ArrayList<AddressModel> items;

    public SearchEvent(boolean isSuccess, ArrayList<AddressModel> items) {
        this.isSuccess = isSuccess;
        this.items = items;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public ArrayList<AddressModel> getItems() {
        return items;
    }
}

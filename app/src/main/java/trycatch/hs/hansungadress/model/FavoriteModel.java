package trycatch.hs.hansungadress.model;

import java.util.ArrayList;

/**
 * Created by trycatch on 2018. 1. 5..
 */

public class FavoriteModel {
    private ArrayList<AddressModel> addressModels;

    public FavoriteModel() {
        addressModels = new ArrayList<>();
    }

    public ArrayList<AddressModel> getAddressModels() {
        return addressModels;
    }

    public void addAddress(AddressModel address) {
        this.addressModels.add(address);
    }
}

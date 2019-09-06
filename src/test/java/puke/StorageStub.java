package puke;

import puke.storage.Storage;

import java.util.ArrayList;

public class StorageStub extends Storage {
    StorageStub(String path) {
        super(path);
    }

    @Override
    public ArrayList<String> read() {
        return null;
    }

    @Override
    public void write(ArrayList<String> lines) {
    }
}

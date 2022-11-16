package pl.telusmikolaj.interfaces;

public interface Block {
    String getColor();

    String getMaterial();

    default int getCount() {
        return 1;
    }
}

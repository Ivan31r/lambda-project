package chapter4;

public interface Carriage {
    String rock();

    public default String rockAndRoll() {
        return "Carriage";
    }
}

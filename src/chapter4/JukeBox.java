package chapter4;

public interface JukeBox {
    String rock();

    public default String rockAndRoll() {
        return "JukeBox";
    }
}

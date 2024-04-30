package chapter4;

public class MC implements Carriage,JukeBox{
    @Override
    public String rock() {
        return null;
    }

    @Override
    public String rockAndRoll() {
//        return Carriage.super.rockAndRoll();
        return JukeBox.super.rockAndRoll();
    }
}

package eg.com.uofcanada.medical.common;

public class DefaultCombo implements ComboElement{
    @Override
    public String getShortString() {
        return "Please Select";
    }

    @Override
    public String toString() {
        return getShortString();
    }
}

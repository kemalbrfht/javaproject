
import java.util.ArrayList;
import java.util.List;

public class RestoranYonetimi {
    private List<Masa> masalar;

    public RestoranYonetimi() {
        masalar = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            masalar.add(new Masa(i));
        }
    }

    // Masaların listesini döndürür
    public List<Masa> getMasalar() {
        return masalar;
    }
}

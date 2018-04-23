import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Handing {
    private List<Integer> value;

    public List<Integer> getValue() {
        value = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        for (int i = 0; i < 4; i++) {
            value.add(numbers.get(i));
        }
        return value;
    }

    public int getBulls(List<Integer> enterValue) {
        int countBulls = 0;
        for (int i = 0; i < 4; i++) {
            if (enterValue.get(i) == value.get(i)) countBulls++;
        }
        return countBulls;
    }
    public int getCows(List<Integer> enterValue){
        int countCows = 0;
        int check = -1 ;
        for (int i = 0; i < 4; i++){
            check = value.indexOf(enterValue.get(i));
            if (check!=-1 && check!=i) countCows++;

        }
        return countCows;
    }

}

import java.util.List;

public class SequentialSearch implements SearchAlgorithm {
    public int search(List<Integer> data, int target) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) == target) return i;
        }
        return -1;
    }
}
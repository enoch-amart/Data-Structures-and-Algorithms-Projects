import java.util.Collections;
import java.util.List;

public class BinarySearch implements SearchAlgorithm {
    public int search(List<Integer> data, int target) {
        Collections.sort(data);
        int left = 0, right = data.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (data.get(mid) == target) return mid;
            else if (data.get(mid) < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}
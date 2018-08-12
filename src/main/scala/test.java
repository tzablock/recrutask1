import javafx.util.Pair;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class test {
    public static void main(String[] args) {
        new Solution().optimalUtilization(3, Stream.of(Stream.of(1,-3).collect(toList()),Stream.of(1,2).collect(toList()),Stream.of(3,4).collect(toList())).collect(toList()),1);
    }

}
 class Solution
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    List<List<Integer>> optimalUtilization(
            int maximumOperatingTravelDistance,
            List<List<Integer>> forwardShippingRouteList,
            List<List<Integer>> returnShippingRouteList)
    {
        Stream<Pair<Integer, List<Integer>>> pairStream = forwardShippingRouteList.stream()
                .flatMap(fs -> returnShippingRouteList.stream().map(rs -> new Pair<List<Integer>, List<Integer>>(fs, rs)))
                .map(p -> new Pair<Integer, List<Integer>>(p.getKey().get(1) + p.getValue().get(1), Stream.of(p.getKey().get(0), p.getKey().get(0)).collect(toList())))
                .filter(p -> p.getKey() <= maximumOperatingTravelDistance);
        Integer maxVal = pairStream.max(Comparator.comparing(Pair::getKey)).get().getKey();
        return pairStream.filter(p -> p.getKey() == maxVal).map(p -> p.getValue()).collect(toList());
    }
}
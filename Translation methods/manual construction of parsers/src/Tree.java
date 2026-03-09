import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class Tree {
    public final List<Tree> children;
    public final String info;

    public Tree(final String info, final Tree... children) {
        this.children = Arrays.asList(children);
        this.info = info;
    }

    public Tree(final String info) {
        children = Collections.emptyList();
        this.info = info;
    }

    @Override
    public String toString() {

        final String collect;
        if (children == null) {
            collect = "error";
        } else {
            collect = children.stream().map(Objects::toString).collect(Collectors.joining(", ", "[", "]"));
        }
        return info + ": " + collect;
    }
}

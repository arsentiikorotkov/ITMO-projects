package parser.LogOpsWithSets;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public class Tree {
    private final List<Tree> children;
    private final String info;


    public Tree(final String info) {
        this(info, new ArrayList<>());
    }

    public Tree(final String info, final List<Tree> children) {
        this.children = children;
        this.info = info;
    }


    public void addChild(final Tree child) {
        children.add(child);
    }

    public void addChild(final String info) {
        children.add(new Tree(info));
    }

    public String getInfo() {
        return info;
    }

    public List<Tree> getChildren() {
        return children;
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

    private String realName() {
        return "node" + hashCode();
    }

    private void visualize0(final FileWriter writer) throws IOException {
        String name = realName();
        writer.write("\t" + name + " [label=\"" + info + "\"]\n");

        for (Tree ch : children) {
            ch.visualize0(writer);
            writer.write("\t" + name + " -- " + ch.realName() + "\n");
        }
    }

    public void visualize(final FileWriter writer) throws IOException {
        writer.write("strict graph {\n");
        visualize0(writer);
        writer.write("}\n");
    }
}

package pl.telusmikolaj.implementations;

import pl.telusmikolaj.interfaces.Block;
import pl.telusmikolaj.interfaces.CompositeBlock;
import pl.telusmikolaj.interfaces.Structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall() {
        this.blocks = new ArrayList<>();
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        ArrayList<Block> resultBlock = new ArrayList<>();

        findBlockByPredicate(
                block -> color.equals(block.getColor()),
                this.blocks,
                resultBlock,
                "Color"
        );

        if (resultBlock.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(resultBlock.get(0));
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        ArrayList<Block> resultBlocks = new ArrayList<>();

        findBlockByPredicate(
                block -> material.equals(block.getMaterial()),
                blocks,
                resultBlocks,
                "Material"
        );

        return resultBlocks;
    }

    private void findBlockByPredicate(Predicate<Block> predicate, List<Block> blocks, List<Block> result, String attribute) {
        for (Block block : blocks) {
            if (predicate.test(block)) {
                result.add(block);
                if (attribute.equals("Color")) break;
            }
            if (isBlockComposite(block)) {
                findBlockByPredicate(predicate, ((CompositeBlock) block).getBlocks(), result, attribute);
            }
        }
    }

    private boolean isBlockComposite(Block block) {
        return block instanceof CompositeBlock;
    }

    @Override
    public int count() {
        return blocks
                .stream()
                .mapToInt(Block::getCount)
                .sum();
    }

    public void addBlock(Block block) {
        this.blocks.add(block);
    }
}

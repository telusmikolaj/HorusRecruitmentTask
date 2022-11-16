package pl.telusmikolaj.interfaces;

import java.util.List;

public interface CompositeBlock extends Block {
    List<Block> getBlocks();

    @Override
    default int getCount() {
        return getBlocks()
                .stream()
                .mapToInt(Block::getCount)
                .sum();
    }

}

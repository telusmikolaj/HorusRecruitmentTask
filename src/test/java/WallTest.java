import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.telusmikolaj.implementations.BlockImpl;
import pl.telusmikolaj.implementations.CompositeBlockImpl;
import pl.telusmikolaj.implementations.Wall;
import pl.telusmikolaj.interfaces.Block;
import pl.telusmikolaj.interfaces.CompositeBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class WallTest {

    private static final String COLOR_RED = "Red";
    private static final String COLOR_BLACK = "Black";
    private static final String COLOR_BLUE = "Blue";
    private static final String COLOR_PINK = "Pink";
    private static final String COLOR_WHITE = "White";
    private static final String MATERIAL_MARBLE = "Marble";
    private static final String MATERIAL_CONCRETE = "Concrete";
    private static final String MATERIAL_CLAY = "Clay";

    private Block redMarbleBlock;

    private Block blackMarbleBlock;

    private Block blueConcreteBlock;

    private Block whiteMarbleBlock;

    private CompositeBlock compositeBlock;

    private Wall wall;

    @BeforeEach
    void setUp() {
        this.redMarbleBlock = new BlockImpl(COLOR_RED, MATERIAL_MARBLE);
        this.blackMarbleBlock = new BlockImpl(COLOR_BLACK, MATERIAL_MARBLE);
        this.blueConcreteBlock = new BlockImpl(COLOR_BLUE, MATERIAL_CONCRETE);
        this.whiteMarbleBlock = new BlockImpl(COLOR_WHITE, MATERIAL_MARBLE);

        List<Block> blocks = new ArrayList<>();
        blocks.add(redMarbleBlock);
        blocks.add(blackMarbleBlock);

        this.compositeBlock = new CompositeBlockImpl(blocks);

        this.wall = new Wall();
        wall.addBlock(this.compositeBlock);
        wall.addBlock(blueConcreteBlock);
        wall.addBlock(whiteMarbleBlock);

    }

    @Test
    void findBlockByColorBlueShouldReturnBlueConcreteBlock() {
        Optional<Block> blockByColor = this.wall.findBlockByColor(COLOR_BLUE);

        assertTrue(blockByColor.isPresent());
        assertEquals(blockByColor.get(), this.blueConcreteBlock);
    }

    @Test
    void findBlockByColorRedShouldReturnRedMarbleBlock() {
        Optional<Block> blockByColor = this.wall.findBlockByColor(COLOR_RED);

        assertTrue(blockByColor.isPresent());
        assertEquals(blockByColor.get(), this.redMarbleBlock);
    }

    @Test
    void findBlockByColorPinkShouldReturnEmptyOptional() {
        Optional<Block> blockByColor = this.wall.findBlockByColor(COLOR_PINK);

        assertTrue(blockByColor.isEmpty());
    }

    @Test
    void findBlocksByMaterialMarbleShouldReturnThreeMarbleBlocks() {
        List<Block> blocksByMaterial = this.wall.findBlocksByMaterial(MATERIAL_MARBLE);

        assertEquals(3, blocksByMaterial.size());
        assertEquals(blocksByMaterial.get(0), this.redMarbleBlock);
        assertEquals(blocksByMaterial.get(1), this.blackMarbleBlock);
        assertEquals(blocksByMaterial.get(2), this.whiteMarbleBlock);
    }

    @Test
    void findBlocksByMaterialClayShouldReturnEmptyList() {
        List<Block> blocksByMaterial = this.wall.findBlocksByMaterial(MATERIAL_CLAY);

        assertTrue(blocksByMaterial.isEmpty());
    }

    @Test
    void countShouldReturnFive() {
        int numOfBlocks = this.wall.count();

        assertEquals(4, numOfBlocks);
    }

    @Test
    void countShouldReturnZero() {
        Wall secondWall = new Wall();
        int numOfBlocks = secondWall.count();

        assertEquals(0, numOfBlocks);
    }

    @Test
    void addBlock() {
        Wall thirdWall = new Wall();

        thirdWall.addBlock(this.compositeBlock);
        int numOfBlocks = thirdWall.count();

        assertEquals(2, numOfBlocks);
    }
}
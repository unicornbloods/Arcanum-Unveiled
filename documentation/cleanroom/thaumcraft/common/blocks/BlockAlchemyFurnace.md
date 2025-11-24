public class BlockAlchemyFurnace extends BlockContainer

## Properties

- Material: iron
- Hardness: 3.0
- Resistance: 17.0
- Step Sound: metal
- Block Bounds: Cube (0 - 1)
- Icon: "thaumcraft:metalbase"


## Behavior

getSubBlocks

- Add one new ItemStack with item = item, size = 1, damage = 0

getRenderType

- -1

isOpaqueCube
renderAsNormalBlock

- false


addCollisionBoxesToList

- Check if meta is 0 and collided with entity that is not EntityLivingBase
- If so, set block bounds to (0 / 0 / 0 - 0 / 0.7 / 1)
- Otherwise set block bounds to cube (0 - 1)
- At end, always call super

getLightValue

- If meta is 0, get TileAlchemyFurnaceAdvanced for current position
- If tile exists and has heat > 100, return heat / maxPower * 12.0

onEntityCollidedWithBlock

- Runs only on server and meta is 0 and entity is EntityItem
- Get TileAlchemyFurnaceAdvanced for current position
- If non null, calls process on tile with Item from EntityItem
- If process was true:
    - Play sound at entity pos: "thaumcraft:bubble", with volume 0.2 and frequency 1.0 + (random float) * 0.4
    - Decrease entity item stack by one, removing the entity if it's 0

getItemDropped

- If meta is 0 return item for block blockStoneDevice
- If meta is 1, 2, 3, 4 return item for blockMetalDevice
- Else return Item for id 0

damageDropped

- meta 1 or 4: return 3
- meta 3: return 9
- meta 2: return 1
- else return 0

createTileEntity

- meta 0: TileAlchemyFurnaceAdvanced
- meta 1: TileAlchemyFurnaceAdvancedNozzle
- Else null

hasComparatorInputOverride

- true

getComparatorInputOverride

- Get TileAlchemyFurnaceAdvancedNozzle for current position
- If null, other TileEntity or tileEntity.furnace is null return 0
- Get TileEntity.furnace
- return vis / maxVis * 14, add 1 if vis > 0

createNewTileEntity

- null

breakBlock

- only runs on server
- If meta is not 0:
    - Iterate over all blocks in cube (x - 1, y - 1, z -1 to x + 1, y + 1, z + 1)
    - Check if block is the same as this and meta us 0
    - If so, get TileAlchemyFurnaceAdvanced. If non null, set tile.destroy flag true
- If meta is 0:
    - Iterate over all blocks in cube (x - 1, y, z -1 to x + 1, y + 1, z + 1)
    - Skip block at current position and blocks that are not this block
    - Get meta at position
    - Set block to Block.getBlockFromItem(getItemDropped(meta)) with meta damageDropped(meta)

randomDisplayTick

- if meta is 0, tileEntity is TileAlchemyFurnaceAdvanced and tile.vis > 0
    - Add new FXSlimyBubble with
        - x / z = block pos + random float
        - y = blockY + 1
        - scale = 0.06 + (random float) * 0.06
        - alpha = 0.8
        - color = 0.6F - (random float) * 0.2, 0.0, 0.6 + (random float) * 0.2
    - Add new FXSlimyBubble with
        - x / z = block pos - 0.6 + (random float) * 0.2D + (50% chance either 0 or 2)
        - y = blockY + 2
        - scale = 0.06 + (random float) * 0.06
        - alpha = 0.8
        - color = 0.6F - (random float) * 0.2, 0.0, 0.6 + (random float) * 0.2
    - Play sound "liquid.lavapop"
        - x / z: block pos + (random float)
        - y: y + block maxY
        - volume: 0.1 + rand.nextFloat() * 0.1
        - pitch: 0.9 + rand.nextFloat() * 0.15
        - distanceDelay false

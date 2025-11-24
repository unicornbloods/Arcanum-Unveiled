public class BlockAiry extends BlockContainer

## Meta mapping

0 - Node (normal) 
1 - Nitor
4 - Warding stone fence
5 - Node (energized)


## Properties

- Custom material: Airy
- Step sound: Silent
- Ticks randomly
- Blank icon

## Behavior

addHitEffects

- When meta 0 or 5: 50% chance to add infused block sparkle at block position

addDestroyEffects

- When meta 0 or 5:
    - Render burst at (x + 0.5, y + 0.5, z + 0.5)
    - Play "thaumcraft:craftfail" sound at (x + 0.5, y + 0.5, z + 0.5)

getBlockHardness

- meta 0 / 5   -> 2.0
- meta 10 / 11 -> 100.0
- meta 12      -> -1.0

getExplosionResistance

- meta 0 / 5    -> 200.0
- meta 10 / 11: -> 50.0F
- meta 12:      -> Float.MAX_VALUE
  
getLightValue

- meta 1 / 2 / 3       -> 15
- meta 4 / 12          -> 0
- meta 0 / 5 / 10 / 11 -> 8

setBlockBoundsBasedOnState

- meta 3 / 4 / 10 / 11 / 12 -> set block bounds to cube (0 - 0)
- otherwise                 -> set block bounds to cube (0.3 - 0.7)

isReplaceable

- meta 2 / 3 / 4 / 10 / 11 -> true

canBeReplacedByLeaves

- meta 2 / 3 / 4 -> true

isLeaves

- meta 2 / 3 -> true

addCollisionBoxesToList

- if meta is 12:
    - sets block bounds to cube (0 - 1)
    - call super
- if meta is 4 and entity is EntityLivingBase but not player:
    - Checks block at (x, y -1, z). If block below is not "block cosmetic solid", check one below. No loop, only once
    - if block below is not isBlockIndirectlyGettingPowered, do same as in 12
  
getBlocksMovement

- when meta not 4, always true
- Check tile for the three blocks below. If tile is TileWardingStone, return isBlockIndirectlyGettingPowered for that block. If not, continue further down.

getCollisionBoundingBoxFromPool

- meta 4 / 12 -> call super
- Else null

getSelectedBoundingBoxFromPool

- meta 0 / 2 / 3 / 4 / 5 / 10 / 11 / 12 -> return AxisAlignedBB.getBoundingBox from (0 - 0)
- Else call super

renderAsNormalBlock
isSideSolid
isOpaqueCube

- false

getRenderType()

- -1

damageDropped

- returns input

getItemDropped
getItem

- if meta is 1 return "itemSource"
- else item with id 0

onBlockHarvested

- if Normal node and on server:
    - Check if tile entity is INode and aspect array has entries
    - If so, spawn itemWispEssence for each aspect at (x, y, z).
        - Skip aspects with < 5 amount
        - Each aspect spawns (aspect amount / 10) rounded up items
        - Each item gets 2 of its aspect.

randomDisplayTick

- if meta is 1:
    - Add new FXSparkle to ParticleEngine with
        - gravity 0.05
        - noClip true
        - x / y / z of block + 0.5
        - velX / velY / velZ of block + 0.5 + (random nextFloat - random nextFloat) / 3
        - scale 1.0
        - type 6
        - max age 3
- if meta is 2 and 1/500 chance:

    - Render wispFX3 effect with
        - x1 / y1 / z1 of block + (random nextInt(3) - random nextInt(3))
        - x2 / y2 / z2 like x1 / x2 / x3 but + (random nextInt(3) - random nextInt(3))
        - size 0.1 (random nextFloat) * 0.1
        - type 7
        - shrink false
        - gravity: 50% change to 0.033 or -0.033

- if meta is 10 or 11

    - Add new FXSparkle to ParticleEngine with
        - x / z of block + (random nextFloat)
        - y: block y + 0.1515F + ((random nextFloat) * 0.33F) / 2.0F
        - if meta 10: RBGColorF (0.65F + (random nextFloat) * 0.1F, 1.0F, 1.0F), also set alpha to 0.8
        - if meta 11: RBGColorF(0.3F (random nextFloat) * 0.1F, 0.0F, 0.5F + (random nextFloat) * 0.2F)
    - if 1/50 chance: play "thaumcraft:jacobs" at with (0.5F, 1.0F + ((random nextFloat) - (random nextFloat)) * 0.2F, false);

createTileEntity

- meta 0 -> TileNode
- meta 1 -> TileNitor
- meta 4 -> TileWardingStoneFence map
- meta 5 -> TileNodeEnergized
- else -> null

createNewTileEntity

- return null

getSubBlocks

- Add new ItemStack with item = item, size = 1, damage = 0

onBlockPlacedBy

- meta is 0 and entity is EntityPlayer: Create random node at (x, y, z)

isAir

- meta 2 / 3 / 10 / 11 -> true

onNeighborBlockChange

- if meta is not 5: return
- Get TileEntity below. if it is not TileNodeStabilizerblock or block below isBlockIndirectlyGettingPowered : explodify()
- Get TileEntity above. if it is not TileNodeConverter: explodify()

explodify

- if not on server return
- set block to air
- create explosion at block center, strength 3, not smoking
- Repeat 50 times:
    - Get x / y / z of block + (random nextInt(8) - random nextInt(8))
    - If is air:
        - if y < block y: Set block to blockFluxGoo
        - else set block to blockFluxGas

onEntityCollidedWithBlock

- if meta is 10:
    - attackEntityFrom magic, damage is 1 + (random nextInt(2))
    - set entity motion x / y to 80% of its previous value
    - if on server and 1% chance: Set block to air
- if meta is 11 and entity is not IEldritchMob
    - if 1% chance: attackEntityFrom wither, damage is 1.0
    - set entity motion x / y to 66% of its previous value
    - if entity is player: addExhaustion 0.05
    - if entity is EntityLivingBase: Add Weakness effect, duration 100, strength 1, isAmbient true

updateTick

- if (meta 10 or 11) and on server: Set block to air at (x, y, z)

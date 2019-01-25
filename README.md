# SpigotMazeGenerator
## Intro
This is a spigot plugin designed to create mazes. This plugin requires [Fast Async World Edit](https://www.spigotmc.org/resources/fast-async-worldedit-voxelsniper.13932/). Mazes are generated asynchronously by using /mazeGen sizeX sizeZ. The maze spawn in increasing X and Z, where you're standing. 

## Pictures

Here are different mazes generated by this plugin.

![alt text](https://github.com/Exeton/SpigotMazeGenerator/blob/master/Pics/Sample%20Maze.png?raw=true)
![alt text](https://github.com/Exeton/SpigotMazeGenerator/blob/master/Pics/TnTTrap.png?raw=true)


## Config
```yaml
WallMaterial: SMOOTH_BRICK
FloorMaterial: CobbleStone

#How many blocks per trap.
#I.e. if BlocksPerTrap is 100, there will be 1 trap in every 10x10 area
BlocksPerTrap: 200

GenerateTraps: true
GenerateLavaTrap: true
GenerateTnTTrap: true

#Path size option: Coming soon
PathSize: 1
```

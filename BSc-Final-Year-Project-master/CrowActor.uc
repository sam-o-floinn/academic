Class CrowActor extends Actor placeable config(Game);

var config int MyConfigInt;


simulated function PostBeginPlay()
{
  `log("A bird actor has been spawned!" @ self);
    `log("MyConfigInt:" @ MyConfigInt);

    `log("Rotation: " @ Rotation);
    SetLocation(Location + vect(0, 0, 112));
    `log("Distance: " @ VSize(Location)); //returns the distance between MyActor and the world's origin.
}

defaultproperties
{
        Begin Object Class=SpriteComponent Name=Sprite
         Sprite=Texture2D'EditorResources.S_NavP'
         HiddenGame=True
        End Object
        Components.Add(Sprite);

        Begin Object Class=ArrowComponent Name=Arrow
        End Object
        Components.Add(Arrow);
}
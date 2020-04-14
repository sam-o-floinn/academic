class CrowPlayerController extends UTPlayerController;

var vector PlayerViewOffset;

simulated function PostBeginPlay()
{
  super.PostBeginPlay();
  bNoCrosshair = true;
  `log("The crow player controller has finally spawned!");

  Pawn.Mesh.SetHidden(false);
}

auto state Walking extends PlayerWalking
{
  simulated function BeginState(Name PreviousStateName)
  {
    Pawn.SetMovementPhysics();
    `log(GetStateName() );
    `log("Hello, the crow is now walking!");
  }
/*
*vector NewAccel, eDoubleClickDir DoubleClickMove, rotator DeltaRot)
*/
  function ProcessMove(float DeltaTime, vector NewAccel, eDoubleClickDir DoubleClickMove, rotator DeltaRot)
  {
    local vector X, Y, Z;
    `log(NewAccel);

    GetAxes(Pawn.Rotation, X, Y, Z);
    NewAccel = PlayerInput.aForward * X + PlayerInput.aStrafe*Y;
    NewAccel.Y = 0;
    NewAccel = Pawn.AccelRate * Normal(NewAccel);
    /*
    `log(NewAccel);
    `log("You should see the AltAccel directly above this message!!");
    */

    super.ProcessMove(DeltaTime, X * VSize(NewAccel), DoubleClickMove, DeltaRot);
  }
}

exec function TakeFlight()
{
  //JumpOffOfPawn();
  `log("Taking flight!");
  if (!IsInState('PlayerFlying'))
  {
    GoToState('PlayerFlying');
  }
  else
  {
    `log("Already in PlayerFlying state?");
    GoToState('Walking');
  }
}

state CrowFlying extends PlayerFlying
{
  simulated function BeginState(name PreviousStateName)
  {
    `log("Now in Crow-Flying state!");
  }
  /*exec function TryLanding()
  {
    GoToState('Walking');
  }
  */
}


defaultproperties
{
  bBehindView = true
//  CameraClass=class'AsTheCrowFlies.CrowCamera'

  PlayerViewOffset = (X=256, Y = 0, Z = 0)
}
/*
state Walking
{

}

state Flying
{
 function ProcessMove()
 {

 }

 function PlayerMove()
 {

 }
} */
/* The player crow.
* Since this is the player, we don't need to write any scripting behaviours here.

 *However, we DO need to define movement behaviours.

* Birds do not move sideways by choice. In rotation, they have a good pitch and yaw, but NO ROLL.

* The crow also has a MakeCache() function they can use when not flying, that will store one item in their inventory.
* Doing this creates a spot on the ground at where they're standing, which will be evident by its brown soil.
* Cached foods are invisible to other characters, but they take four seconds to create!

* If the player right-clicks on this while their inventory is empty, they will get whatever item is stored inside it.
* If their inventory is full, nothing happens.

* The player can be bumped by a CompetingBird: they are stunned for one second and lose the item they are carrying.

* The player can eat any food they collect, but only when at their nest.
* They can also give their food to the CrowNest actor by walking over them.
*/
/*For reference, here's ProcessMove as in UTPlayerController in the PlayerWalking state:
function ProcessMove(float DeltaTime, vector NewAccel, eDoubleClickDir DoubleClickMove, rotator DeltaRot)
{
 if ( (DoubleClickMove == DCLICK_Active) && (Pawn.Physics == PHYS_Falling) )
    DoubleClickDir = DCLICK_Active;
    else if ( (DoubleClickMove != DCLICK_None) && (DoubleClickMove < DCLICK_Active) )
    {
	if ( UTPawn(Pawn).Dodge(DoubleClickMove) )
	DoubleClickDir = DCLICK_Active;
    }

    Super.ProcessMove(DeltaTime,NewAccel,DoubleClickMove,DeltaRot);
}

function PlayerMove( float DeltaTime )
{
 GroundPitch = 0;
 Super.PlayerMove(DeltaTime);
}
*/
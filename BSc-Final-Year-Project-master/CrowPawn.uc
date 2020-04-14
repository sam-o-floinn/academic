class CrowPawn extends UTPawn;

var int IsoCamAngle;
var float CamOffsetDistance;

simulated function PostBeginPlay()
{
	super.PostBeginPlay();
	SetMeshVisibility(true);
	`log("The player crow pawn has spawned!");
}

event Bump(Actor Other, PrimitiveComponent OtherComp, vector HitNormal)
{
   /* if( CompetingBird(Other) != none )
    {
        `log("Ow, that magpie bumped into me!");
        GoToState('Stunned');
    } */
}

simulated function SetMeshVisiblity(bool bVisible)
{
	super.SetMeshVisibility(bVisible);
	Mesh.SetOwnerNoSee(false);
}

simulated function bool CalcCamera(float DeltaTime, out vector out_CamLoc, out rotator out_CamRot, out float out_FOV)
{
    local Vector HitLocation, HitNormal;

    out_CamLoc           = Location;
    out_CamLoc.X        -= Cos(Rotation.Yaw * UnrRotToRad) * Cos(IsoCamAngle * UnrRotToRad) * CamOffsetDistance;
    out_CamLoc.Y        -= Sin(Rotation.Yaw * UnrRotToRad) * Cos(IsoCamAngle * UnrRotToRad) * CamOffsetDistance;
    out_CamLoc.Z        -= Sin(IsoCamAngle * UnrRotToRad) * CamOffsetDistance;

    out_CamRot.Yaw       = Rotation.Yaw;
    out_CamRot.Pitch     = IsoCamAngle;
    out_CamRot.Roll      = 0;



    if (Trace(HitLocation, HitNormal, out_CamLoc, Location, false, vect(12, 12, 12)) != none)
    {
        out_CamLoc = HitLocation;
    }

    return true;
}

defaultproperties
{
    IsoCamAngle=20
    CamOffsetDistance=190.0
}
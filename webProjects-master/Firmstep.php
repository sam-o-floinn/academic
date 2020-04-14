<!DOCTYPE html>
<!--@author Sam O'FloinnFirmstep
Firmstep Test Interview Program
-->
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap core CSS -->
    <link href="dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="dist/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="theme.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

	<title>
	Firmstep Queue
	</title>

	<body>
	<?php
		include 'dbh.php';
	?>
	<div id="intro" class="container-fluid">
	  <div class="jumbotron" align="center">
		<h1>Service Management</h1>
		<p>Pick a service on the left. See the queue on the right.</p>
	  </div>
	</div>
	
	<div id="serviceWrap" class="panel panel-group container-fluid row" >
		<div id="customerForm" class="panel-default col-md-6 float-left" style="border:1px solid #cecece">
			<div class="panel-heading"><h3>Service:</h3></div>
				
			<div class="panel-body panel-default col-md-6 float-left">
				<form class="form-group" action="#" method="post">
					<div class="radio"> <!--class="container-fluid"-->
						<input type="radio" name="service" value="Housing">Housing</input> <br/>
						<input type="radio" name="service" value="Benefits">Benefits</input> <br/>
						<input type="radio" name="service" value="Council Tax">Council Tax</input><br/>
						<input type="radio" name="service" value="Fly-tipping">Fly-tipping</input><br/>
						<input type="radio" name="service" value="Missed Bin">Missed Bin</input><br/>
					</div>

					<div id="classes" class="btn-group" data-toggle="buttons">
						<label class="btn btn-primary active">
							<input type="radio" name="customer" id="citi" value="Citizen">Citizen</button>
						</label>
						<label class="btn btn-primary">
							<input type="radio" name="customer" id="orgi" value="Organisation">Organisation</button>
						</label>
						<label class="btn btn-primary">
							<input type="radio" name="customer" id="anon" value="Anonymous">Anonymous</button>
						</label>
					</div>
					
					<div id="titles" class="form-group">
						<label class="str-only" for="title">Title:</label>
						<select class="form-control" name="title">
							<option value="Mr.">Mr</option>
							<option value="Ms.">Ms</option>
							<option value="Mrs.">Mrs</option>
						</select>
					</div>
					
					<div id="names" class="form-group">
						<label class="str-only" for="first">First Name:</label> <br/>
						<input type="text" class="form-control" name="first"> <br/>
						<label class="str-only" for="last">Last Name:</label> <br/>
						<input type="text" class="form-control" name="last">
					</div>
					<input type="submit" class="btn btn-info" name="submit" value="Submit" />
				</form>
			</div>
		</div>
		<!--Queue:
		A table displaying all of the items in a queued array-->
		<div id="queueTable" class=" panel-default col-md-6 float-right" style="border:1px solid #cecece">
			<div class="panel-heading"><h3>Queue:</h3></div>

			<div class="panel-body">
				<div class="col-md-6">
					<table class="table table-bordered">
					<tr>
						<th>#</th>
						<th>Type</th>
						<th>Name</th>
						<th>Service</th>
						<th>Queued At</th>
					</tr>
						
					<?php
						if (isset($_POST["submit"])) {
							if (isset($_POST["service"])) {
								$service = $_POST["service"];
								$customer = $_POST["customer"];
								$title = $_POST["title"];
								
								if($customer == "Citizen") {
									$name = $title . " " . $_POST["first"] . " " . $_POST["last"];
								}
								else if ($customer == "Anonymous") { //name is anonymous
									$name = "Anonymous";
								}
								else { //if customer is an organisation or unmarkedif ($customer == "orgi") {
									$name = $_POST["first"] . " " . $_POST["last"];
								}
								
								/*
								* sql update. This section assumes the existence of a MySQL database and table called "firm"
								*/
								$query = "INSERT INTO firm (service, customer, name, time) 
								VALUES ('". $service . "', '". $customer . "', '" . $name . "', '" . date('H:i') . "');";
								
								$result = mysqli_query($conn, $query);
								
								if (!$result) {
									printf("Error: %s\n", mysqli_error($conn));
									exit();
								}
							} else {
								echo "Service undefined. Please resubmit data.";
							}
							
							
						}
						
						$query = "SELECT * FROM firm;";
						$result = mysqli_query($conn, $query);
						if (!$result) {
							printf("Error: %s\n", mysqli_error($conn));
							exit();
						}
						$row = mysqli_fetch_array($result);
						
						while ($row = mysqli_fetch_row($result)) {
							echo "<tr>
							<td>" . ($row[0] - 1) . "</td>
							<td>" . $row[1] . "</td>
							<td>" . $row[2] . "</td>
							<td>" . $row[3] . "</td>
							<td>" . $row[4] . "</td>
							</tr>";
						}
					?>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
	<script src="dist/js/bootstrap.min.js"></script>
	<script src="assets/js/docs.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>

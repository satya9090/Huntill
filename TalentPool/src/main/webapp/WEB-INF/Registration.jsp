

<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%> --%>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>


<hr>
<div class="container bootstrap snippet">

	<div class="row">

		<div class="col-sm-9">
			<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#home">Menu 1</a></li>
				<li><a data-toggle="tab" href="#messages">Menu 2</a></li>
				<li><a data-toggle="tab" href="#settings">Menu 3</a></li>
				<li><a data-toggle="tab" href="#menu4">Menu 4</a></li>
				<li><a href="loginPage">LOGIN</a></li>
			</ul>


			<div class="tab-content">
				<div class="tab-pane active" id="home">
					<hr>
					<form class="form" action="saveCandidateInformation" method="post"
						id="registrationForm">
						<div class="form-group">

							<div class="col-xs-6">
								<label for="first_name"><h4>First name</h4></label> <input
									type="text" class="form-control" name="firstName"
									id="firstName" placeholder="first name"
									title="enter your first name if any.">
							</div>
						</div>
						<div class="form-group">

							<div class="col-xs-6">
								<label for="middle_name"><h4>middle name</h4></label> <input
									type="text" class="form-control" name="middleName"
									id="middle_name" placeholder="last name"
									title="enter your middle_name if any.">
							</div>
						</div>
						<div class="form-group">

							<div class="col-xs-6">
								<label for="last_name"><h4>Last name</h4></label> <input
									type="text" class="form-control" name="lastName"
									id="last_name" placeholder="last name"
									title="enter your last name if any.">
							</div>
						</div>
						
						<div class="form-group">

							<div class="col-xs-6">
								<label for="last_name"><h4>User Id</h4></label> <input
									type="text" class="form-control" name="userId"
									id="userId" placeholder="userId"
									title="enter your userId if any.">
							</div>
						</div>
						<div class="form-group">

							<div class="col-xs-6">
								<label for="last_name"><h4>Password</h4></label> <input
									type="password" class="form-control" name="password"
									id="password" placeholder="password"
									title="enter your password if any.">
							</div>
						</div>

						<div class="form-group">

							<div class="col-xs-6">
								<label for="email"><h4>Email id</h4></label> <input type="email"
									class="form-control" name="emailId" id="emailId"
									placeholder="you@email.com" title="enter your email.">
							</div>
						</div>
						
						<div class="form-group">

							<div class="col-xs-6">
								<label for="alternate_email_id"><h4> Alternate Email id</h4></label> <input type="email"
									class="form-control" name="alternateEmailId" id="alternateEmailId"
									placeholder="you@email.com" title="enter your alternate_email_id">
							</div>
						</div>
						<div class="form-group">

							<div class="col-xs-6">
								<label for="phone"><h4>Location id:</h4></label> <input type="text"
									class="form-control" name="location" id="location"
									placeholder="somewhere" title="enter a location">
							</div>
						</div>

						<div class="form-group">
							<div class="col-xs-6">
								<label for="mobile"><h4>Contact_number</h4></label> <input type="text"
									class="form-control" name="contactNumber" id="mobile"
									placeholder="enter mobile number"
									title="enter your mobile number if any.">
							</div>
						</div>

						<div class="form-group">

							<div class="col-xs-6">
								<label for="collageName"><h4>institute name</h4></label> <input
									type="text" class="form-control" id="instituteName"
									name="instituteName" placeholder="collage_name"
									title="enter your collage_name">
							</div>
						</div>

						<div class="form-group">

							<div class="col-xs-6">
								<label for="passing_year"><h4>Passing Year</h4></label> <input
									type="text" class="form-control" id="passingYear"
									name="passingYear" placeholder="passing_year"
									title="enter your passing_year">
							</div>
						</div>
						<div class="form-group">

							<div class="col-xs-6">
								<label for="Grade"><h4>Grade</h4></label> <input
									type="text" class="form-control" id="grade"
									name="grade" placeholder="grade"
									title="enter your grade">
							</div>
						</div><br>
						<div class="form-group">

							<div class="col-xs-6">
								<label for="passing_year"><h4>Gender</h4></label>
								 <select class="form-control buttom"
									name="gender">

									<option value="Male">Male</option>
									<option value="Female">Female</option>
									<option value="Other">Other</option>

								</select>
							</div>
						</div><br>
						<div class="form-group">

							<div class="col-xs-6"> 
								<label for="passing_year"><h4>isEmployer</h4></label>
								 <select class="form-control buttom"
									name="isEmployer">

									<option value="Y">Yes</option>
									<option value="N">No</option>
									

								</select>
							</div>
						</div>

						<div class="form-group">
							<div class="col-xs-12">
								<br>
								<button class="btn btn-lg btn-success" type="submit">
									<i class="glyphicon glyphicon-ok-sign"></i> Save
								</button>

							</div>
						</div>
					</form>

					<hr>

				</div>
				<!--/tab-pane-->
				<div class="tab-pane" id="messages">


					<h2></h2>

					<hr>
					<form class="form" action="saveQuestionAnswer" method="post"
						id="registrationForm">

						<div class="form-group"> 
							<div class="col-xs-6">

								What are you looking for ?<input type="hidden"
									class="form-control" name="question_1" id="first_name"
									value="1" disabled></br> <select class="form-control buttom"
									name="TalentQuestion">

									<option value="Full time">Full time</option>
									<option value="Part time">Part time</option>
									<option value="Internship">Internship</option>

								</select>

							</div>
						</div>
						<br>
						<div class="form-group">
							<div class="col-xs-6">

								Where would you like to work ?<input type="hidden"
									class="form-control" name="question_2" id="first_name"
									value="2" disabled></br> <select class="form-control buttom"
									name="TalentQuestion">

									<option value="New York">New York</option>
									<option value="Los Angeles">Los Angeles</option>
									<option value="Chicago">Chicago</option>
									<option value="Washington">Washington</option>
									<option value="Washington">San Francisco</option>
									<option value="Washington">Boston</option>
									<option value="Washington">Dallas</option>
									<option value="Washington">Anywhere</option>
								</select>

							</div>
						</div>
						<br>
						<div class="form-group">
							<div class="col-xs-6">

								which industries are you on radar Internet & Software ?<input
									type="hidden" class="form-control" name="question_3"
									id="first_name" value="3" disabled><br> <select
									class="form-control buttom" name="TalentQuestion">

									<option value="Computer Networking">Computer
										Networking</option>
									<option value="Investment banking">Investment banking</option>
									<option value="Research">Research</option>
									<option value="Healthcare">Healthcare</option>
									<option value="Medical devices">Medical devices</option>
									<option value="defense">defense</option>
								</select>

							</div>
						</div>
						<br>
						<div class="form-group">
							<div class="col-xs-6">

								Types of Employers ?<input type="hidden" class="form-control"
									name="question_4" id="first_name" value="4" disabled><br>
								<select class="form-control buttom" name="TalentQuestion">

									<option value="Start up">Start up</option>
									<option value="High Growth">High Growth</option>
									<option value="Established">Established</option>
									<option value="Non Profit">Non Profit</option>
									<option value="Large sized company">Large sized
										company</option>
									<option value="Mid Sized company">Mid Sized company</option>
									<option value="Small Company">Small Company</option>
								</select>

							</div>
						</div>
						<br>
						<div class="form-group">
							<div class="col-xs-6">

								what u want ?<input type="hidden" class="form-control"
									name="question_5" id="first_name" value="5" disabled><br>
								<select class="form-control buttom" name="TalentQuestion">

									<option value="1">satya</option>
									<option value="">nucigent</option>
									<option value="">satya</option>
									<option value="">jitesh</option>
								</select>

							</div>
						</div>

						<br>

						<div class="form-group">
							<div class="col-xs-12">
								<br>
								<button class="btn btn-lg btn-success" type="submit">
									<i class="glyphicon glyphicon-ok-sign"></i> Save
								</button>

							</div>
						</div>
					</form>

				</div>
				<!--/tab-pane-->
				<div class="tab-pane" id="settings">


					<hr>
					<form class="form" action="saveCandidateExperience" method="post"
						id="registrationForm">
						<div class="form-group">

							<div class="col-xs-6">
								<label for="Project Name"><h4>Project Name</h4></label> <input
									type="text" class="form-control" name="projectName"
									id="projectName" placeholder="Project Name"
									title="enter your Project Name if any.">
							</div>
						</div>
						<div class="form-group">

							<div class="col-xs-6">
								<label for="last_name"><h4>start date</h4></label> <input
									type="date" class="form-control" name="startDate"
									id="startDate" placeholder="start_date"
									title="enter your start date.">
							</div>
						</div>

						<div class="form-group">

							<div class="col-xs-6">
								<label for="end_date"><h4>end date</h4></label> <input
									type="date" class="form-control" name="endDate" id="endDate"
									placeholder="end_date" title="enter your end_date">
							</div>
						</div>

						<div class="form-group">
							<div class="col-xs-6">
								<label for="mobile"><h4>Technology used</h4></label> <input
									type="text" class="form-control" name="TechnologyUsed"
									id="TechnologyUsed" placeholder="enter Technology"
									title="enter your Technology if any.">
							</div>
						</div>
						<div class="form-group">

							<div class="col-xs-6">
								<label for="description"><h4>description</h4></label> <input
									type="text" class="form-control" name="description"
									id="description" placeholder="description"
									title="enter your description">
							</div>
						</div>

						<div class="form-group">
							<div class="col-xs-12">
								<br>
								<button class="btn btn-lg btn-success pull-right" type="submit">
									<i class="glyphicon glyphicon-ok-sign"></i> Save
								</button>

							</div>
						</div>
					</form>


				</div>

				<div class="tab-pane" id="menu4">


					<hr>
					<form class="form" action="##" method="post" id="registrationForm">

						<div class="form-group">
							<div class="col-xs-12">
								<br>
								<button class="btn btn-lg btn-success pull-right" type="submit">
									<i class="glyphicon glyphicon-ok-sign"></i> Save
								</button>

							</div>
						</div>
					</form>


				</div>

				<script type="text/javascript">
					$(document).ready(function() {

						var readURL = function(input) {
							if (input.files && input.files[0]) {
								var reader = new FileReader();

								reader.onload = function(e) {
									$('.avatar').attr('src', e.target.result);
								}

								reader.readAsDataURL(input.files[0]);
							}
						}

						$(".file-upload").on('change', function() {
							readURL(this);
						});
					});
				</script>

			</div>
			<!--/tab-pane-->
		</div>
		<!--/tab-content-->

	</div>
	<!--/col-9-->
</div>
<!--/row-->

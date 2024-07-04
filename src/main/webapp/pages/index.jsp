<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Student Details</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<style>
.dropdown {
	display: inline;
}
</style>

</head>
<body>

	<form id="searchForm" action="/search" method="get"
		class="d-flex justify-content-around align-items-center mb-3">
		<input type="text" name="registrationNumber"
			placeholder="Registration Number"
			class="form-control m-3 p-2 rounded dark-border"> <input
			type="text" name="firstName" placeholder="First Name"
			class="form-control m-3 p-2 rounded dark-border"> <input
			type="text" name="lastName" placeholder="Last Name"
			class="form-control m-3 p-2 rounded dark-border"> <select
			name="courseName" class="form-select m-3 p-2 rounded dark-border">
			<option selected disabled>Course Name:</option>
			<c:forEach var="course" items="${courses}">
				<option value="${course}"
					<c:if test="${course eq search.courseName}">selected</c:if>>
					${course}</option>
			</c:forEach>
		</select> <select name="branchName"
			class="form-select m-3 p-2 rounded dark-border">
			<option selected disabled>Branch Name:</option>
			<c:forEach var="branch" items="${branches}">
				<option value="${branch}"
					<c:if test="${branch eq search.branchName}">selected</c:if>>
					${branch}</option>
			</c:forEach>
		</select>

		<button type="submit" class="btn btn-primary ml-4 mb-1">Search</button>
		<a href="/home"><button type="button"
				class="btn btn-danger ml-2 mb-1">Reset</button></a> <a href="/loadAll"><button
				type="button" class="btn btn-success ml-2 mb-1">Load All</button></a>
	</form>

	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th scope="col">S.No</th>
				<th scope="col">Registration Number</th>
				<th scope="col">First Name</th>
				<th scope="col">Last Name</th>
				<th scope="col">Gender</th>
				<th scope="col">Course Name</th>
				<th scope="col">Branch Name</th>
				<th scope="col">Enrollment Date</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty students}">

				<c:set var="a" value="1" />
				<c:forEach var="student" items="${students}">
					<tr>
						<td scope="col">${a}</td>
						<td>${student.registrationNumber}</td>
						<td>${student.firstName}</td>
						<td>${student.lastName}</td>
						<td>${student.gender}</td>
						<td>${student.course != null ? student.course.courseName : 'N/A'}</td>
						<td>${student.branch != null ? student.branch.branchName : 'N/A'}</td>
						<td>${student.enrollmentDate}</td>
					</tr>
					<c:set var="a" value="${a + 1}" />
				</c:forEach>
			</c:if>

			<c:if test="${empty students}">
				<td colspan="8" class="text-center">No Records Found</td>
			</c:if>
			<c:if test="${not empty message}">
				<tr>
					<td colspan="8" class="text-center text-danger">${message}</td>
				</tr>
			</c:if>
		</tbody>
	</table>
	<div id="footer" class="fixed-bottom">
		<div class="d-flex justify-content-around">
			<a href="/excel"><button type="button"
					class="btn btn-primary ml-5 mb-1">Send as Excel</button></a> <a
				href="/pdf"><button type="button"
					class="btn btn-primary ml-5 mb-1">Send as Pdf</button></a>
			<button type="button" class="btn btn-primary ml-5 mb-1"
				onclick="openModal()">New Record</button>
		</div>
	</div>

	<!-- login modal -->
	<div class="modal fade mt-5" id="exampleModalLong" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLongTitle"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Log in to
						Continue</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="loginForm" action="/login" method="post">
						<!-- UserType Dropdown -->
						<label>Select account type</label> <select name="userType"
							class="form-select m-3 p-2  rounded dark-border " required>
							<option selected disabled>Account Type:</option>
							<c:forEach var="account" items="${accountType}">
								<option value="${account}">${account}</option>
							</c:forEach>
						</select>
						<div class="form-group">
							<label for="userName">Username: </label> <input type="text"
								class="form-control" id="userName" placeholder="Enter Username"
								name="userName" required>
						</div>
						<div class="form-group">
							<label for="password">Password: </label> <input type="password"
								class="form-control" id="password" placeholder="Enter Password"
								name="password" required>
						</div>

					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal" disabled>Cancel</button>
					<button type="submit" form="loginForm" class="btn btn-primary">Login</button>
				</div>
			</div>
		</div>
	</div>


	<!-- Modal for new data of student/new entry -->
	<div class="modal fade" id="studentModal" tabindex="-1" role="dialog"
		aria-labelledby="studentModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="studentModalLabel">Student
						Information</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<!-- student form input  -->
					<form id="studentForm" action="/handleStudent" method="post"
						enctype="multipart/form-data">
						<div class="form-group">
							<label for="firstName">First Name</label> <input type="text"
								class="form-control" id="firstName" name="firstName" required>
						</div>
						<div class="form-group">
							<label for="middleName">Middle Name</label> <input type="text"
								class="form-control" id="middleName" name="middleName">
						</div>
						<div class="form-group">
							<label for="lastName">Last Name</label> <input type="text"
								class="form-control" id="lastName" name="lastName" required>
						</div>
						<div class="form-group">
							<label for="gender">Gender</label> <select class="form-control"
								id="gender" name="gender" required>
								<option value="Male">Male</option>
								<option value="Female">Female</option>
								<option value="Other">Other</option>
							</select>
						</div>
						<div class="form-group">
							<label for="dateOfBirth">Date of Birth</label> <input type="date"
								class="form-control" id="dateOfBirth" name="dateOfBirth"
								required>
						</div>
						<div class="form-group">
							<label for="phoneNumber">Phone Number</label> <input type="text"
								class="form-control" id="phoneNumber" name="phoneNumber">
						</div>
						<div class="form-group">
							<label for="course">Course</label> <select class="form-control"
								id="course" name="course" required>
								<option value="">Select Course</option>
							</select>
						</div>

						<div class="form-group">
							<label for="branch">Branch</label> <select class="form-control"
								id="branch" name="branch" disabled required>
								<option value="">Select Branch</option>
							</select>
						</div>
						<div class="form-group">
							<label for="studentImage">Student Image</label> <input
								type="file" class="form-control" id="studentImage"
								name="studentImage" required="required">
						</div>
						<div class="form-group">
							<label for="street">Street</label> <input type="text"
								class="form-control" id="street" name="address.street" required>
						</div>

						<div class="form-group">
							<label for="city">City</label> <input type="text"
								class="form-control" id="city" name="address.city" required>
						</div>

						<div class="form-group">
							<label for="state">State</label> <input type="text"
								class="form-control" id="state" name="address.state">
						</div>

						<div class="form-group">
							<label for="postalCode">Postal Code</label> <input type="text"
								class="form-control" id="postalCode" name="address.postalCode">
						</div>

						<div class="form-group">
							<label for="country">Country</label> <input type="text"
								class="form-control" id="country" name="address.country"
								required>
						</div>
						<div class="form-group">
							<label for="fees">Fees</label> <input type="text"
								class="form-control" id="fees" name="fees" readonly>
						</div>


						<button type="submit" class="btn btn-primary btn-block">Submit</button>
					</form>
				</div>
			</div>
		</div>
	</div>


	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>

	<script>
	
	$(document).ready(function() {
	    <c:if test="${empty message or message == 'Invalid Login Attempt try again'}">
	        setTimeout(function() {
	            $('#exampleModalLong').modal('show');
	        }, 2000);
	    </c:if>
	});
	function openModal() {
        $('#studentModal').modal('show');
    }
	document.addEventListener('DOMContentLoaded', function() {
	    fetchCourses();
	});

	function fetchCourses() {
	    fetch('/getCourses', {
	        method: 'POST'
	    })
	    .then(response => response.json())
	    .then(data => populateCourses(data))
	    .catch(error => console.error('Error fetching courses:', error));
	}

	function populateCourses(courses) {
	    const courseSelect = document.getElementById('course');
	    courses.forEach(course => {
	        const option = document.createElement('option');
	        option.value = course.courseId;
	        option.textContent = course.courseName;
	        option.setAttribute('data-fees', course.fees); 
	        courseSelect.appendChild(option);
	    });

	    courseSelect.addEventListener('change', function() {
	        const selectedCourseId = this.value;
	        const selectedCourse = courses.find(course => course.courseId == selectedCourseId);
	        populateBranches(selectedCourse.branches);
	        updateFees(selectedCourse);
	    });
	}

	function populateBranches(branches) {
	    const branchSelect = document.getElementById('branch');
	    branchSelect.innerHTML = '<option value="">Select Branch</option>';
	    branches.forEach(branch => {
	        const option = document.createElement('option');
	        option.value = branch.branchId;
	        option.textContent = branch.branchName;
	        branchSelect.appendChild(option);
	    });
	    branchSelect.disabled = false;
	    
	}

	function updateFees(selectedCourse) {
	    const feesInput = document.getElementById('fees');
	    
	    let fees = '';
	    if (selectedCourse) {
	        fees = selectedCourse.fees;
	    }
	    
	    feesInput.value = fees;
	  
	}

	</script>

</body>
</html>

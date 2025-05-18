var api = "http://localhost:9191/api/guest";
var guestTable;

function init() {
console.log('inside init');
// Add submit event to form for new and edit
$("#registrationForm").on('submit', function(event) {
event.preventDefault(); // Prevent the default form submission

console.log("Submitting");
createRegistration();
// $('#guestModal').modal('hide');
});
}

function createRegistration() {
console.log('inside createRegister');

// Retrieve the registration data from the form
var registerData = {
name: $("#name").val(),
lastName: $("#lastName").val(),
phone_num: $("#phone_num").val(),
email: $("#email").val(),
password: $("#password").val(),
confirmPassword: $("#confirmPassword").val()
};


if (!validatePassword(registerData.password, registerData.confirmPassword)) {
alert("Password and Confirm Password do not match. Please try again. Your password should have a-z, 0-9, and symbols like !@#$%^&*.");
return false;
}

var guestJson = JSON.stringify(registerData);
console.log(guestJson);

$.ajax({
url: api,
type: "POST",
data: guestJson,
contentType: "application/json; charset=utf-8",
dataType: "json",
success: function(guest) {
console.log(guest);
// Clear fields in page
$("#id").val('');
$("#name").val('');
$("#lastName").val('');
$("#email").val('');
$("#phone_num").val('');
$("#password").val('');
$("#confirmPassword").val('');
},
error: function(jqXHR, textStatus, errorThrown) {
console.log('Error: ' + textStatus);
}
});
}
function validatePassword(password, confirmPassword) {
// Validate the password
if (password !== confirmPassword) {
console.log("Password and Confirm Password do not match. Please try again. Your password should have a-z, 0-9, and symbols like !@#$%^&*.");
return false;
}

var passwordPattern = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d!@#$%^&*]{6,}$/;
if (!passwordPattern.test(password)) {
console.log("Password must contain at least 6 characters, including a letter and a number.");
return false;
}

showAlert('success', 'Your registration is successful! You can login!');
return true;
}

// Function to display an alert
function showAlert(type, message) {
var alertClass = type === 'success' ? 'alert-success' : 'alert-danger';
var alertHTML = '<div class="alert ' + alertClass + '">' + message + '</div>';

$('.container').prepend(alertHTML);
}
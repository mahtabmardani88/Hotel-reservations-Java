//$(document).ready(function() {
//  $('#forgotPasswordForm').submit(function(event) {
//    event.preventDefault();
//    var email = $('#email').val();
//    $.ajax({
//      type: 'POST',
//      url: '/resetpassword',
//      data: {
//        email: email
//      },
//      success: function(response) {
//        if (response === 'success') {
//          $('#successMessage').text('An email with instructions to reset your password has been sent to your email address.');
//          $('#successMessage').addClass('success-message').show();
//          $('#errorMessage').removeClass('error-message').hide();
//        } else if (response === 'notfound') {
//          $('#errorMessage').text('The provided email address does not exist in our records.');
//          $('#errorMessage').addClass('error-message').show();
//          $('#successMessage').removeClass('success-message').hide();
//        }
//      },
//      error: function() {
//        alert('An error occurred. Please try again later.');
//      }
//    });
//  });
//});
$(document).ready(function() {
  $('#forgotPasswordForm').submit(function(event) {
    event.preventDefault();
    var email = $('#email').val();
    $.ajax({
      type: 'POST',
      url: '/resetpassword',
      data: {
        email: email
      },
      success: function(response) {
        if (response === 'success') {
          $('#successMessage').text('An email with instructions to reset your password has been sent to your email address.');
          $('#successMessage').addClass('success-message').show();
          $('#errorMessage').removeClass('error-message').hide();
        } else if (response === 'notfound') {
          $('#errorMessage').text('The provided email address does not exist in our records.');
          $('#errorMessage').addClass('error-message').show();
          $('#successMessage').removeClass('success-message').hide();
        }
      },
      error: function() {
        alert('An error occurred. Please try again later.');
      }
    });
  });
});

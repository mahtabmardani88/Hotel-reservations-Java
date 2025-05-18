var api =http://localhost:9191/api/login;

$(document).ready(function() {
    $('#loginForm').submit(function(event) {
        event.preventDefault(); // Prevent the default form submission

        var email = $('#email').val();
        var password = $('#password').val();

        // Prepare the data to be sent to the server
        var formData = {
            email: email,
            password: password
        };

        // Perform an Ajax POST request to the login endpoint
        $.ajax({
            type: 'POST',
            url: '/login',
            data: JSON.stringify(formData),
            contentType: 'application/json',
            success: function(response) {
                if (response === 'admin') {
                    // Redirect to the admin page
                    window.location.href = '/admin';
                } else if (response === 'reservationguest') {
                    // Redirect to the customer page
                    window.location.href = '/reservationguest';
                } else {
                    // Show error message in a pop-up box
                    Swal.fire({
                        icon: 'error',
                        title: 'Invalid Email or Password',
                        text: 'Please check your login credentials',
                    });
                }
            },
            error: function() {
                // Show error message in a pop-up box
                Swal.fire({
                    icon: 'error',
                    title: 'An error occurred',
                    text: 'Please try again later',
                });
            }
        });
    });
});
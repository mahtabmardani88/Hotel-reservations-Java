var api = "http://localhost:9191/api/guest" ;
var guestTable;

function init(){

    console.log('inside init' );


    $("#newGuestButton").click( function () {
        console.log("Inside click of newGuestButton");
        $('#guestModal').modal('show');
    });

    $("#editGuestButton").click( function () {
        console.log("Inside click of editGuestButton");
        // Get the data from selected row and fill fields in modal


        if (guestTable.row($('.selected')).data() == undefined) {
            alert("Select Guest first");
        }else{
            var guest = guestTable.row($('.selected')).data();
            alert(guest.id);
            $("#id").val(guest.id);
            $("#name").val(guest.name);
            $("#lastName").val(guest.lastName);
            $("#email").val(guest.email);
            $("#phone_num").val(guest.phone_num);
            $("#password").val(guest.password);

            $('#guestModal').modal('show');
        }

    });

    $("#deleteGuestButton").click( function () {
        console.log("Inside click of deleteGuestButton");

        if (guestTable.row($('.selected')).data() == undefined) {
            alert("Select Guest first");
        }else{
            $('#guestDeleteModal').modal('show');
        }

    });

    // Button in modal
    $("#deleteGuestConfirmButton").click( function () {
        console.log("Inside click of deleteGuestButton");
        deleteGuest();
        $('#guestDeleteModal').modal('hide');
    });

    // Add submit event to form for new and edit
    $("#guestForm").on('submit', function() {
        console.log("Submitting");
        createGuest();
        $('#guestModal').modal('hide');
    });

    initGuestTable();
    // Get guest from backend and and update table
    getGuestData();



}

function initGuestTable() {

    console.log('inside initGuestTable' );

    // Create columns (with titles) for datatable:
    columns = [
        { "title":  "Guest ID",
            "data": "id" ,
            "visible": false },
        { "title":  "Name",
            "data": "name" },
        { "title":  "LastName",
            "data": "lastName" },
        { "title": "Email",
            "data": "email"},
       { "title": "Phone_num",
            "data": "phone_num"},
        { "title": "Password",
            "data": "password"},

    ];

    // Define new table with above columns
    guestTable = $("#guestTable").DataTable( {
        "order": [[ 0, "asc" ]],
        "columns": columns
    });


    $("#guestTable tbody").on( 'click', 'tr', function () {
        console.log("Clicking on row");
        if ( $(this).hasClass('selected') ) {
          $(this).removeClass('selected');
          // emptyRoomModals();
        }
        else {
            guestTable.$('tr.selected').removeClass('selected');
          // emptyRoomModals();
            $(this).addClass('selected');
        }
    });

}

function getGuestData(){

    console.log('inside getGuestData' );
    // http:/localhost:9191/api/guest
    // json list of guest
    $.ajax({
        url: api,
        type: "get",
        dataType: "json",
        // success: function(guests, textStatus, jqXHR){
        success: function(guests){

 //           console.log('Data: ' + guests );

            if (guests) {
                guestTable.clear();
                guestTable.rows.add(guests);
                guestTable.columns.adjust().draw();
            }
        },

        fail: function (error) {
            console.log('Error: ' + error);
        }

    });

}

function createGuest(){

    console.log('inside createGuest' );

    // Put guest data from page in Javascript object --- SIMILAR TO JSON
    var guestData = {
            id: $("#id").val(),
            name: $("#name").val(),
            lastName: $("#lastName").val(),
            email: $("#email").val(),
            phone_num: $("#phone_num").val(),
            password: $("#password").val()

    }

    // Transform Javascript object to json
    var guestJson = JSON.stringify(guestData);

    console.log(guestJson);

    $.ajax({
        url: api,
        type: "post",
        data: guestJson,    // json for request body
        contentType:"application/json; charset=utf-8",   // What we send to frontend
        dataType: "json",  // get back from frontend
        // success: function(guest, textStatus, jqXHR){
        success: function(guest){

          console.log(guest);

          // Clear fields in page
          $("#gues.id").val('');
          $("#name").val('');
          $("#lastname").val('');
          $("#email").val('');
          $("#phone_num").val('');
          $("#password").val('');


          // Refresh table data
          getGuestData();

        },

        fail: function (error) {
          console.log('Error: ' + error);
        }

    });

}

function deleteGuest(){

    if (guestTable.row($('.selected')).data() == undefined) {
        alert("Select guests first");
    }else{

        var guest = guestTable.row($('.selected')).data();

        console.log(api + '/' + guest.id);

            $.ajax({
                url: api + '/' + guest.id,
                type: "delete",
                contentType: "application/json",
                dataType: "text",  // get back from frontend
                // success: function(guest, textStatus, jqXHR){
                success: function(message){

                  console.log(message);

                  // Refresh table data
                  getGuestData();

                },

                fail: function (error) {
                  console.log('Error: ' + error);
                }

            });
    }
}
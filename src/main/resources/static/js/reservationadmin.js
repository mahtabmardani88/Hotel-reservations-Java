var api = "http://localhost:9191/api/reservation" ;
var reservationTable;

function init(){
//    alert('Hello');
    console.log('inside init' );

    $("#newReservationButton").click( function () {
            console.log("Inside click of newReservationButton");
            $('#reservationModal').modal('show');
        });

        $("#editReservationButton").click( function () {
            console.log("Inside click of editReservationButton");
            // Get the data from selected row and fill fields in modal

            if (reservationTable.row($('.selected')).data() == undefined) {
                alert("Select Reservation first");
            }else{
                var reservation = reservationTable.row($('.selected')).data();
                alert(reservation.reservationId);
                 $("#reservationId").val(reservation.reservationId);
                $("#id").val(reservation.guest.id);
                $("#roomNumber").val(reservation.room.roomNumber);
                $("#startDate").val(reservation.startDate);
                $("#endDate").val(reservation.endDate);
                $("#adults").val(reservation.room.adults);
                $("#children").val(reservation.room.children);
                $("#discountId").val(reservation.discount.discountId);
                $("#facilities").val(reservation.room.facilities);
                $("#total_Price").val(reservation.total_Price);
                $("#cancellation").prop("checked", reservation.cancellation);

                $('#reservationModal').modal('show');
            }

        });

    $("#deleteReservationButton").click( function () {
        console.log("Inside click of deleteReservationButton");

        if (reservationTable.row($('.selected')).data() == undefined) {
            alert("Select Reservation first");
        }else{
            $('#reservationDeleteModal').modal('show');
        }

    });

    // Button in modal
    $("#deleteReservationConfirmButton").click( function () {
        console.log("Inside click of deleteReservationButton");
        deleteReservation();
        $('#ReservationDeleteModal').modal('hide');
    });

    // Add submit event to form for new and edit
    $("#reservationForm").on('submit', function() {
        console.log("Submitting");
        createReservation();
        $('#reservationModal').modal('hide');
    });

    initReservationTable();
    // Get reservations from backend and and update table
    getReservationData();



}

function initReservationTable() {

    console.log('inside initReservationTable' );

    // Create columns (with titles) for datatable:
    columns = [
            { "title":  "ReservationId",
                "data": "reservationId" ,
                "visible": true },
          { "title":  "Guest ID",
                 "data": "guest.id"},
           { "title":  "Room Number",
                "data": "room.roomNumber" },
            { "title":  "Start Date",
                "data": "startDate" },
            { "title":  "End Date",
                "data": "endDate" },
            { "title": "Adults",
                "data": "room.adults"},
            { "title": "Children",
                "data": "room.children"},
             { "title":  "DiscountId",
               "data": "discount.discountId" ,
                "visible": false },
            { "title": "Percentage",
             "data": "discount.percent_of_Discount"},
            { "title": "Room Type",
               "data": "room.roomType"},
            { "title": "Total Price",
              "data": "total_Price"},
            { "title": "Cancellation",
              "data": "cancellation"},

    ];

    // Define new table with above columns
    reservationTable = $("#reservationTable").DataTable( {
        "order": [[ 0, "asc" ]],
        "columns": columns
    });


    $("#reservationTable tbody").on( 'click', 'tr', function () {
        console.log("Clicking on row");
        if ( $(this).hasClass('selected') ) {
          $(this).removeClass('selected');
          // emptyReservationModals();
        }
        else {
            reservationTable.$('tr.selected').removeClass('selected');
          // emptyReservationModals();
            $(this).addClass('selected');
        }
    });

}

function getReservationData(){

    console.log('inside getReservationData' );
    // http:/localhost:9191/api/reservation
    // json list of guest
    $.ajax({
        url: api,
        type: "get",
        dataType: "json",
        // success: function(reservations, textStatus, jqXHR){
        success: function(reservations){

 //           console.log('Data: ' + reservations );

            if (reservations) {
               reservationTable.clear();
               reservationTable.rows.add(reservations);
               reservationTable.columns.adjust().draw();
            }
        },

        fail: function (error) {
            console.log('Error: ' + error);
        }

    });

}

function createReservation(){

    console.log('inside createReservation' );

    // Put reservation data from page in Javascript object --- SIMILAR TO JSON
    var reservationData = {
                reservationId: $("#reservationId").val(),
               guest:{ id: $("#id").val()},

                room: { roomNumber: $("#roomNumber").val(),
                adults: $("#adults").val(),
                children: $("#children").val(),
                facilities: $("#facilities").val()},

                startDate: $("#startDate").val(),
                endDate: $("#endDate").val(),

               discount : {discountId: $("#discountId").val()},

                total_Price: $("#total_Price").val(),
                cancellation: $("#cancellation").prop('checked')

    }

    // Transform Javascript object to json
    var reservationJson = JSON.stringify(reservationData);

    console.log(reservationJson);

    $.ajax({
        url: api,
        type: "post",
        data: reservationJson,    // json for request body
        contentType:"application/json; charset=utf-8",   // What we send to frontend
        dataType: "json",  // get back from frontend
        // success: function(reservation, textStatus, jqXHR){
        success: function(reservation){

          console.log(reservation);

          // Clear fields in page
          $("#reservationId").val('');
                    $("#guest.id").val('');
                     $("#roomNumber").val('');
                    $("#startDate").val('');
                    $("#endDate").val('');
                    $("#adults").val('');
                    $("#children").val('');
                    $("#percent_of_Discount").val('');
                    $("#facilities").val('');
                    $("#total_Price").val('');
                    $("#cancellation").val('');

          // Refresh table data
          getReservationData();

        },

        fail: function (error) {
          console.log('Error: ' + error);
        }

    });

}

function deleteReservation(){

    if (reservationTable.row($('.selected')).data() == undefined) {
        alert("Select Reservation first");
    }else{
        var reservation = reservationTable.row($('.selected')).data();

        console.log(api + '/' + reservation.reservationId);

            $.ajax({
                url: api + '/' + reservation.reservationId,
                type: "delete",
                contentType: "application/json",
                dataType: "text",  // get back from frontend
                // success: function(reservation, textStatus, jqXHR){
                success: function(message){

                  console.log(message);

                  // Refresh table data
                  getReservationData();

                },

                fail: function (error) {
                  console.log('Error: ' + error);
                }

            });
    }
}
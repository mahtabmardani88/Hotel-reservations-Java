var api_room = "/api/room";
var api_reservation = "/api/reservation";
var roomTable;

function init(){
//assign functions to events (click, submit)
$("#adults, #children, #roomType, #check_in_date, #check_out_date").on("change", function () {
getAvailableRooms();
});

// Add submit event to form for new and edit
$("#check-availability").on("click", function() {
console.log("Checking");
getAvailableRooms();
});
//initialize roomTable;
initRoomTable();
getAllRooms();
// refresh roomTable with data
}

function initRoomTable() {
// Create columns (with titles) for datatable: id, name, address, age
var columns = [
{
title: "Room number",
data: "roomNumber",
},
{
title: "Room Type",
data: "roomType",
},
{
title: "Adult",
data: "adults",
},
{
title: "Children",
data: "children",
},
];

// Define new table with above columns
roomTable = $("#room-table").DataTable({
order: [[0, "asc"]],
columns: columns,
});
}

function getAllRooms() {
    $.ajax({
        url: "/api/rooms",
        type: "get",
        dataType: "json",
        success: function (rooms) {
            refreshRoomTable(rooms);
        },
        error: function (error) {
            console.log("Error: " + error);
        }
    });
}
function getAvailableRooms() {
  var checkInDate = $("#check_in_date").val();
  var checkOutDate = $("#check_out_date").val();
//    var queryString = "?check-in-date=" + checkInDate + "&check-out-date=" + checkOutDate;

    var criteria = {
       "check-in-date": checkInDate,
       "check-out-date": checkOutDate,
       adults: $("#adults").val(),
       children: $("#children").val(),
       roomType: $("#roomType").val(),
     };
//     var criteriaJson = JSON.stringify(criteria);
$.ajax({
  url: "http://localhost:9191/api/reservationguest/available",
  type: "POST",
  contentType: "application/json",
//  data: JSON.stringify(yourData),
  data: JSON.stringify(criteria),
  success: function(response) {
  },
  error: function(xhr, status, error) {
    console.log("Error: " + error);
    console.log(xhr.responseText);
  }
});

}
function createReservationGuest(reservation) {
    var reservationJson = JSON.stringify(reservation);

    $.ajax({
        url: api_reservation,
        type: "post",
        data: reservationJson,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (reservation) {
            console.log("Reservation created: " + JSON.stringify(reservation));
        },
        error: function (error) {
            console.log("Error: " + error);
        },
    });
}


function refreshRoomTable( rooms){

if (rooms) {
roomTable.clear();
roomTable.rows.add(rooms);
roomTable.columns.adjust().draw();
}

}
init();
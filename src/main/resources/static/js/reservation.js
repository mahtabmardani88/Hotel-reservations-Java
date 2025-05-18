//// insert variable for API calls
//var api_room = "/api/room";
//var api_reservation = "/api/reservation";
//
//// Insert variable for dataTable
//var roomTable;
//
//function init() {
//  // Assign functions to events (click, change)
//  $("#adults, #children, #roomType, #startDate, #endDate").on("change", function () {
//    getAvailableRooms();
//  });
//
//  // Add submit event to form for new and edit
//    $("#check-availability").on('click', function() {
//        console.log("Checking");
//        getAvailableRooms();
//
//  });
//
//  // Initialize roomTable
//  initRoomTable();
//  getAllRooms();
//}
//
//function initRoomTable() {
//  // Create columns (with titles) for datatable: id, name, address, age
//  var columns = [
//    {
//      title: "Reservation ID",
//      data: "reservationId",
//      visible: false,
//    },
//    {
//      title: "Room number",
//      data: "roomNumber",
//    },
//    {
//      title: "Room Type",
//      data: "roomType",
//    },
//    {
//      title: "Adult",
//      data: "adults",
//    },
//    {
//      title: "Children",
//      data: "children",
//    },
//  ];
//
//  // Define new table with above columns
//  roomTable = $("#room-table").DataTable({
//    order: [[0, "asc"]],
//    columns: columns,
//  });
//}
//
//function getAllRooms() {
//  $.ajax({
//    url: api_room,
//    type: "get",
//    dataType: "json",
//    success: function (rooms) {
//      // Refresh table data
//      refreshRoomTable(rooms);
//    },
//    error: function (error) {
//      console.log("Error: " + error);
//    },
//  });
//}
//
//function getAvailableRooms() {
//  var checkInDate = $("#startDate").val();
//  var checkOutDate = $("#endDate").val();
//  var queryString = "?check-in-date=" + checkInDate + "&check-out-date=" + checkOutDate
//
//  var criteria = {
////    check_in_date: checkInDate,
////    check_out_date: checkOutDate,
//    adults: $("#adults").val(),
//    children: $("#children").val(),
//    roomType: $("#roomType").val(),
//  };
//
//    var criteriaJson = JSON.stringify(criteria);
//
//
//  $.ajax({
//    url: api_reservation + "/available",
//    type: "post",
//    data: criteriaJson,
//    contentType:"application/json; charset=utf-8",   // What we send to frontend
//    dataType: "json",
//    success: function (rooms) {
//      // Refresh table data
//      refreshRoomTable(rooms);
//    },
//    error: function (error) {
//      console.log("Error: " + error);
//    },
//  });
//}
//
//function refreshRoomTable(rooms) {
//  if (rooms) {
//    roomTable.clear();
//    roomTable.rows.add(rooms);
//    roomTable.columns.adjust().draw();
//  }
//}
// insert variable for api call
var api_room = "/api/room";
var api_reservation = "/api/reservation";

// Insert vaiable for dataTable
var roomTable;

function init(){

    //assign functions to events (click, submit)

    $("#adults, #children, #roomType, #startDate, #endDate").on("change", function () {
        getAvailableRooms();
    });

    // Add submit event to form for new and edit
    $("#check-availability").on('click', function() {
        console.log("Checking");
        getAvailableRooms();

    });
    //initialize roomTable;
    initRoomTable();
    getAllRooms();
    // refresh roomTable with data
}

function initRoomTable(){

    // Create columns (with titles) for datatable: id, name, address, age
    columns = [
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
            }
    ];


function getDiscountData() {
    // Function implementation

    // Define new table with above columns
    roomTable = $("#room-table").DataTable( {
        "order": [[ 0, "asc" ]],
        "columns": columns
    });

    $("#room-table tbody").on( 'click', 'tr', function () {
        console.log("Clicking on row");
        if ( $(this).hasClass('selected') ) {
          $(this).removeClass('selected');
          clearDiscountData();
          // emptyRoomModals();
        }
        else {
          roomTable.$('tr.selected').removeClass('selected');
          $(this).addClass('selected');
         getDiscountData();

        }
    });

}
}




function seaViewRenderer( seaView){
    if (seaView == true) {
        return "with sea view"
    } else {
        return "without sea view"
    }
}

function getAllRooms(){

    $.ajax({
        url: api_room,
        type: "get",
        dataType: "json",  // get back from frontend
        // success: function(reservation, textStatus, jqXHR){
        success: function(rooms){

          // Refresh table data
          refreshRoomTable(rooms);

        },

        fail: function (error) {
          console.log('Error: ' + error);
        }

    });

}
function getAvailableRooms(){

    var checkInDate = $("#startDate").val();
    var checkOutDate = $("#endDate").val();
    var queryString = "?check-in-date=" + checkInDate + "&check-out-date=" + checkOutDate

    var criteria = {
        check_in_date: checkInDate,
        check_out_date: checkOutDate,
        adults: $("#adults").val(),
        children: $("#children").val(),
        roomType: $("#roomType").val()
        };

    var criteriaJson = JSON.stringify(criteria);
//       alert("Get available rooms " + criteriaJson);
        // get data from form
        // data -> json
        // call post backend url
        // refresh roomTable

    $.ajax({
        url: api_reservation + "/available" + queryString,
        type: "post",
        data: criteriaJson,    // json for request body
        contentType:"application/json; charset=utf-8",   // What we send to frontend
        dataType: "json",  // get back from frontend
        // success: function(reservation, textStatus, jqXHR){
        success: function(rooms){

          // Refresh table data
          refreshRoomTable(rooms);

        },

        fail: function (error) {
          console.log('Error: ' + error);
        }

    });
}

function refreshRoomTable( rooms){
    if (rooms) {
        roomTable.clear();
        roomTable.rows.add(rooms);
        roomTable.columns.adjust().draw();
    }
}

//--------------------

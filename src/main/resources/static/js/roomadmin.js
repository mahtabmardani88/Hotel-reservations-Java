
var api = "http://localhost:9191/api/room" ;
var roomTable;

function init(){

    console.log('inside init' );


    $("#newRoomButton").click( function () {
        console.log("Inside click of newRoomButton");
        $('#roomModal').modal('show');
    });

    $("#editRoomButton").click( function () {
        console.log("Inside click of editRoomButton");
        // Get the data from selected row and fill fields in modal

        if (roomTable.row($('.selected')).data() == undefined) {
            alert("Select Room first");
        }else{
            var room = roomTable.row($('.selected')).data();
            alert(room.roomId);
             $("#roomId").val(room.roomId);
            $("#roomNumber").val(room.roomNumber);
            $("#floor").val(room.floor);
            $("#bedType").val(room.bedType);
            $("#adults").val(room.adults);
            $("#children").val(room.children);
            $("#comments").val(room.comments);
            $("#facilities").val(room.facilities);
            $("#roomType").val(room.roomType);
            $("#price").val(room.price);
            $("#cleaned").prop("checked", room.cleaned);

            $('#roomModal').modal('show');
        }

    });

    $("#deleteRoomButton").click( function () {
        console.log("Inside click of deleteRoomButton");

        if (roomTable.row($('.selected')).data() == undefined) {
            alert("Select Room first");
        }else{
            $('#roomDeleteModal').modal('show');
        }

    });

    // Button in modal
    $("#deleteRoomConfirmButton").click( function () {
        console.log("Inside click of deleteRoomButton");
        deleteRoom();
        $('#RoomDeleteModal').modal('hide');
    });

    // Add submit event to form for new and edit
    $("#roomForm").on('submit', function() {
        console.log("Submitting");
        createRoom();
        $('#roomModal').modal('hide');
    });

    initRoomTable();
    // Get rooms from backend and and update table
    getRoomData();



}

function initRoomTable() {

    console.log('inside initRoomTable' );

    // Create columns (with titles) for datatable:
    columns = [
        { "title":  "roomId ID",
            "data": "roomId" ,
            "visible": false },
      { "title":  "RoomNumber",
      "data": "roomNumber" },
        { "title":  "Floor",
            "data": "floor" },
        { "title":  "BedType",
            "data": "bedType" },
        { "title": "Adults",
            "data": "adults"},
       { "title": "Children",
            "data": "children"},
        { "title": "Comments",
            "data": "comments"},
         { "title": "Facilities",
          "data": "facilities"},
          { "title": "RoomType",
             "data": "roomType"},
              { "title": "Price",
            "data": "price"},
            { "title": "Cleaned",
            "data": "cleaned"},


    ];

    // Define new table with above columns
    roomTable = $("#roomTable").DataTable( {
        "order": [[ 0, "asc" ]],
        "columns": columns
    });


    $("#roomTable tbody").on( 'click', 'tr', function () {
        console.log("Clicking on row");
        if ( $(this).hasClass('selected') ) {
          $(this).removeClass('selected');
          // emptyRoomModals();
        }
        else {
            roomTable.$('tr.selected').removeClass('selected');
          // emptyRoomModals();
            $(this).addClass('selected');
        }
    });

}

function getRoomData(){

    console.log('inside getRoomData' );
    // http:/localhost:9191/api/room
    // json list of guest
    $.ajax({
        url: api,
        type: "get",
        dataType: "json",
        // success: function(rooms, textStatus, jqXHR){
        success: function(rooms){

 //           console.log('Data: ' + rooms );

            if (rooms) {
                roomTable.clear();
                roomTable.rows.add(rooms);
                roomTable.columns.adjust().draw();
            }
        },

        fail: function (error) {
            console.log('Error: ' + error);
        }

    });

}

function createRoom(){

    console.log('inside createRoom' );

    // Put guest data from page in Javascript object --- SIMILAR TO JSON
    var roomData = {
            roomId: $("#roomId").val(),
            roomNumber: $("#roomNumber").val(),
            floor: $("#floor").val(),
            bedType: $("#bedType").val(),
            adults: $("#adults").val(),
            children: $("#children").val(),
            facilities: $("#facilities").val(),
             comments: $("#comments").val(),
            roomType: $("#roomType").val(),
            price: $("#price").val(),
            cleaned: $("#cleaned").prop('checked')


    }

    // Transform Javascript object to json
    var roomJson = JSON.stringify(roomData);

    console.log(roomJson);

    $.ajax({
        url: api,
        type: "post",
        data: roomJson,    // json for request body
        contentType:"application/json; charset=utf-8",   // What we send to frontend
        dataType: "json",  // get back from frontend
        // success: function(room, textStatus, jqXHR){
        success: function(room){

          console.log(room);

          // Clear fields in page
          $("#roomId").val('');
          $("#roomNumber").val('');
          $("#floor").val('');
          $("#bedType").val('');
          $("#adults").val('');
          $("#children").val('');
          $("#facilities").val('');
          $("#comments").val('')
          $("#roomType").val('');
           $("#price").val('');
          $("#cleaned").val('');

          // Refresh table data
          getRoomData();

        },

        fail: function (error) {
          console.log('Error: ' + error);
        }

    });

}

function deleteRoom(){

    if (roomTable.row($('.selected')).data() == undefined) {
        alert("Select room first");
    }else{
        var room = roomTable.row($('.selected')).data();

        console.log(api + '/' + room.roomId);

            $.ajax({
                url: api + '/' + room.roomId,
                type: "delete",
                contentType: "application/json",
                dataType: "text",  // get back from frontend
                // success: function(room, textStatus, jqXHR){
                success: function(message){

                  console.log(message);

                  // Refresh table data
                  getRoomData();

                },

                fail: function (error) {
                  console.log('Error: ' + error);
                }

            });

    }
}
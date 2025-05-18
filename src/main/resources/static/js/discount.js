var api = "http://localhost:9191/api/discount";
var discountTable;

function init() {
    console.log('inside init');


    $("#newDiscountButton").click(function () {
        console.log("Inside click of newDiscountButton");
        $('#discountModal').modal('show');
    });

    $("#editDiscountButton").click(function () {
        console.log("Inside click of editDiscountButton");
        // Get the data from selected row and fill fields in modal

        if (discountTable.row($('.selected')).data() == undefined) {
            alert("Select Discount first");
        } else {
            var discount = discountTable.row($('.selected')).data();
            alert(discount.discountId);
            $("#discountId").val(discount.discountId);
            $("#percent_of_Discount").val(discount.percent_of_Discount);
            $("#startDate").val(discount.startDate);
            $("#endDate").val(discount.endDate);
            $("#type").val(discount.type);

            $('#discountModal').modal('show');
        }
    });

    $("#deleteDiscountButton").click(function () {
        console.log("Inside click of deleteDiscountButton");

        if (discountTable.row($('.selected')).data() == undefined) {
            alert("Select Discount first");
        } else {
            $('#discountDeleteModal').modal('show');
        }
    });

    // Button in modal
    $("#deleteDiscountConfirmButton").click(function () {
                 console.log("Inside click of deleteDiscountButton");
                 deleteDiscount();
                 $('#DiscountDeleteModal').modal('hide');
             });

    // Add submit event to form for new and edit
    $("#discountForm").on('submit', function () {
        console.log("Submitting");
        createDiscount();
        $('#discountModal').modal('hide');
    });

    initDiscountTable();
    // Get Discount from backend and and update table
    getDiscountData();
}

function initDiscountTable() {
    console.log('inside initDiscountTable');

    // Create columns (with titles) for datatable:
    columns = [
        { "title": "Discount ID",
            "data": "discountId",
            "visible": false},
        { "title": "Percent_of_Discount",
            "data": "percent_of_Discount"},
        {"title": "StartDate",
            "data": "startDate"},
        { "title": "EndDate",
            "data": "endDate" },
        { "title": "RoomType",
            "data": "type" },
    ];

//    // Define new table with above columns
    discountTable = $("#discountTable").DataTable({
        "order": [[0, "asc"]],
        "columns": columns
    });

    $("#discountTable tbody").on('click', 'tr', function () {
        console.log("Clicking on row");
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            // emptyDiscountModals();
        } else {
            discountTable.$('tr.selected').removeClass('selected');
            // emptyDiscountModals();
            $(this).addClass('selected');
        }
    });
}

function getDiscountData() {
    console.log('inside getDiscountData');
    // http:/localhost:9191/api/discount
    // json list of discount
    $.ajax({
        url: api,
        type: "get",
        dataType: "json",
        // success: function(discounts, textStatus, jqXHR){
        success: function (discounts) {
            // console.log('Data: ' + discounts );
            if (discounts) {
                discountTable.clear();
                discountTable.rows.add(discounts);
                discountTable.columns.adjust().draw();
            }
        },
        fail: function (error) {
            console.log('Error: ' + error);
        }
    });
}
//function createDiscount() {
//    console.log('inside createDiscount');
//    // Put discount data from page in JavaScript object --- SIMILAR TO JSON
//    var discountData = {
//        discountId: $("#discountId").val(),
//        percent_of_Discount: $("#percent_of_Discount").val(),
//        startDate: $("#startDate").val(),
//        endDate: $("#endDate").val(),
//        type: $("#type").val()
//    }
//
//    // Transform JavaScript object to JSON
//    var discountJson = JSON.stringify(discountData);
//    console.log(discountJson);
//    $.ajax({
//        url: api,
//        type: "post",
//        data: discountJson,    // JSON for request body
//        contentType: "application/json; charset=utf-8",   // What we send to frontend
//        dataType: "json",  // Get back from frontend
//        success: function (discount) {
//            console.log(discount);
//            // Refresh table data
//            getDiscountData();
//        },
//        error: function (error) {
//            console.log('Error: ' + error);
//        }
//    });
//}


function createDiscount() {
    console.log('inside createDiscount');
    // Put discount data from page in Javascript object --- SIMILAR TO JSON
    var discountData = {
        discountId: $("#discountId").val(),
        percent_of_Discount: $("#percent_of_Discount").val(),
        startDate: $("#startDate").val(),
        endDate: $("#endDate").val(),
        type: $("#type").val()
    }


    // Transform Javascript object to json
    var discountJson = JSON.stringify(discountData);
    console.log(discountJson);
    $.ajax({
        url: api,
        type: "post",
        data: discountJson,    // json for request body
        contentType: "application/json; charset=utf-8",   // What we send to frontend
        dataType: "json",  // get back from frontend
        // success: function(discount, textStatus, jqXHR){
        success: function (discount) {
            console.log(discount);
            // Clear fields in page
            $("#discountId").val('');
            $("#Percent_of_Discount").val('');
            $("#startDate").val('');
            $("#endDate").val('');
            $("#type").val('');
            // Refresh table data
            getDiscountData();
        },
        fail: function (error) {
            console.log('Error: ' + error);
        }
    });
}



function deleteDiscount() {
    if (discountTable.row($('.selected')).data() == undefined) {
        alert("Select discounts first");
    } else {
        var discount = discountTable.row($('.selected')).data();
        console.log(api + '/' + discount.discountId);
        $.ajax({
            url: api + '/' + discount.discountId,
            type: "delete",
            contentType: "application/json",
            dataType: "text",  // get back from frontend
            // success: function(guest, textStatus, jqXHR){
            success: function (message) {
                console.log(message);
                // Refresh table data
                getDiscountData();
            },
            fail: function (error) {
                console.log('Error: ' + error);
            }
        });
    }
}

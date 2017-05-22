(function () {
	$("#cableName").prop('readonly', true);

	getAllRequest();

	$('#create_request_tbody').delegate("tr", "click", function (e) {
		compliteRequest($(this).attr('id'));
	});

	$('#create_request_form').on('submit', function (event) {
		event.preventDefault();
		createRequest();
	});


})();
//

function compliteRequest(cableName) {
	$('#modal_create_request').modal('toggle');
	$('#cableName').val(cableName);
}
;

function getAllRequest() {

	$.get('/allRequest').done(function (requests) {
		let requestArray = JSON.parse(requests);

		drowTable(requestArray);
	});
}
;


function createRequest() {
	let name = $('#cableName').val();
	let length = $('#length').val();
	let request = {};
	request.cableName = name;
	request.length = length;
	$.ajax({
		type: 'POST',
		url: '/request/create',
		data: JSON.stringify(request),
		contentType: 'application/json',
		success: function () {
			$(location).attr('href', '/');
		},
		error: function (jqXHR, textStatus, errorThrown) {
			$('.text-danger').remove();
			let errors = jqXHR.responseJSON;
			$.each(errors, function (i, val) {
				$('#' + val.fieldName).parent().append($('<p></p>').addClass('text-danger').text(val.message)
						);
			});
		}
	});

}
;


function drowTable(requests) {

	for (let i = 0; i < requests.length; i++) {
		let tr = $('<tr></tr>');
		$('<td>' + (i + 1) + '</td>').appendTo(tr);
		$('<td>' + requests[i].date + '</td>').appendTo(tr);
		$('<td>' + requests[i].cable.name + '</td>').appendTo(tr);
		$('<td>' + requests[i].length + '</td>').appendTo(tr);
		$('<button type="button" class="btn btn-link"  onclick="modal(' + requests[i].id + ')">Печат</button>').appendTo(tr);
		tr.appendTo('#requestTableBody');

	}
}
;

function modal(id) {

	let url = '/request/createPdf/' + id;
	$("#pdf").empty();
	PDFObject.embed(url, "#pdf");
	$("#pdfModal").modal();

}
;

function getCableName(chooseData) {
	let result = [];

	for (let i = 0; i < chooseData.length; i++) {
		result.push(chooseData[i].name);
	}
	return result;
}
;

//================================================//

$(function () {
	$('#create_request').on('click', function (event) {
		event.preventDefault();
		create();

	});
});


function create() {
	$.ajax({
		type: 'GET',
		url: '/cable/getCables',
		contentType: 'application/json',
		success: function (cables) {
			let tbody = $('#create_request_tbody');
			tbody.empty();
			$.each(cables, function (i, value) {
				tbody.append($('<tr></tr>').attr('id', '' + value.name)
						.append($('<td></td>').text(value.id))
						.append($('<td></td>').text(value.name))
						);
			});



			$('#modal_create_request').modal();
			
		}
	});

}
;

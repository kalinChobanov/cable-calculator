
$(document).ready(function () {

//	getAllRequest();
	
//	getCables();
	
//	getCores();
	
//	getConductor();

	$("input[name='core']").focus(function () {
		$.get('/conductor/choose').done(function (data) {
			let chooseData = JSON.parse(data);
			$("#tags").autocomplete({
				source: chooseData,
				minLength: 1,
				appendTo: $("#divCore"),
			});
		});
	});

	$('ul li').click(function () {
		$(this).addClass('active').siblings().removeClass('active');
	});



	$("#myBtn").click(function () {
		$("#myModal").modal();
	});
});


//function getAllRequest() {
//
//	$.get('/allRequest').done(function (requests) {
//		let requestArray = JSON.parse(requests);
//
//		drowTable(requestArray);
//	});
//};
//
//function drowTable(requests) {
//
//	for (let i = 0; i < requests.length; i++) {
//		let tr = $('<tr></tr>');
//		$('<td>' + (i + 1) + '</td>').appendTo(tr);
//		$('<td>' + requests[i].date + '</td>').appendTo(tr);
//		$('<td>' + requests[i].cable.name + '</td>').appendTo(tr);
//		$('<td>' + requests[i].length + '</td>').appendTo(tr);
//		$('<button type="button" class="btn btn-link"  onclick="modal('+requests[i].id +')">Печат</button>').appendTo(tr);
//		tr.appendTo('#requestTableBody');
//
//	}
//};

//function getCables() {
//
//	$.get('cable/getCables').done(function (cables) {
//		let cablesArray = JSON.parse(cables);
//
//		drowCableTable(cablesArray);
//	});
//};

//function drowCableTable(cables) {
//	
//	for (let i = 0; i < cables.length; i++) {
//		let tr = $('<tr></tr>');
//		$('<td>' + (i + 1) + '</td>').appendTo(tr);
//		$('<td>' + cables[i].name + '</td>').appendTo(tr);
//		
//		if (cables[i].conductor != null) {
//			$('<td>' + cables[i].conductor.name + '</td>').appendTo(tr);
//		}else {
//			$('<td></td>').appendTo(tr);
//		}
////		alert(cables[i].numberOfConductor);
//		
//		$('<td  class="text-center">' + cables[i].numberOfConductors + '</td>').appendTo(tr);
//		
//		tr.appendTo('#cableTableBody');
//	}
//};

//function getConductor() {
//
//	$.get('conductor/getAll').done(function (conductors) {
//		let conductorsArray = JSON.parse(conductors);
//
//		drowConductorsTable(conductorsArray);
//	});
//};
//
//function drowConductorsTable(conductors) {
//	
//	for (let i = 0; i < conductors.length; i++) {
//		let tr = $('<tr></tr>');
//		$('<td>' + (i + 1) + '</td>').appendTo(tr);
//		$('<td>' + conductors[i].name + '</td>').appendTo(tr);
//		
//		$('<td>' + conductors[i].material + '</td>').appendTo(tr);
//		
//		$('<td  class="text-center">' + conductors[i].degree + '</td>').appendTo(tr);
//		$('<td  class="text-center">' + conductors[i].cut + '</td>').appendTo(tr);
//		
//		tr.appendTo('#conductorTableBody');
//	}
//};

//function getCores() {
//
//	$.get('core/getCores').done(function (cores) {
//		let coresArray = JSON.parse(cores);
//
//		drowCoresTable(coresArray);
//	});
//};
//
//function drowCoresTable(cores) {
//	
//	for (let i = 0; i < cores.length; i++) {
//		let tr = $('<tr></tr>');
//		$('<td>' + (i + 1) + '</td>').appendTo(tr);
//		$('<td>' + cores[i].name + '</td>').appendTo(tr);
//		$('<td>' + cores[i].coreSize + '</td>').appendTo(tr);
//		
//		tr.appendTo('#coresTableBody');
//	}
//};

function getConductors() {


	$.get('/conductor/getAll').done(function (requests) {
		$("#myModal").modal();
		let currentRequest = JSON.parse(requests);
		console.dir(currentRequest);
		$('.modal-title').text('Жила');
		for (var i = 0; i < currentRequest.length; i++) {
			$('<p></p>').text(currentRequest[i].name).appendTo('#conductorsName');
		}

	});

};

//function modal(id) {
//
//	$.ajax({
//		type: 'GET',
//		url: '/request/test/' + id,
//		xhrFields: {
//			responseType: 'blob'
//		},
//		success: function (data) {
//			var file = new Blob([data], {type: 'application/pdf'}),
//					url = URL.createObjectURL(file),
//					_iFrame = document.createElement('iframe');
//			_iFrame.setAttribute('src', url);
//			_iFrame.setAttribute('width', '780px');
//			_iFrame.setAttribute('height', '550px');
//
//			$("#pdf").empty();
//			$("#pdfModal").modal();
//			
//			$('#pdfTitle').text('Поръчка');
//			$('#pdf').append(_iFrame);
//		}
//	});
//}




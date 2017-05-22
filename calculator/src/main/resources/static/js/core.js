(function () {
	getCores();

	$('#addCore').on('submit', function (event) {
		event.preventDefault();
		createCore();
	});
})();

function getCores() {

	$.get('core/getCores').done(function (cores) {
		let coresArray = JSON.parse(cores);

		drowCoresTable(coresArray);
	});
}
;

function drowCoresTable(cores) {

	for (let i = 0; i < cores.length; i++) {
		let tr = $('<tr></tr>');
		$('<td>' + (i + 1) + '</td>').appendTo(tr);
		$('<td>' + cores[i].name + '</td>').appendTo(tr);
		$('<td>' + cores[i].coreSize + '</td>').appendTo(tr);

		tr.appendTo('#coresTableBody');
	}
}
;

function createCore() {
	let name = $('#name').val();
	let material = $('#material').val();
	let coreSize = $('#coreSize').val();

	let request = {
		name: name,
		material: material,
		coreSize: coreSize
	};

	$.ajax({
		type: 'POST',
		url: '/core/create',
		data: JSON.stringify(request),
		contentType: 'application/json',
		success: function () {
			$(location).attr('href', '/core');
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

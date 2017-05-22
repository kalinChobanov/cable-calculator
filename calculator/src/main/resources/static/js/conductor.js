(function () {
	getConductor();

	$("input[name='core']").focus(function () {
		$.get('/conductor/choose').done(function (data) {
			let chooseData = JSON.parse(data);
			$("#tags").autocomplete({
				source: chooseData,
				appendTo: $("#divCore")
			});
		});
	});
})();

function getConductor() {

	$.get('conductor/getAll').done(function (conductors) {
		let conductorsArray = JSON.parse(conductors);

		drowConductorsTable(conductorsArray);
	});
};

function drowConductorsTable(conductors) {

	for (let i = 0; i < conductors.length; i++) {
		let tr = $('<tr></tr>');
		$('<td>' + (i + 1) + '</td>').appendTo(tr);
		$('<td>' + conductors[i].name + '</td>').appendTo(tr);

		$('<td>' + conductors[i].material + '</td>').appendTo(tr);

		$('<td  class="text-center">' + conductors[i].degree + '</td>').appendTo(tr);
		$('<td  class="text-center">' + conductors[i].cut + '</td>').appendTo(tr);

		tr.appendTo('#conductorTableBody');
	}
};
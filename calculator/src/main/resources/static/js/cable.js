(function () {
	getCables();
	
	$("input[name='conductorName']").focus(function () {
		$.get('/conductor/getAll').done(function (data) {
			let chooseData = JSON.parse(data);
			let result = getConductorsName(chooseData);
			$("#conductorName").autocomplete({
				source: result,
				appendTo: $("#divConductor")
			});
		});
	});
	
})();

function getCables() {

	$.get('cable/getCables').done(function (cables) {
		let cablesArray = cables;
		

		drowCableTable(cablesArray);
	});
};

function drowCableTable(cables) {
	
	for (let i = 0; i < cables.length; i++) {
		let tr = $('<tr></tr>');
		$('<td>' + (i + 1) + '</td>').appendTo(tr);
		$('<td>' + cables[i].name + '</td>').appendTo(tr);
		
		if (cables[i].conductor !== null) {
			$('<td>' + cables[i].conductor.name + '</td>').appendTo(tr);
		}else {
			$('<td></td>').appendTo(tr);
		}
		
		$('<td  class="text-center">' + cables[i].numberOfConductors + '</td>').appendTo(tr);
		
		tr.appendTo('#cableTableBody');
	}
};



function getConductorsName(chooseData){
	let result = [];
	
	for (let i = 0; i < chooseData.length; i++) {
		result.push(chooseData[i].name);
	}
	return result;
};
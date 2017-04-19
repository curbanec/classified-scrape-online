// return an array of arrays
var stuffs = $.ajax({
	url:"/api/dashboardPopulate/applicationActivites", 
	type:'GET'
}); 

var chart_plot_03_data = [
			[0, 1],
			[1, 9],
			[2, 6],
			[3, 10],
			[4, 5],
			[5, 17],
			[6, 6],
			[7, 10],
			[8, 7],
			[9, 11],
			[10, 35],
			[11, 9],
			[12, 12],
			[13, 5],
			[14, 3],
			[15, 4],
			[16, 9]
		];
var chart_plot_03_settings = {
		series: {
			curvedLines: {
				apply: true,
				active: true,
				monotonicFit: true
			}
		},
		colors: ["#26B99A"],
		grid: {
			borderWidth: {
				top: 0,
				right: 0,
				bottom: 1,
				left: 1
			},
			borderColor: {
				bottom: "#7F8790",
				left: "#7F8790"
			}
		}
	};

if ($("#chart_plot_03").length){
	console.log('Plot3');
	
	$.plot($("#chart_plot_03"), [{
		label: "activated tasks",
		data: chart_plot_03_data,
		lines: {
			fillColor: "rgba(150, 202, 89, 0.12)"
		}, 
		points: {
			fillColor: "#fff"
		}
	}], chart_plot_03_settings);
	
};

/*$.getJSON(<?php echo site_url('find_representatives/find_rep_by_address/get_coordinates'); ?>, function(data) {
	  console.log(data);
	});*/















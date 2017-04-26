var stuffs;

function setupGraph(data){
	console.log(stuffs);
	var chart_plot_03_data=[];
	var count = 0;
	for (var j = 0; j< data.length; j++){
		var point = [count, data[j].numberOfClicks];
		chart_plot_03_data.push(point);
		count++;
	}
	console.log(chart_plot_03_data);
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
	}

$.ajax({
	url:"/api/dashboardPopulate/applicationActivites", 
	type:'GET',
	success:function(data){
		stuffs=data;
		setupGraph(data);
	}
}); 














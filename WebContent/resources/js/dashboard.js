$(function(){
	
	// Datatable Default Options
	$.extend( $.fn.dataTable.defaults, {
		 searching: false,
			dom: "<'table-responsive'tr><'row'<'col-sm-6'i><'col-sm-6'p>>",
			language: {
				paginate: {
					previous: "&laquo;",
					next: "&raquo;"
				}
			},
			order: [
				[0, "asc"]
			]
	});
	
	$(".card").css({"border-radius": "4px"});
	
	// ================== Number of Users Bar Chart1 [START] ======================
	
	var barChartData = {"label" : [], "color" : [], "totalValue" : [], "totalCount" : []};
	var ctx = document.getElementById('bar-chart1');
	var myChart = null;
	
	var getNumberofUsers = function (filter) {
		$.ajax({
		    type : "POST",
		    contentType: 'application/json',
		    url : "getNumberofUsers.json",
		    dataType: 'json',
	        async: true,
		    data : JSON.stringify(getFilter()),
		    success: function(data){
		    	if(myChart) {
		    		myChart.destroy();
		    	}
		    	
		    	barChartData.label = [];
	    		barChartData.color = [];
	    		barChartData.totalValue = [];
	    		barChartData.totalCount = [];
		    	if(data) {
		    		console.log(data);
		    		for(let d of data) {
		    			barChartData.label.push(d.name);
		    			barChartData.color.push(d.colorCode);
		    			barChartData.totalValue.push(d.totalValue);
		    			barChartData.totalCount.push(d.totalCount);
		    		}
		    		myChart = prepareChart(barChartData.label, barChartData.color, barChartData.totalValue, barChartData.totalCount);
		    	}
		    }
		});
	};

	var prepareChart = function (label, color, totalValue, totalCount) {
		console.log(label);
		console.log(color);
		console.log(totalValue);
		console.log(totalCount);
		if(label && color && totalValue && totalCount) {
			return new Chart(ctx, {
			    type: 'bar',
			    data: {
			        labels: label,
			        datasets: [{
			            data: totalCount,
			            backgroundColor: color,
			            borderColor: color,
			            borderWidth: 1
			        }]
			    },
			    options: {
			        scales: {
			            yAxes: [{
			                ticks: {
			                    beginAtZero: true
			                }
			            }]
			        },
			        legend : {
			        	display : false
			        } ,
			        tooltips: {
			            callbacks: {
			                label: function(tooltipItem, data) {
			                	var labels = [];
			                    labels[0] = "Redemption : " + (totalCount[tooltipItem.index]).toLocaleString();
			                    labels[1] = "Value : " + (totalValue[tooltipItem.index]).toLocaleString();
			                    return labels;
			                }
			            }
			        }
			    }
			});
		}
	};
		
	// ================== Number of Users Bar Chart1 [END] ======================
	
	// ================== Number of subscribed clinics Bar Chart2[START] ======================

	var barChartData2 = {"label" : [], "color" : [], "totalValue" : [], "totalCount" : []};
	var ctx = document.getElementById('bar-chart2');
	var myChart2 = null;
	
	var getNumberofSubscribedClinics = function (filter) {
		$.ajax({
		    type : "POST",
		    contentType: 'application/json',
		    url : "getNumberofSubscribedClinics.json",
		    dataType: 'json',
	        async: true,
		    data : JSON.stringify(getFilter()),
		    success: function(data){
		    	if(myChart2) {
		    		myChart2.destroy();
		    	}
		    	
		    	barChartData2.label = [];
	    		barChartData2.color = [];
	    		barChartData2.totalValue = [];
	    		barChartData2.totalCount = [];
		    	if(data) {
		    		console.log(data);
		    		for(let d of data) {
		    			barChartData2.label.push(d.name);
		    			barChartData2.color.push(d.colorCode);
		    			barChartData2.totalValue.push(d.totalValue);
		    			barChartData2.totalCount.push(d.totalCount);
		    		}
		    		myChart2 = prepareChart(barChartData2.label, barChartData2.color, barChartData2.totalValue, barChartData2.totalCount);
		    	}
		    }
		});
	};

	var prepareChart2 = function (label, color, totalValue, totalCount) {
		console.log(label);
		console.log(color);
		console.log(totalValue);
		console.log(totalCount);
		if(label && color && totalValue && totalCount) {
			return new Chart(ctx, {
			    type: 'bar',
			    data: {
			        labels: label,
			        datasets: [{
			            data: totalCount,
			            backgroundColor: color,
			            borderColor: color,
			            borderWidth: 1
			        }]
			    },
			    options: {
			        scales: {
			            yAxes: [{
			                ticks: {
			                    beginAtZero: true
			                }
			            }]
			        },
			        legend : {
			        	display : false
			        } ,
			        tooltips: {
			            callbacks: {
			                label: function(tooltipItem, data) {
			                	var labels = [];
			                    labels[0] = "Redemption : " + (totalCount[tooltipItem.index]).toLocaleString();
			                    labels[1] = "Value : " + (totalValue[tooltipItem.index]).toLocaleString();
			                    return labels;
			                }
			            }
			        }
			    }
			});
		}
	};	
	// ================== Number of subscribed clinics [END] ======================
	
	// ================== Patients (Based on location) [START] ======================
	
	var getPatientsPerLocation = function (filter) {
		$.ajax({
		    type : "POST",
		    contentType: 'application/json',
		    url : "getPatientsPerLocation.json",
		    dataType: 'json',
	        async: true,
		    data : JSON.stringify(getFilter()),
		    success: function(data){
		    	if(data) {
		    		$("#patient-table").DataTable().destroy();
		    		$("#patients-per-location").html("");
		    		var rows = "";
		    		for(var i = 1; i <= data.length; i++) {
		    			rows += "<tr>";
		    			var d = data[i - 1]; 
		    			rows += "<td class='col-sm-1'>"+i+"</td>";
		    			if(d) {
		    				if(d.name) {
		    					rows += "<td class='col-sm-5'><a href=dashboard.html?userId="+d.userId+">"+d.name+"</a></td>";
		    				} else {
		    					rows += "<td class='col-sm-5'>&nbsp;</td>"
		    				}
		    				if(d.redeemCount) {
		    					rows += "<td class='col-sm-2' style='text-align: right; padding-right: 20px;'>"+(d.redeemCount).toLocaleString()+"</td>";
		    				} else {
		    					rows += "<td>&nbsp;</td>"
		    				}
		    				if(d.totalAmount) {
		    					rows += "<td data-order="+d.totalAmount+" class='col-sm-4'><div class='media-middle media-right'><span class='label arrow-left arrow-primary' style='min-width:100px; font-size: 13px;'>"+(d.totalAmount).toLocaleString() +" MMK</span></div></td>";
		    				} else {
		    					rows += "<td>&nbsp;</td>"
		    				}
		    			}
		    			rows += "</tr>";
		    		}
		    		$("#patients-per-location").append(rows);
		    		$("#patient-table").DataTable().draw();
		    	}
		    	
		    }
		});
	};

	
	// ================== Patients (Based on location) [END] ======================
	
	// ================== Clinics (Based on locations) [START] ======================
	
	var getClinicsPerLocation = function (filter) {
		$.ajax({
		    type : "POST",
		    contentType: 'application/json',
		    url : "getClinicsPerLocation.json",
		    dataType: 'json',
	        async: true,
		    data : JSON.stringify(getFilter()),
		    success: function(data){
		    	if(data) {
		    		$("#clinic-table").DataTable().destroy();
		    		$("#clinics-per-location").html("");
		    		var rows = "";
		    		for(var i = 1; i <= data.length; i++) {
		    			rows += "<tr>";
		    			var d = data[i - 1]; 
		    			rows += "<td class='col-sm-1'>"+i+"</td>";
		    			if(d) {
		    				if(d.name) {
		    					rows += "<td class='col-sm-5'><a href=dashboard.html?userId="+d.userId+">"+d.name+"</a></td>";
		    				} else {
		    					rows += "<td class='col-sm-5'>&nbsp;</td>"
		    				}
		    				if(d.redeemCount) {
		    					rows += "<td class='col-sm-2' style='text-align: right; padding-right: 20px;'>"+(d.redeemCount).toLocaleString()+"</td>";
		    				} else {
		    					rows += "<td>&nbsp;</td>"
		    				}
		    				if(d.totalAmount) {
		    					rows += "<td data-order="+d.totalAmount+" class='col-sm-4'><div class='media-middle media-right'><span class='label arrow-left arrow-primary' style='min-width:100px; font-size: 13px;'>"+(d.totalAmount).toLocaleString() +" MMK</span></div></td>";
		    				} else {
		    					rows += "<td>&nbsp;</td>"
		    				}
		    			}
		    			rows += "</tr>";
		    		}
		    		$("#clinics-per-location").append(rows);
		    		$("#clinic-table").DataTable().draw();
		    	}
		    	
		    }
		});
	};
	
	// ================== Clinics (Based on locations) [END] ======================
});

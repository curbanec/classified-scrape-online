// var stuffs;

$.ajax({
	url:"/api/dashboardPopulate/applicationActivites?from=20170419&to=20170429", 
	type:'GET',
	success:function(data){
		// stuffs=data;
		setupGraph(data);
	}
}); 

/*$.ajax({
	url:"/api/dashboardPopulate/", 
	type:'GET',
	success:function(data){
		setupAlertTable(data);
	}
}); */

function setupAlertTable(data){
	
	$('#tableBody').append('<tr class="odd pointer"> <td class="a-center "> <input type="checkbox" class="flat" name="table_records"> </td>  <td class=" ">'+ area +'</td><td class=" ">'+ submissionTime +'</td><td class=" ">1</td><td class=" ">'+ query +'</td><td class=" ">'+ notifyAddress +'</td><td class="a-right a-right "><button id='+ queryId +' class="btn btn-success" type="button" onclick="cancel('+ queryId +')">'+ 'Active' +'</button></td></tr>');
    
}




function setupGraph(data){
	// console.log(stuffs);
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

// minimize/maximize dropdowns
$(document).ready(function() {
    $('.collapse-link').on('click', function() {
        var $BOX_PANEL = $(this).closest('.x_panel'),
            $ICON = $(this).find('i'),
            $BOX_CONTENT = $BOX_PANEL.find('.x_content');
        
        if ($BOX_PANEL.attr('style')) {
            $BOX_CONTENT.slideToggle(200, function(){
                $BOX_PANEL.removeAttr('style');
            });
        } else {
            $BOX_CONTENT.slideToggle(200); 
            $BOX_PANEL.css('height', 'auto');  
        }

        $ICON.toggleClass('fa-chevron-up fa-chevron-down');
    });
});

$(function() {
    $('#reportrange').daterangepicker({
    	 locale: {
             format: 'YYYY/MM/DD'
         }	
    	});
});

$(function(){
	$('.applyBtn').on('click', function(){
		var from = $('input[name="daterangepicker_start"]').val().replace(/\//g,"");
		var to = $('input[name="daterangepicker_end"]').val().replace(/\//g,"");
		console.log(to)
		console.log(from)
		
		$.ajax({
			url:"/api/dashboardPopulate/applicationActivites?from=" + from + "&to=" + to, 
			type:'GET',
			success:function(data){
				setupGraph(data);
			}
		}); 
	});
});


// content from custom.js

var CURRENT_URL = window.location.href.split('#')[0].split('?')[0],
$BODY = $('body'),
$MENU_TOGGLE = $('#menu_toggle'),
$SIDEBAR_MENU = $('#sidebar-menu'),
$SIDEBAR_FOOTER = $('.sidebar-footer'),
$LEFT_COL = $('.left_col'),
$RIGHT_COL = $('.right_col'),
$NAV_MENU = $('.nav_menu'),
$FOOTER = $('footer');

//Sidebar
function init_sidebar() {
// TODO: This is some kind of easy fix, maybe we can improve this
var setContentHeight = function () {
	// reset height
	$RIGHT_COL.css('min-height', $(window).height());

	var bodyHeight = $BODY.outerHeight(),
		footerHeight = $BODY.hasClass('footer_fixed') ? -10 : $FOOTER.height(),
		leftColHeight = $LEFT_COL.eq(1).height() + $SIDEBAR_FOOTER.height(),
		contentHeight = bodyHeight < leftColHeight ? leftColHeight : bodyHeight;

	// normalize content
	contentHeight -= $NAV_MENU.height() + footerHeight;

	$RIGHT_COL.css('min-height', contentHeight);
};

  $SIDEBAR_MENU.find('a').on('click', function(ev) {
	  console.log('clicked - sidebar_menu');
        var $li = $(this).parent();

        if ($li.is('.active')) {
            $li.removeClass('active active-sm');
            $('ul:first', $li).slideUp(function() {
                setContentHeight();
            });
        } else {
            // prevent closing menu if we are on child menu
            if (!$li.parent().is('.child_menu')) {
                $SIDEBAR_MENU.find('li').removeClass('active active-sm');
                $SIDEBAR_MENU.find('li ul').slideUp();
            }else
            {
				if ( $BODY.is( ".nav-sm" ) )
				{
					$SIDEBAR_MENU.find( "li" ).removeClass( "active active-sm" );
					$SIDEBAR_MENU.find( "li ul" ).slideUp();
				}
			}
            $li.addClass('active');

            $('ul:first', $li).slideDown(function() {
                setContentHeight();
            });
        }
    });

// toggle small or large menu 
$MENU_TOGGLE.on('click', function() {
		console.log('clicked - menu toggle');
		
		if ($BODY.hasClass('nav-md')) {
			$SIDEBAR_MENU.find('li.active ul').hide();
			$SIDEBAR_MENU.find('li.active').addClass('active-sm').removeClass('active');
		} else {
			$SIDEBAR_MENU.find('li.active-sm ul').show();
			$SIDEBAR_MENU.find('li.active-sm').addClass('active').removeClass('active-sm');
		}

	$BODY.toggleClass('nav-md nav-sm');

	setContentHeight();
});

	// check active menu
	$SIDEBAR_MENU.find('a[href="' + CURRENT_URL + '"]').parent('li').addClass('current-page');

	$SIDEBAR_MENU.find('a').filter(function () {
		return this.href == CURRENT_URL;
	}).parent('li').addClass('current-page').parents('ul').slideDown(function() {
		setContentHeight();
	}).parent().addClass('active');

	// recompute content when resizing
	$(window).smartresize(function(){  
		setContentHeight();
	});

	setContentHeight();

	// fixed sidebar
	if ($.fn.mCustomScrollbar) {
		$('.menu_fixed').mCustomScrollbar({
			autoHideScrollbar: true,
			theme: 'minimal',
			mouseWheel:{ preventDefault: true }
		});
	}
};
// /Sidebar




$(document).ready(function() {
	

	init_sidebar();
	
			
});	


(function($,sr){
    // debouncing function from John Hann
    // http://unscriptable.com/index.php/2009/03/20/debouncing-javascript-methods/
    var debounce = function (func, threshold, execAsap) {
      var timeout;

        return function debounced () {
            var obj = this, args = arguments;
            function delayed () {
                if (!execAsap)
                    func.apply(obj, args); 
                timeout = null; 
            }

            if (timeout)
                clearTimeout(timeout);
            else if (execAsap)
                func.apply(obj, args);

            timeout = setTimeout(delayed, threshold || 100); 
        };
    };

    // smartresize 
    jQuery.fn[sr] = function(fn){  return fn ? this.bind('resize', debounce(fn)) : this.trigger(sr); };

})(jQuery,'smartresize');











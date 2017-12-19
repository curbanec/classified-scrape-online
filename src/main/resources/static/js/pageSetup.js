
function getCookie(c_name) {
    if(document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=");
        if(c_start != -1) {
            c_start = c_start + c_name.length + 1;
            c_end = document.cookie.indexOf(";", c_start);
            if(c_end == -1) c_end = document.cookie.length;
            return unescape(document.cookie.substring(c_start,c_end));
        }
    }
    return "";
}

function fillTable(data){
		
		for(var i=0;i<data.length;i++){
			
			if(data[i].isActiveIndicator){

				$('#tableBody').append('<tr class="odd pointer"> <td class="a-center "> <input type="checkbox" class="flat" name="table_records"> </td>  <td class=" " id="area">'+ data[i].area +'</td><td class=" ">'+ data[i].submissionTimeDate +'</td><td class=" ">1</td><td class=" " id="queryName">'+ data[i].queryName +'</td><td class=" " id="notifyAddress">'+ data[i].notifyAddress +'</td><td class="a-right a-right "><button id='+ data[i].queryId +' class="btn btn-success" type="button" onclick="cancelOrEnable('+ data[i].queryId + ',' + data[i].isActiveIndicator + ')">'+ 'Active' +'</button></td></tr>');

			} else {

				$('#tableBody').append('<tr class="odd pointer"> <td class="a-center "> <input type="checkbox" class="flat" name="table_records"> </td>  <td class=" " id="area">'+ data[i].area +'</td><td id='+ data[i].queryId + 'date' + ' class=" ">'+ data[i].submissionTimeDate +'</td><td class=" ">1</td><td class=" " id="queryName">'+ data[i].queryName +'</td><td class=" " id="notifyAddress">'+ data[i].notifyAddress +'</td><td class="a-right a-right "><button id='+ data[i].queryId +' class="btn btn-danger" type="button" onclick="cancelOrEnable('+ data[i].queryId + ',' + data[i].isActiveIndicator + ')">'+ 'Disabled' +'</button></td></tr>');
				
			}
		
			var newTableRow = $('input.flat');
			
			newTableRow.iCheck({
				checkboxClass: 'icheckbox_flat-green', 
				radioClass: 'iradio_flat-green'
				});
			
			    $('table input').on('ifChecked', function () {
			        checkState = '';
			        $(this).parent().parent().parent().addClass('selected');
			        countChecked();
			    });
			    $('table input').on('ifUnchecked', function () {
			        checkState = '';
			        $(this).parent().parent().parent().removeClass('selected');
			        countChecked();
			    });

			    var checkState = '';

			    $('.bulk_action input').on('ifChecked', function () {
			        checkState = '';
			        $(this).parent().parent().parent().addClass('selected');
			        countChecked();
			    });
			    $('.bulk_action input').on('ifUnchecked', function () {
			        checkState = '';
			        $(this).parent().parent().parent().removeClass('selected');
			        countChecked();
			    });
			    $('.bulk_action input#check-all').on('ifChecked', function () {
			        checkState = 'all';
			        countChecked();
			    });
			    $('.bulk_action input#check-all').on('ifUnchecked', function () {
			        checkState = 'none';
			        countChecked();
			    });

			    function countChecked() {
			        if (checkState === 'all') {
			            $(".bulk_action input[name='table_records']").iCheck('check');
			        }
			        if (checkState === 'none') {
			            $(".bulk_action input[name='table_records']").iCheck('uncheck');
			        }

			        var checkCount = $(".bulk_action input[name='table_records']:checked").length;

			        if (checkCount) {
			            $('.column-title').hide();
			            $('.bulk-actions').show();
			            $('.action-cnt').html(checkCount + ' Records Selected');
			        } else {
			            $('.column-title').show();
			            $('.bulk-actions').hide();
			        }
			    } 
		} 
		// var json = {"submissionTimeDate":submissionTimeDate, "area":area, "query":query, "notifyAddress":notifyAddress, "queryId":queryId};		
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

$(function() {
   
	console.log("get alerts");
   
    $.ajax({
	    headers: { 'X-XSRF-TOKEN': getCookie("XSRF-TOKEN") },
	 	url:"/api/main/retrieveAlertsForUser", 
		type:'GET',
		success:function(data){
			fillTable(data); //fillTable not created yet
		}
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
	/*$(window).smartresize(function(){  
		setContentHeight();
	});*/

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

function init_validator () {
	 
	if( typeof (validator) === 'undefined'){ return; }
	console.log('init_validator'); 
  
  // initialize the validator function
  validator.message.date = 'not a real date';

  // validate a field on "blur" event, a 'select' on 'change' event & a '.reuired' classed multifield on 'keyup':
  $('form')
    .on('blur', 'input[required], input.optional, select.required', validator.checkField)
    .on('change', 'select.required', validator.checkField)
    .on('keypress', 'input[required][pattern]', validator.keypress);

  $('.multi.required').on('keyup blur', 'input', function() {
    validator.checkField.apply($(this).siblings().last()[0]);
  });

  $('form').submit(function(e) {
    e.preventDefault();
    var submit = true;

    // evaluate the form using generic validaing
    if (!validator.checkAll($(this))) {
      submit = false;
    }

    if (submit)
      this.submit();

    return false;
	});
  
  };



$(document).ready(function() {
	init_sidebar();
	init_validator();
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
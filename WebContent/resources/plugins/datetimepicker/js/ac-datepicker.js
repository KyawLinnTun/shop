'use strict';
$(document).ready(function() {
    $('#d_week').datepicker({
        daysOfWeekDisabled: "2"
    });

    $('#d_highlight').datepicker({
        daysOfWeekHighlighted: "1"
    });

    $('.d_auto').datepicker({
        autoclose: true
    });
    
    $('.d_t_auto').datetimepicker({
    	format:'d/m/Y H:i',
    	minDate:0, //Now
    	//minTime:0, //Now
    	todayButton:true,
    	step:15,
        autoclose: true
    });
    $('.ac_d_auto').datetimepicker({
    	format:'d/m/Y',
    	 timepicker:false,
    	minDate:0, //Now
    	todayButton:true,
    	step:15,
        autoclose: true
    });
    
    $('.t_auto').datetimepicker({
    	format:'H:i',
    	datepicker:false,
    	todayButton:true,
    	step:15,
        autoclose: true
    });

    $('#d_disable').datepicker({
        datesDisabled: ['10/15/2018', '10/16/2018' ,  '10/17/2018' , '10/18/2018' ]
    });

    $('#d_toggle').datepicker({
        keyboardNavigation: false,
        forceParse: false,
        toggleActive: true
    });

    $('#startDate').datepicker({
    	format:'d/m/yyyy',
        keyboardNavigation: false,
        forceParse: false,
        todayHighlight: true
    });
    $('#endDate').datepicker({
    	format:'d/m/yyyy',
        keyboardNavigation: false,
        forceParse: false,
        todayHighlight: true
    });

    $('#disp_week').datepicker({
        calendarWeeks: true
    });

    $('#datepicker_range').datepicker({});
});

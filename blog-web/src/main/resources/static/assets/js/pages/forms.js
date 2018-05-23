//------------- forms.js -------------//
$(document).ready(function() {

	//------------- Check all Checkboxes -------------//
	$('#checkAllExample').checkAll({
		masterCheckbox: '.check-all',
		otherCheckboxes: '.check'
	});
  $('#checkAllFl').checkAll({
    masterCheckbox: '.check-all',
    otherCheckboxes: '.check'
  })
	//------------- Password metter -------------//
	$(function() {
		var $input = $('#password-metter');
	    var $output = $('#output-message');

	    $.passy.requirements.length.min = 4;

	    var feedback = [
	        { class: 'red-bg', text: 'password is poor' },
	        { class: 'teal-bg', text: 'might be okay' },
	        { class: 'blue-bg', text: 'password is good' },
	        { class: 'green-bg', text: 'good job fabulous password!' }
	    ];		
	    $input.passy(function(strength, valid) {
	        $output.text(feedback[strength].text);
	    });

		$('#generate-password').click(function() {
		    $input.passy( 'generate', 8 );
		});
		$('#show-password').click(function() {
		    $input.prop("type", "text");
		});
	});

	//------------- Autosize Text area -------------//
	$('.elastic').autosize({append: "\n"});

	//------------- Max Lenght input filed -------------//
	$('.limitInput').maxlength({
		alwaysShow: true,
		threshold: 10,
		warningClass: "label label-success",
		limitReachedClass: "label label-danger",
		validate: true
    });
	//------------- Max Lenght Textarea -------------//
	$('.limitTextarea').maxlength({
		alwaysShow: true,
		threshold: 10,
		warningClass: "label label-success",
		limitReachedClass: "label label-danger",
		separator: ' of ',
		preText: 'You have ',
		postText: ' chars remaining.',
		validate: true
    });

    //------------- File input styling -------------//
    $(":file").filestyle({
    	buttonText: "图片上传",
    	classButton: "btn btn-primary",
    	classInput: "form-control file-upload",
    	classIcon: "fa-plus-sign"
    });

    //------------- Color picker -------------//
    $("#color-picker").spectrum({
	    color: "#f00",
	    showInput: true
	});

	$("#minimalPicker").spectrum({
	    showButtons:false
	});

	$("#showPaletteOnly").spectrum({
	    showPaletteOnly: true,
	    showPalette:true,
	    color: 'blanchedalmond',
	    palette: [
	        ['black', 'white', 'blanchedalmond',
	        'rgb(255, 128, 0);', 'hsv 100 70 50'],
	        ['red', 'yellow', 'green', 'blue', 'violet']
	    ]
	});

	$("#flat-color-picker").spectrum({
	    flat: true,
	    showInput: true
	});

	//------------- Date time picker -------------//

	$(".datetime-picker").datetimepicker({
        format: "dd MM yyyy - hh:ii"
    }).on('show', function(){
	    $('.datetimepicker').find('th.prev>i').attr('class', '').addClass('en-arrow-left8');
	    $('.datetimepicker').find('th.next>i').attr('class', '').addClass('en-arrow-right8');
	});

    $(".datetime-picker1").datetimepicker({
        format: "dd MM yyyy -  hh:ii",
        autoclose: true,
        todayBtn: true,
        pickerPosition: "bottom-left",
    });

    $(".datetime-picker2").datetimepicker({
        format: "dd MM yyyy",
        autoclose: true,
        todayBtn: true,
        pickerPosition: "bottom-left",
        minView:2
    });

	//------------- Date range picker -------------//
	//bacis usage
	$('#daterangepicker').daterangepicker();

	//report range
	$('#reportrange').daterangepicker(
	    {
	      ranges: {
	         'Today': [moment(), moment()],
	         'Yesterday': [moment().subtract('days', 1), moment().subtract('days', 1)],
	         'Last 7 Days': [moment().subtract('days', 6), moment()],
	         'Last 30 Days': [moment().subtract('days', 29), moment()],
	         'This Month': [moment().startOf('month'), moment().endOf('month')],
	         'Last Month': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
	      },
	      startDate: moment().subtract('days', 29),
	      endDate: moment()
	    },
	    function(start, end) {
	        $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
	    }
	);

	//date and time picker
	$('#date-time-picker').daterangepicker({ 
		timePicker: true, 
		timePickerIncrement: 30, 
		format: 'MM/DD/YYYY h:mm A' 
	});

	//------------- Tags -------------//
	$('.tags').tagsInput({width: 'auto'});
	
	//------------- Spinner -------------//
	$('#spinner').spinner();

	$("#spinner-decimal").spinner({
		step: 0.01,
		numberFormat: "n"
	});
	$("#spinner-currency").spinner({
		min: 5,
		max: 2500,
		step: 25,
		start: 1000,
		numberFormat: "C"
    });

    $.widget("ui.timespinner", $.ui.spinner, {
	    options: {
	      // seconds
	      step: 60 * 1000,
	      // hours
	      page: 60
	    },
 
	    _parse: function( value ) {
	      if ( typeof value === "string" ) {
	        // already a timestamp
	        if ( Number( value ) == value ) {
	          return Number( value );
	        }
	        return +Globalize.parseDate( value );
	      }
	      return value;
	    },
 
	    _format: function( value ) {
	      return Globalize.format( new Date(value), "t" );
	    }
  	});
 
   $("#spinner-time").timespinner();

   //------------- Masked input fields -------------//
	$("#mask-phone").mask("(999) 999-9999", {completed:function(){alert("Callback action after complete");}});
	$("#mask-phoneExt").mask("(999) 999-9999? x99999");
	$("#mask-phoneInt").mask("+40 999 999 999");
	$("#mask-date").mask("99/99/9999");
	$("#mask-ssn").mask("999-99-9999");
	$("#mask-productKey").mask("a*-999-a999", { placeholder: "*" });
	$("#mask-eyeScript").mask("~9.99 ~9.99 999");
	$("#mask-percent").mask("99%");

	//------------- Select 2 -------------//
	$('.select2').select2({placeholder: 'Select state'});

	//------------- Dual list box -------------//
	$('.duallistbox').bootstrapDualListbox({
	    nonselectedlistlabel: 'Non-selected',
	    selectedlistlabel: 'Selected',
	    preserveselectiononmove: 'moved',
	    moveonselect: false,
	    iconMove: 'en-arrow-right8 s16',
	    iconMoveAll: 'fa-double-angle-right s16',
	    iconRemove: 'en-arrow-left8 s16',
	    iconRemoveAll: 'fa-double-angle-left s16'
	});

	//------------- Switches -------------//
	$('input.switch').onoff();
	//disabled switch
	$('input.switch:disabled').onoff('disable').closest('div.onoffswitch').addClass('onoffswitch-disabled');

	//------------- Sparklines -------------//
	$('#usage-sparkline').sparkline([35,46,24,56,68, 35,46,24,56,68], {
		width: '180px',
		height: '30px',
		lineColor: '#00ABA9',
		fillColor: false,
		spotColor: false,
		minSpotColor: false,
		maxSpotColor: false,
		lineWidth: 2
	});

	$('#cpu-sparkline').sparkline([22,78,43,32,55, 67,83,35,44,56], {
		width: '180px',
		height: '30px',
		lineColor: '#00ABA9',
		fillColor: false,
		spotColor: false,
		minSpotColor: false,
		maxSpotColor: false,
		lineWidth: 2
	});

	$('#ram-sparkline').sparkline([12,24,32,22,15, 17,8,23,17,14], {
		width: '180px',
		height: '30px',
		lineColor: '#00ABA9',
		fillColor: false,
		spotColor: false,
		minSpotColor: false,
		maxSpotColor: false,
		lineWidth: 2
	});

  $("#validate").validate({
    ignore: null,
    ignore: 'input[type="hidden"]',
    errorPlacement: function(error, element) {
      wrap = element.parent();
      wrap1 = wrap.parent();
      if (wrap1.hasClass('checkbox')) {
        error.insertAfter(wrap1);
      } else {
        if (element.attr('type')=='file') {
          error.insertAfter(element.next());
        } else {
          error.insertAfter(element);
        }
      }
    },
    errorClass: 'help-block',
    rules: {
      email: {
        required: true,
        email: true
      },
      select2: "required",
      password: {
        required: true,
        minlength: 5
      },
      confirm_password: {
        required: true,
        minlength: 5,
        equalTo: "#password"
      },
      textarea: {
        required: true,
        minlength: 10
      },
      maxLenght: {
        required: true,
        maxlength: 10
      },
      rangelenght: {
        required: true,
        rangelength: [10, 20]
      },
      url: {
        required: true,
        url: true
      },
      range: {
        required: true,
        range: [5, 10]
      },
      minval: {
        required: true,
        min: 13
      },
      maxval: {
        required: true,
        max: 13
      },
      date: {
        required: true,
        date: true
      },
      number: {
        required: true,
        number: true
      },
      digits: {
        required: true,
        digits: true
      },
      ccard: {
        required: true,
        creditcard: true
      },
      file: {
        required: true,
        accept: "png|jpeg|jpg|gif",
        filesize: 2048
      },
      agree: "required"
    },
    messages: {
      password: {
        required: "Please provide a password",
        minlength: "Your password must be at least 5 characters long"
      },
      confirm_password: {
        required: "Please provide a password",
        minlength: "Your password must be at least 5 characters long",
        equalTo: "Please enter the same password as above"
      },
      agree: "Please accept our policy",
      textarea: "Write some info for you",
      file: "File must be JPG, GIF or PNG, less than 2MB"
    },
    highlight: function(element) {
      if ($(element).offsetParent().parent().hasClass('form-group')) {
        $(element).offsetParent().parent().removeClass('has-success').addClass('has-error');
      } else {
        if ($(element).attr('type')=='file') {
          $(element).parent().parent().removeClass('has-success').addClass('has-error');
        }
        $(element).offsetParent().parent().parent().parent().removeClass('has-success').addClass('has-error');

      }
    },
    unhighlight: function(element,errorClass) {
      if ($(element).offsetParent().parent().hasClass('form-group')) {
        $(element).offsetParent().parent().removeClass('has-error').addClass('has-success');
        $(element.form).find("label[for=" + element.id + "]").removeClass(errorClass);
      } else if ($(element).offsetParent().parent().hasClass('checkbox')) {
        $(element).offsetParent().parent().parent().parent().removeClass('has-error').addClass('has-success');
        $(element.form).find("label[for=" + element.id + "]").removeClass(errorClass);
      } else if ($(element).next().hasClass('bootstrap-filestyle')) {
        $(element).parent().parent().removeClass('has-error').addClass('has-success');
      }
      else {
        $(element).offsetParent().parent().parent().removeClass('has-error').addClass('has-success');
      }
    }
  });

    //------------- Init pie charts -------------//
	initPieChart();

 	
});

//Setup easy pie charts
var initPieChart = function() {
	$(".pie-chart").easyPieChart({
        barColor: '#5a5e63',
        borderColor: '#5a5e63',
        trackColor: '#d9dde2',
        scaleColor: false,
        lineCap: 'butt',
        lineWidth: 10,
        size: 40,
        animate: 1500
    });
}
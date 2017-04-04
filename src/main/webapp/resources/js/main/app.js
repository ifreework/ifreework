 
//BACKGROUND CHANGER

  $(function() {
      $("#button-bg").click(function() {
    	  $("body").attr("class","");
    	  $("body").addClass("body_skin1");
      });
      $("#button-bg2").click(function() {
    	  $("body").attr("class","");
    	  $("body").addClass("body_skin2");
      });


      $("#button-bg3").click(function() {
    	  $("body").attr("class","");
    	  $("body").addClass("body_skin3");
      });

      $("#button-bg5").click(function() {
    	  $("body").attr("class","");
    	  $("body").addClass("body_skin5");
      });

      $("#button-bg6").click(function() {
    	  $("body").attr("class","");
    	  $("body").addClass("body_skin6");
      });

      $("#button-bg7").click(function() {
    	  $("body").attr("class","");
    	  $("body").addClass("body_skin7");
      });
      $("#button-bg8").click(function() {
    	  $("body").attr("class","");
    	  $("body").addClass("body_skin8");
      });
      $("#button-bg9").click(function() {
    	  $("body").attr("class","");
    	  $("body").addClass("body_skin9");
      });

      $("#button-bg10").click(function() {
    	  $("body").attr("class","");
    	  $("body").addClass("body_skin10");
      });
      $("#button-bg11").click(function() {
    	  $("body").attr("class","");
    	  $("body").addClass("body_skin11");
      });
      $("#button-bg12").click(function() {
    	  $("body").attr("class","");
    	  $("body").addClass("body_skin12");
      });

      $("#button-bg13").click(function() {
    	  $("body").attr("class","");
    	  $("body").addClass("body_skin13");
      });
      /**
       * Background Changer end
       */
  });

//TOGGLE CLOSE
    $('.nav-toggle').click(function() {
        //get collapse content selector
        var collapse_content_selector = $(this).attr('href');

        //make the collapse content to be shown or hide
        var toggle_switch = $(this);
        $(collapse_content_selector).slideToggle(function() {
            if ($(this).css('display') == 'block') {
                //change the button label to be 'Show'
                toggle_switch.html('<span class="entypo-minus-squared"></span>');
            } else {
                //change the button label to be 'Hide'
                toggle_switch.html('<span class="entypo-plus-squared"></span>');
            }
        });
    });


    $('.nav-toggle-alt').click(function() {
        //get collapse content selector
        var collapse_content_selector = $(this).attr('href');

        //make the collapse content to be shown or hide
        var toggle_switch = $(this);
        $(collapse_content_selector).slideToggle(function() {
            if ($(this).css('display') == 'block') {
                //change the button label to be 'Show'
                toggle_switch.html('<span class="entypo-up-open"></span>');
            } else {
                //change the button label to be 'Hide'
                toggle_switch.html('<span class="entypo-down-open"></span>');
            }
        });
        return false;
    });
    //CLOSE ELEMENT
    $(".gone").click(function() {
        var collapse_content_close = $(this).attr('href');
        $(collapse_content_close).hide();



    });

//tooltip
    $('.tooltitle').tooltip();
 